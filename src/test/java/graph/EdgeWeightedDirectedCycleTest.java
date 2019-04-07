package graph;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeWeightedDirectedCycleTest {
    @Test
    void testEWDG() {
        EdgeWeightedDirectedCycle cycle = new EdgeWeightedDirectedCycle(new EdgeWeightedDigraph(
                new In("/graph/EWD.cycle.txt")));
        assertEquals(Arrays.asList(4, 5, 2, 3), CollectionUtil.newArrayList(cycle.cycle()).stream()
                .map(e -> e.to()).collect(toList()));
        cycle = new EdgeWeightedDirectedCycle(new EdgeWeightedDigraph(
                new In("/graph/tinyEWD.txt")));
        assertEquals(Arrays.asList(3, 6, 4, 7), CollectionUtil.newArrayList(cycle.cycle()).stream()
                .map(e -> e.to()).collect(toList()));
    }
}
