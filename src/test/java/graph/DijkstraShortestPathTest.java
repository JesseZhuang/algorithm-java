package graph;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        for (int v = 0; v < 1000; v++) assertEquals(sp1.hasPathTo(v), sp2.hasPathTo(v));
    }

    @Test
    void testPQVersion() {
        DijkstraShortestPath sp = new DijkstraShortestPath(new princeton.jsl.EdgeWeightedDigraph(
                new In("/graph/EWD.cycle.txt")), 2);
        assertEquals(Arrays.asList(3, 4, 5, 7, 0), CollectionUtil.newArrayList(sp.pathTo(0)).stream()
                .map(e -> e.to()).collect(Collectors.toList()));
        assertEquals(0.68, sp.distTo(0));
    }

    @Test
    void testPQVersionRelaxedRoute() {
        DijkstraShortestPath sp = new DijkstraShortestPath(new princeton.jsl.EdgeWeightedDigraph(
                new In("/graph/EWD.cycle.txt")), 1);
        assertEquals(0.14, sp.distTo(3));
        assertEquals(Arrays.asList(2, 3), CollectionUtil.newArrayList(sp.pathTo(3)).stream()
                .map(e -> e.to()).collect(Collectors.toList()));
        assertEquals(0.5, sp.distTo(4));
        assertEquals(Arrays.asList(2, 3, 4), CollectionUtil.newArrayList(sp.pathTo(4)).stream()
                .map(e -> e.to()).collect(Collectors.toList()));
    }

    @Test
    void testWithRandomGraph() {
        for(int i = 0; i < 100; i++) {
            princeton.jsl.EdgeWeightedDigraph graph = new princeton.jsl.EdgeWeightedDigraph(100, 8630);
            EdgeWeightedDigraph graph2 = new EdgeWeightedDigraph(100);
            for (DirectedEdge e : graph.edges()) graph2.addEdge(
                    new edu.princeton.cs.algs4.DirectedEdge(e.from(), e.to(), e.weight()));
            DijkstraShortestPath sp = new DijkstraShortestPath(graph, 2);
            DijkstraSP sp2 = new DijkstraSP(graph2, 2);
            for (int j = 0; j < 100; j++) {
                assertEquals(sp2.hasPathTo(j), sp.hasPathTo(j));
                assertEquals(sp2.distTo(j), sp.distTo(j));
            }
        }
    }
}
