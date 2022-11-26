package princeton.jsl;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeWeightedDigraphTest {
    private In in;

    @BeforeEach
    void setup() {
        //ClassLoader classLoader = EdgeWeightedDigraphTest.class.getClassLoader();
        //in = new In(new Scanner(classLoader.getResourceAsStream("graph/1000EWD.txt")));
        in = new In("/graph/1000EWD.txt");
    }

    @Test
    void testReadFromFile() {
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(in);
        assertEquals(16866, digraph.E());
    }

    @Test
    void testPrinceton4Digraph() {
        edu.princeton.cs.algs4.EdgeWeightedDigraph graph = new edu.princeton.cs.algs4.EdgeWeightedDigraph(in);
        assertEquals(16866, graph.E());
        assertEquals(1000, graph.V());
    }
}
