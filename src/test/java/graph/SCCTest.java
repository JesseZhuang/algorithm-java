package graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.GabowSCC;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Tarjan, Gabow, KosarajuSharir SCC.
 * <p>
 * Gabow's and Tarjan's algorithms are desirable in practice since they require only one depth-first search
 * instead of two. However, Kosaraju's algorithm is simple in implementation.
 */
public class SCCTest {

    Digraph digraph1;

    @BeforeEach
    void setup() {
        digraph1 = new Digraph(6);
        digraph1.addEdge(0, 1); // cycle 0,1,2,0
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 0);
        digraph1.addEdge(3, 4); // 3->4, not strongly connected
    }

    @Test
    void testDAG() {
        KosarajuSharirSCC scc = new KosarajuSharirSCC(new Digraph(new In("/graph/tinyDG.txt")));
        List<List<Integer>> components = new ArrayList<>();
        components.add(Arrays.asList(1));
        components.add(Arrays.asList(0, 2, 3, 4, 5));
        components.add(Arrays.asList(9, 10, 11, 12));
        components.add(Arrays.asList(6, 8));
        components.add(Arrays.asList(7));
        assertEquals(components, SCC.components(scc, 13));
    }

    @Test
    void testGabowSCC() { // add debug point in GabowSCC to see how it works
        GabowSCC scc = new GabowSCC(digraph1);
        List<List<Integer>> components = new ArrayList<>();
        components.add(Arrays.asList(0, 1, 2));
        components.add(Arrays.asList(4));
        components.add(Arrays.asList(3));
        components.add(Arrays.asList(5));
        assertEquals(components, SCC.components(scc, 6));
    }
}
