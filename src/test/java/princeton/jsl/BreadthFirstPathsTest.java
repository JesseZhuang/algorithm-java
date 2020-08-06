package princeton.jsl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BreadthFirstPathsTest {

    @Test
    void testMultipleSources() {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(2, 1);
        g.addEdge(3, 4);

        BreadthFirstPaths bfp = new BreadthFirstPaths(g, ImmutableList.of(0, 3));
        assertTrue(bfp.hasPathTo(2));
        assertEquals(ImmutableList.of(0, 1, 2), Lists.newArrayList(bfp.pathTo(2)));
        assertEquals(Integer.MAX_VALUE, bfp.distTo(5));

        princeton.jsl.Graph g2 = new princeton.jsl.Graph(6);
        g2.addEdge(0, 1);
        g2.addEdge(2, 1);
        g2.addEdge(3, 4);

        princeton.jsl.BreadthFirstPaths bfp2 = new princeton.jsl.BreadthFirstPaths(g2, ImmutableList.of(0, 3));
        assertTrue(bfp2.hasPathTo(2));
        assertEquals(ImmutableList.of(0, 1, 2), Lists.newArrayList(bfp2.pathTo(2)));
        assertEquals(Integer.MAX_VALUE, bfp2.distTo(5));
    }
}
