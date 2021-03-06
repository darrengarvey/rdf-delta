/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  See the NOTICE file distributed with this work for additional
 *  information regarding copyright ownership.
 */

package org.seaborne.patch.system;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.core.Quad;
import org.seaborne.patch.RDFChanges;

// UNFINISHED

/** A {@link DatasetGraph} implementation that buffers adds/deletes and prefix changes
 * then plays them to another {@link DatasetGraph} or to a {@link RDFChanges}.
 * <p>
 * This has the capacity to add {@link DatasetGraph#abort} functionality to a {@link DatasetGraph}
 * that does not provide it.
 */
public class DatasetGraphBuffering extends AbstractDatasetGraphAddDelete {

    // Structurally safe but not thread safe.
    private Set<Quad> addQuads      = ConcurrentHashMap.newKeySet();
    private Set<Quad> deleteQuads   = ConcurrentHashMap.newKeySet();
    // Necessary to check to avoid duplicate suppression in find*
    private boolean checking = true;

    public DatasetGraphBuffering(DatasetGraph dsg) {
        super(dsg);
    }

    @Override
    protected void actionAdd(Node g, Node s, Node p, Node o) {
        // add/delete (Quad) creates churn.
        if ( checking && get().contains(g,s,p,o) )
            return;
        Quad quad = Quad.create(g, s, p, o);
        if ( addQuads.add(quad) )
            deleteQuads.remove(quad);
    }

    @Override
    protected void actionDelete(Node g, Node s, Node p, Node o) {
        if ( checking && ! get().contains(g,s,p,o) )
            return;
        Quad quad = Quad.create(g, s, p, o);
        if ( deleteQuads.add(quad) )
            addQuads.remove(quad);
    }

    @Override
    public Iterator<Quad> find(Node g, Node s, Node p, Node o) {
        // Original, without deletes
        Iterator<Quad> iter1 = Iter.filter(get().find(g, s, p, o), (q)->!deleteQuads.contains(q) );
        // plus adds (which do not contain deletes)
        Iterator<Quad> iter2 = addQuads.stream().filter(q->Match.match(q, g, s, p, o)).iterator();
        return Iter.concat(iter1,iter2);
    }

//    isEmpty()
//    find()
//    find(Quad)
//    find(Node, Node, Node, Node)
//    findNG(Node, Node, Node, Node)
//    contains(Quad)
//    contains(Node, Node, Node, Node)
//    size()

    private void flushBuffering() {
        for ( Quad q : deleteQuads )
            get().delete(q);
        for ( Quad q : addQuads )
            get().add(q);
        deleteQuads.clear();
        addQuads.clear();
    }

    // Transactions trigger flushBuffering()

    // If DatasetGraphWrapper
//    @Override
//    public void add(Quad quad)
//    { get().add(quad) ; }
//
//    @Override
//    public void delete(Quad quad)
//    { get().delete(quad) ; }
//
//    @Override
//    public void add(Node g, Node s, Node p, Node o)
//    { get().add(g, s, p, o) ; }
//
//    @Override
//    public void delete(Node g, Node s, Node p, Node o)
//    { get().delete(g, s, p, o) ; }
//
//    @Override
//    public void deleteAny(Node g, Node s, Node p, Node o)
//    { get().deleteAny(g, s, p, o) ; }
//
//    @Override
//    public void clear()
//    { get().clear() ; }


}
