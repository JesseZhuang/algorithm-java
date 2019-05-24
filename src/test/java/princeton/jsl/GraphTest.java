package princeton.jsl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {
    @Test
    void testGraph() {
        Integer[][] edges = new Integer[][]{{0, 1}, {0, 2}};
        Graph g = new Graph(edges);
        assertEquals(3, g.V());
        assertEquals(2, g.E());
        assertEquals(Arrays.asList(1, 2), g.adj(0));
    }
}
