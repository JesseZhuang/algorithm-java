package graph;

import edu.princeton.cs.algs4.AcyclicLP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Algorithm: relaxation when found a longer path.
 */
public class AcyclicLPTest {
    @Test
    void testTinyEWDAG() {
        AcyclicLP acyclicLP = new AcyclicLP(new EdgeWeightedDigraph(new In("/graph/tinyEWDAG.txt")), 5);
        assertEquals(2.43, acyclicLP.distTo(7));
        assertEquals(Arrays.asList(1, 3, 6, 4, 7),
                CollectionUtil.toList(acyclicLP.pathTo(7)).stream().map(e -> e.to()).collect(toList()));
    }

    @Test
    void testNegativeWeight() {
        AcyclicLP acyclicLP = new AcyclicLP(new EdgeWeightedDigraph(new In("/graph/EWDAG.negative.txt")), 0);
        assertEquals(0.02, acyclicLP.distTo(5), 0.00001);
        assertEquals(Arrays.asList(6, 1, 5),
                CollectionUtil.toList(acyclicLP.pathTo(5)).stream().map(e -> e.to()).collect(toList()));
    }
}
