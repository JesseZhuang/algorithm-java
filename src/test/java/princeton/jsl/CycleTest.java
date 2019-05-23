package princeton.jsl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CycleTest {
    @Test
    void testNoCycle() {
        Cycle cycle = new Cycle(new Graph(new int[][]{{0, 1}}));
        assertFalse(cycle.hasCycle());
    }

    @Test
    void testSelfLoop() {
        Cycle cycle = new Cycle(new Graph(new int[][]{{0, 0}}));
        assertTrue(cycle.hasCycle());
        assertEquals(Arrays.asList(0, 0), cycle.cycle());
    }

    @Test
    void testParallelEdges() {
        Graph graph = new Graph(new int[][]{{0, 1}, {0, 1}});
        assertEquals(2, graph.degree(0));
        Cycle cycle = new Cycle(graph);
        assertEquals(Arrays.asList(0, 1, 0), cycle.cycle());
    }

    @Test
    void testGraphWithCycle() {
        Cycle cycle = new Cycle(new Graph(new int[][]{{0, 1}, {1, 2}, {2, 0}}));
        assertTrue(cycle.hasCycle());
        assertEquals(Arrays.asList(2, 1, 0, 2), cycle.cycle());
    }
}
