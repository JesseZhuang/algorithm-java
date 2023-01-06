package princeton.jsl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class TopologicalTests {
    /**
     * test for branched tree DAG, e.g., 0->1->(2,3)
     */
    @Test
    void testBranchedTree() {
        Digraph dg = new Digraph(4);
        dg.addEdge(0, 1);
        dg.addEdge(1, 2);
        dg.addEdge(1, 3);
        Topological tbt = new Topological(dg);
        assertIterableEquals(Arrays.asList(0, 1, 3, 2), tbt.order()); // note order between 3,2 is arbitrary
    }
}