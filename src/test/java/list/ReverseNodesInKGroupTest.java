package list;

import org.junit.jupiter.api.Test;
import struct.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReverseNodesInKGroupTest {

    private ListNode build(int... vals) {
        return ListNode.createFromArray(vals);
    }

    // [1,2,3,4,5] k=2 -> [2,1,4,3,5]
    @Test
    void testK2Iterative() {
        assertEquals(build(2, 1, 4, 3, 5),
                ReverseNodesInKGroup.reverseKGroupIterative(build(1, 2, 3, 4, 5), 2));
    }

    @Test
    void testK2Recursive() {
        assertEquals(build(2, 1, 4, 3, 5),
                ReverseNodesInKGroup.reverseKGroupRecursive(build(1, 2, 3, 4, 5), 2));
    }

    // [1,2,3,4,5] k=3 -> [3,2,1,4,5]
    @Test
    void testK3Iterative() {
        assertEquals(build(3, 2, 1, 4, 5),
                ReverseNodesInKGroup.reverseKGroupIterative(build(1, 2, 3, 4, 5), 3));
    }

    @Test
    void testK3Recursive() {
        assertEquals(build(3, 2, 1, 4, 5),
                ReverseNodesInKGroup.reverseKGroupRecursive(build(1, 2, 3, 4, 5), 3));
    }

    // k=1 (no change)
    @Test
    void testK1Iterative() {
        assertEquals(build(1, 2, 3, 4, 5),
                ReverseNodesInKGroup.reverseKGroupIterative(build(1, 2, 3, 4, 5), 1));
    }

    @Test
    void testK1Recursive() {
        assertEquals(build(1, 2, 3, 4, 5),
                ReverseNodesInKGroup.reverseKGroupRecursive(build(1, 2, 3, 4, 5), 1));
    }

    // k=length (full reverse)
    @Test
    void testKEqualsLengthIterative() {
        assertEquals(build(5, 4, 3, 2, 1),
                ReverseNodesInKGroup.reverseKGroupIterative(build(1, 2, 3, 4, 5), 5));
    }

    @Test
    void testKEqualsLengthRecursive() {
        assertEquals(build(5, 4, 3, 2, 1),
                ReverseNodesInKGroup.reverseKGroupRecursive(build(1, 2, 3, 4, 5), 5));
    }

    // single node
    @Test
    void testSingleNodeIterative() {
        assertEquals(build(1),
                ReverseNodesInKGroup.reverseKGroupIterative(build(1), 1));
    }

    @Test
    void testSingleNodeRecursive() {
        assertEquals(build(1),
                ReverseNodesInKGroup.reverseKGroupRecursive(build(1), 1));
    }

    // k > length (no change)
    @Test
    void testKGreaterThanLengthIterative() {
        assertEquals(build(1, 2, 3),
                ReverseNodesInKGroup.reverseKGroupIterative(build(1, 2, 3), 5));
    }

    @Test
    void testKGreaterThanLengthRecursive() {
        assertEquals(build(1, 2, 3),
                ReverseNodesInKGroup.reverseKGroupRecursive(build(1, 2, 3), 5));
    }

    // empty list (null)
    @Test
    void testNullIterative() {
        assertNull(ReverseNodesInKGroup.reverseKGroupIterative(null, 2));
    }

    @Test
    void testNullRecursive() {
        assertNull(ReverseNodesInKGroup.reverseKGroupRecursive(null, 2));
    }

    // [1,2,3,4,5,6] k=3 -> [3,2,1,6,5,4]
    @Test
    void testK3EvenGroupsIterative() {
        assertEquals(build(3, 2, 1, 6, 5, 4),
                ReverseNodesInKGroup.reverseKGroupIterative(build(1, 2, 3, 4, 5, 6), 3));
    }

    @Test
    void testK3EvenGroupsRecursive() {
        assertEquals(build(3, 2, 1, 6, 5, 4),
                ReverseNodesInKGroup.reverseKGroupRecursive(build(1, 2, 3, 4, 5, 6), 3));
    }
}
