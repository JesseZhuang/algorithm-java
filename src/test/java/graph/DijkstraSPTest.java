package graph;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraSPTest {
    @Test
    void testCyclic() {
        DijkstraSP sp = new DijkstraSP(new EdgeWeightedDigraph(new In("/graph/EWD.cycle.txt")), 2);
        assertEquals(Arrays.asList(3, 4, 5, 7, 0), CollectionUtil.newArrayList(sp.pathTo(0)).stream()
                .map(e -> e.to()).collect(Collectors.toList()));
        assertEquals(0.68, sp.distTo(0));
    }
}
