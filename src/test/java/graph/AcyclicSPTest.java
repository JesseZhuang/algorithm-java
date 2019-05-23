package graph;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class AcyclicSPTest {

    EdgeWeightedDigraph digraph;

    @Test
    void testCyclicGraph() {
        In in = new In("/graph/EWD.cycle.txt");
        digraph = new EdgeWeightedDigraph(in);
        assertThrows(IllegalArgumentException.class, () -> new AcyclicSP(digraph, 1));
    }

    @Test
    void testTinyEWDAG() {
        In in = new In("/graph/tinyEWDAG.txt");
        AcyclicSP acyclicSP = new AcyclicSP(new EdgeWeightedDigraph(in), 5);
        assertTrue(acyclicSP.hasPathTo(6));
        assertEquals(1.13, acyclicSP.distTo(6));
        // PrimitiveIterator.OfInt vertices = Arrays.stream(new int[]{1, 3, 6}).iterator();
        Iterator<Integer> vertices = Arrays.asList(1, 3, 6).iterator();
        for (DirectedEdge e : acyclicSP.pathTo(6))
            assertEquals(vertices.next().intValue(), e.to());
    }

    @Test
    void testNegativeWeight() {
        AcyclicSP acyclicSP = new AcyclicSP(new EdgeWeightedDigraph(new In("/graph/EWDAG.negative.txt")), 0);
        assertTrue(acyclicSP.hasPathTo(5));
        assertNotEquals(0.043 + 0.072, 0.115); // double calculation error
        assertEquals(-0.135, acyclicSP.distTo(5), 0.00001);
        Iterator<Integer> vertices = Arrays.asList(3, 6, 2, 4, 1, 5).iterator();
        for (DirectedEdge e : acyclicSP.pathTo(5))
            assertEquals(vertices.next().intValue(), e.to());
    }
}
