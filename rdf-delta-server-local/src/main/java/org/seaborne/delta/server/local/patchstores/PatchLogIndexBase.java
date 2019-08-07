/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.seaborne.delta.server.local.patchstores;

import static java.lang.String.format;

import java.util.Objects;
import java.util.function.Supplier;

import org.apache.jena.atlas.logging.Log;
import org.seaborne.delta.DeltaException;
import org.seaborne.delta.Id;
import org.seaborne.delta.Version;
import org.seaborne.delta.server.local.LogEntry;

/**
 * Implementation framework for a PatchLogIndex based on {@link LogIndex}.
 */
public abstract class PatchLogIndexBase implements PatchLogIndex {

    private final Object lock = new Object();
    // Natural values for a new patch log.
    private Version      earliestVersion = Version.INIT;
    private Id           earliestId      = null;
    private Version      currentVersion  = Version.INIT;
    private Id           currentId       = null;
    private Id           previousId      = null;
    private final LogIndex logIndex;

    protected PatchLogIndexBase(LogIndex logIndex, LogEntry earliest,  LogEntry latest) {
        this.logIndex = logIndex;
        if ( latest!=null ) {
            this.currentVersion = latest.getVersion();
            this.currentId = latest.getPatchId();
            this.previousId = latest.getPrevious();
        } else {
            this.currentVersion = logIndex.current();
            this.currentId = logIndex.fetchVersionToId(currentVersion);
            LogEntry e = (currentId==null) ? null : logIndex.fetchPatchInfo(currentId);
            if ( e != null )
                this.previousId = e.getPrevious();
        }
        if (earliest != null ) {
            earliestVersion = earliest.getVersion();
            earliestId = earliest.getPatchId();
            if ( earliest.getPrevious() != null )
                Log.warn(PatchLogIndexBase.class, "Dangling earliest prvious reference: "+earliest.getPrevious());
        } else {
            this.earliestVersion = logIndex.earliest();
            this.earliestId = logIndex.fetchVersionToId(earliestVersion);
        }
    }

    @Override
    final
    public Id versionToId(Version version) {
        if ( Objects.equals(currentVersion, version) )
            return currentId;
        return logIndex.fetchVersionToId(version);
    }

    //    @Override
    //    public  Version nextVersion() {}


    @Override
    final
    public Version idToVersion(Id id) {
        if ( Objects.equals(currentId, id) )
            return currentVersion;
        return logIndex.fetchPatchInfo(id).getVersion();
    }

    @Override
    final
    public LogEntry getPatchInfo(Id id) {
        // Get the three values inside a lock so they are consistent with "save".
        return runWithLockRtn(()->{
                if ( Objects.equals(currentId, id) )
                    return new LogEntry(currentId, currentVersion, previousId);
                return logIndex.fetchPatchInfo(id);
            });
    }

    @Override
    final
    public Version nextVersion() {
        return logIndex.genNextVersion();
    }

    @Override
    public void runWithLock(Runnable action) {
        synchronized (lock) {
            action.run();
        }
    }

    @Override
    public <X> X runWithLockRtn(Supplier<X> action) {
        synchronized(lock) {
            return action.get();
        }
    }

    @Override
    public boolean isEmpty() {
        //return version == DeltaConst.VERSION_UNSET || DeltaConst.VERSION_INIT;
        return currentId == null;
    }

    @Override
    final public void save(Version newVersion, Id newCurrent, Id newPrevious) {
        Objects.requireNonNull(newVersion);
        Objects.requireNonNull(newCurrent);
        synchronized(lock) {
            if ( Objects.equals(newVersion, currentVersion) )
                throw new DeltaException(
                    format("save: Attempt save state with current version %s", currentVersion));
            if ( newVersion.isBefore(currentVersion) )
                throw new DeltaException(
                    format("save: Attempt save state at version %s with older version %s", currentVersion, newVersion));
            if ( ! Objects.equals(this.currentId, newPrevious) )
                throw new DeltaException(
                    format("save: Attempt save state when current != new prev (%s %s)", currentId, newPrevious));
            logIndex.save(newVersion, newCurrent, newPrevious);
            this.currentVersion = newVersion;
            this.currentId = newCurrent;
            this.previousId = newPrevious;
            if ( earliestId == null ) {
                earliestId = newCurrent;
                earliestVersion = newVersion;
            }
        }
    }

    @Override
    final public Version getEarliestVersion() {
        return earliestVersion;
    }

    @Override
    final public Id getEarliestId() {
        return earliestId;
    }

    @Override
    final public Version getCurrentVersion() {
        return currentVersion;
    }

    @Override
    final public Id getCurrentId() {
        return currentId;
    }

    @Override
    final public Id getPreviousId() {
        return previousId;
    }

    @Override
    public void release() {}

    @Override
    public void delete() {}

    @Override
    public void syncVersionInfo() {}
}
