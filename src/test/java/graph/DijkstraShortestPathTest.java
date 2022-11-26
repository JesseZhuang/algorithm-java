package graph;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DijkstraShortestPathTest {
    private In in1;
    private In in2;

    @BeforeEach
    void setup() {
        in1 = new In("/graph/1000EWD.txt");
        in2 = new In("/graph/1000EWD.txt");
    }

    @Test
    void testPrincetonSameToPQVersion() {
        DijkstraSP sp1 = new DijkstraSP(new EdgeWeightedDigraph(in1), 0);
        DijkstraShortestPath sp2 = new DijkstraShortestPath(new princeton.jsl.EdgeWeightedDigraph(in2), 0);
        for (int v = 0; v < 1000; v++) {
            // assertEquals(sp1.hasPathTo(v), sp2.hasPathTo(v));
            if (sp1.distTo(v) != sp2.distTo(v)) {
                System.out.println("distTo: " + v);
                System.out.println(sp1.distTo(v));
                System.out.println(sp2.distTo(v));
            }
        }
    }
}
