package graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.introcs.In;
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
}
