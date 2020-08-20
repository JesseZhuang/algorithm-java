package graph;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import edu.princeton.cs.algs4.Bipartite;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import static org.junit.jupiter.api.Assertions.*;
import static util.CollectionUtil.toList;

class BipartiteTest {
    Bipartite toBeTested;

    @Test
    void testDisconnectedGraph() {
        Graph graph = GraphGenerator.simple(6, 2);
        System.out.println(graph);
        toBeTested = new Bipartite(GraphGenerator.simple(6, 2));
        assertTrue(toBeTested.isBipartite());
    }

    @Test
    void testNotBipartite() {
        Graph graph = new Graph(6);
        graph.addEdge(0,1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        toBeTested = new Bipartite(graph);
        assertFalse(toBeTested.isBipartite());
        assertEquals(Ints.asList(0, 2, 1, 0), CollectionUtil.toList(toBeTested.oddCycle()));
    }

}
