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
        TopologicalX tbt2 = new TopologicalX(dg);
        assertIterableEquals(Arrays.asList(0, 1, 2, 3), tbt2.order());

        dg = new Digraph(4);
        dg.addEdge(0, 1);
        dg.addEdge(1, 3);
        dg.addEdge(1, 2);
        tbt = new Topological(dg);
        assertIterableEquals(Arrays.asList(0, 1, 2, 3), tbt.order());
        tbt2 = new TopologicalX(dg);
        assertIterableEquals(Arrays.asList(0, 1, 3, 2), tbt2.order());
    }

    /**
     * 1->0 2->3
     */
    @Test
    void testDisconnected() {
        Digraph dg = new Digraph(4);
        dg.addEdge(1, 0);
        dg.addEdge(2, 3);
        Topological tbt = new Topological(dg); // dfs
        assertIterableEquals(Arrays.asList(2, 3, 1, 0), tbt.order()); // 2->3 not necessarily before 1->0
        TopologicalX tbt2 = new TopologicalX(dg); // bfs
        assertIterableEquals(Arrays.asList(1, 2, 0, 3), tbt2.order()); // 2 not necessarily between 1->0
    }
}