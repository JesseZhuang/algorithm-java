package graph;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeWeightedDirectedAllCyclesTest {
    @Test
    void testAllCyclesEWD() {
        EdgeWeightedDirectedAllCycles cycles = new EdgeWeightedDirectedAllCycles(new EdgeWeightedDigraph(
                new In("/graph/tinyEWD.txt")));
        assertEquals(6, cycles.cycles().size());
        cycles = new EdgeWeightedDirectedAllCycles(new EdgeWeightedDigraph(
                new In("/graph/EWD.cycle.txt")));
        assertEquals(1, cycles.cycles().size());
    }
}
