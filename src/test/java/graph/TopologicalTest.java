package graph;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SymbolDigraph;
import edu.princeton.cs.algs4.Topological;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DFS. Reverse post order.
 */
public class TopologicalTest {

    private Topological topological;

    @Test
    void testTopologicalSort() {
        topological = new Topological(new EdgeWeightedDigraph(new In("/graph/tinyEWDAG.txt")));
        assertTrue(topological.hasOrder());
        assertEquals(Arrays.asList(5, 1, 3, 6, 4, 7, 0, 2), CollectionUtil.newArrayList(topological.order()));
    }

    @Test
    void testCycle() {
        topological = new Topological(new EdgeWeightedDigraph(new In("/graph/tinyEWD.txt")));
        assertFalse(topological.hasOrder());
    }

    @Test
    void testSymbolGraph() {
        List<String> order = Arrays.asList("Calculus", "Linear Algebra", "Introduction to CS", "Advanced Programming",
                "Algorithms", "Theoretical CS", "Artificial Intelligence", "Robotics", "Machine Learning",
                "Neural Networks", "Databases", "Scientific Computing", "Computational Biology");
        SymbolDigraph sg = new SymbolDigraph("/graph/jobs.txt", "/");
        topological = new Topological(sg.G());
        assertEquals(order, CollectionUtil.toList(topological.order()).stream()
                .map(v -> sg.name(v)).collect(Collectors.toList()));
    }

    @Test
    void testBranchedGraph() {
        topological = new Topological(new EdgeWeightedDigraph(new In("/graph/tinyEWDAG.2branch.01.txt")));
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), CollectionUtil.toList(topological.order()));
    }
}
