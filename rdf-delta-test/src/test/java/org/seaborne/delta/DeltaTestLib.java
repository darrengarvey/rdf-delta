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

package org.seaborne.delta;

import org.apache.jena.atlas.lib.FileOps;
import org.apache.jena.tdb.base.file.Location;

public class DeltaTestLib {
    public static Location SourceArea = Location.create("target/test/sources");
    public static Location PatchArea = Location.create("target/test/patches");
    
    public static void resetTestAreas() {
        ensureClear(SourceArea.getDirectoryPath()); 
        ensureClear(PatchArea.getDirectoryPath());
    }
    
    private static void ensureClear(String area) {
        FileOps.ensureDir(area);
        FileOps.clearDirectory(area);
    }
}
