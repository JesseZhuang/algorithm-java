package graph;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstOrderTest {

    @Test
    void testTinyDAG() {
        Digraph dg = new Digraph(new In("/graph/tinyDAG.txt"));
        for (int v : dg.adj(0)) System.out.println(v);
        DepthFirstOrder dfs = new DepthFirstOrder(dg);
        // order from the bag for adj(v) depends on bag implementation and order edges was read in
        assertEquals(Arrays.asList(0, 5, 4, 1, 6, 9, 11, 12, 10, 2, 3, 7, 8), CollectionUtil.toList(dfs.pre()));
    }
}
