package graph;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeWeightedDigraphTest {

    EdgeWeightedDigraph digraph;

    @BeforeEach
    void setup() {
        // note these text file cannot have a blank line at the end
        In in = new In("/graph/tinyEWD.txt");
        digraph = new EdgeWeightedDigraph(in);
    }

    @Test
    void testReadFromTxt() {
        assertEquals(8, digraph.V());
        assertEquals(15, digraph.E());
        assertEquals(15, StreamSupport.stream(digraph.edges().spliterator(), false).count());
    }

    @Test
    void testAddEdge() {
        DirectedEdge edge = new DirectedEdge(1, 3, 0.42);
        digraph.addEdge(edge);
        assertEquals(16, digraph.E());
        Iterable<DirectedEdge> edges = digraph.adj(1);
        assertEquals(2, StreamSupport.stream(edges.spliterator(), false).count());
    }

    @Test
    void testAdj() {
        int fromVertex = 0;
        Iterable<DirectedEdge> edges = digraph.adj(fromVertex);
        int[] w = new int[]{2, 4};
        double[] weights = new double[]{0.26, 0.38};
        int count = 0;
        Iterator<DirectedEdge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            DirectedEdge edge = iterator.next();
            assertEquals(fromVertex, edge.from());
            assertEquals(w[count], edge.to());
            assertEquals(weights[count], edge.weight());
            count++;
        }
    }

    @Test
    void testOutDegree() {
        assertEquals(3, digraph.outdegree(6));
    }
}
