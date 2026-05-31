package list;

import list.CopyListWithRandomPointer.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopyListWithRandomPointerTest {

    // Helper: build list from values, randomIndices[i] is the index that node i's random points to (-1 for null)
    private static Node buildList(int[] vals, int[] randomIndices) {
        if (vals.length == 0) return null;
        Node[] nodes = new Node[vals.length];
        for (int i = 0; i < vals.length; i++) {
            nodes[i] = new Node(vals[i]);
        }
        for (int i = 0; i < vals.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        for (int i = 0; i < vals.length; i++) {
            nodes[i].random = randomIndices[i] == -1 ? null : nodes[randomIndices[i]];
        }
        return nodes[0];
    }

    // Helper: verify deep copy is correct and shares no nodes with original
    private static void assertDeepCopy(Node original, Node copy) {
        Node o = original, c = copy;
        // Collect original nodes for identity checks
        java.util.Set<Node> originals = new java.util.HashSet<>();
        Node tmp = original;
        while (tmp != null) {
            originals.add(tmp);
            tmp = tmp.next;
        }
        while (o != null) {
            assertNotNull(c);
            assertFalse(originals.contains(c), "Copy node must not be same object as original");
            assertEquals(o.val, c.val);
            if (o.random == null) {
                assertNull(c.random);
            } else {
                assertNotNull(c.random);
                assertEquals(o.random.val, c.random.val);
                assertFalse(originals.contains(c.random), "Copy random must not point to original");
            }
            o = o.next;
            c = c.next;
        }
        assertNull(c);
    }

    // --- LeetCode Example 1: [[7,null],[13,0],[11,4],[10,2],[1,0]] ---
    @Test
    void example1HashMap() {
        Node head = buildList(new int[]{7, 13, 11, 10, 1}, new int[]{-1, 0, 4, 2, 0});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListHashMap(head));
    }

    @Test
    void example1Interleave() {
        Node head = buildList(new int[]{7, 13, 11, 10, 1}, new int[]{-1, 0, 4, 2, 0});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListInterleave(head));
    }

    // --- LeetCode Example 2: [[1,1],[2,1]] ---
    @Test
    void example2HashMap() {
        Node head = buildList(new int[]{1, 2}, new int[]{1, 1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListHashMap(head));
    }

    @Test
    void example2Interleave() {
        Node head = buildList(new int[]{1, 2}, new int[]{1, 1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListInterleave(head));
    }

    // --- LeetCode Example 3: [[3,null],[3,0],[3,null]] ---
    @Test
    void example3HashMap() {
        Node head = buildList(new int[]{3, 3, 3}, new int[]{-1, 0, -1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListHashMap(head));
    }

    @Test
    void example3Interleave() {
        Node head = buildList(new int[]{3, 3, 3}, new int[]{-1, 0, -1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListInterleave(head));
    }

    // --- Empty list ---
    @Test
    void emptyListHashMap() {
        assertNull(CopyListWithRandomPointer.copyRandomListHashMap(null));
    }

    @Test
    void emptyListInterleave() {
        assertNull(CopyListWithRandomPointer.copyRandomListInterleave(null));
    }

    // --- Single node with no random ---
    @Test
    void singleNodeNoRandomHashMap() {
        Node head = buildList(new int[]{42}, new int[]{-1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListHashMap(head));
    }

    @Test
    void singleNodeNoRandomInterleave() {
        Node head = buildList(new int[]{42}, new int[]{-1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListInterleave(head));
    }

    // --- Single node with self-random ---
    @Test
    void singleNodeSelfRandomHashMap() {
        Node head = buildList(new int[]{5}, new int[]{0});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListHashMap(head));
    }

    @Test
    void singleNodeSelfRandomInterleave() {
        Node head = buildList(new int[]{5}, new int[]{0});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListInterleave(head));
    }

    // --- All random pointers point to last node ---
    @Test
    void allRandomToLastHashMap() {
        Node head = buildList(new int[]{1, 2, 3, 4}, new int[]{3, 3, 3, 3});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListHashMap(head));
    }

    @Test
    void allRandomToLastInterleave() {
        Node head = buildList(new int[]{1, 2, 3, 4}, new int[]{3, 3, 3, 3});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListInterleave(head));
    }

    // --- Negative values ---
    @Test
    void negativeValuesHashMap() {
        Node head = buildList(new int[]{-1, -5000, 0, 10000}, new int[]{1, 0, 3, -1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListHashMap(head));
    }

    @Test
    void negativeValuesInterleave() {
        Node head = buildList(new int[]{-1, -5000, 0, 10000}, new int[]{1, 0, 3, -1});
        assertDeepCopy(head, CopyListWithRandomPointer.copyRandomListInterleave(head));
    }
}
