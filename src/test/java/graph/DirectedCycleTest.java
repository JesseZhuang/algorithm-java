package graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DirectedCycleTest {
    @Test
    void testDGWithCycle() {
        Digraph dg = new Digraph(new In("/graph/tinyDG.txt"));
        DirectedCycle dc = new DirectedCycle(dg);
        assertEquals(Arrays.asList(3, 5, 4, 3), CollectionUtil.newArrayList(dc.cycle()));
    }

    @Test
    void testDAG() {
        DirectedCycle dc = new DirectedCycle(new Digraph(new In("/graph/tinyDAG.txt")));
        assertFalse(dc.hasCycle());
    }
}
