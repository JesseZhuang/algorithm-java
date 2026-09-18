package list;

import org.junit.jupiter.api.Test;
import struct.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumTwinSumLinkedListTest {

    private void verify(int[] input, int expected) {
        assertEquals(expected, MaximumTwinSumLinkedList.pairSum(ListNode.createFromArray(input)));
        assertEquals(expected, MaximumTwinSumLinkedList.pairSumStack(ListNode.createFromArray(input)));
    }

    @Test
    void testExample1() {
        verify(new int[]{5, 4, 2, 1}, 6);
    }

    @Test
    void testExample2() {
        verify(new int[]{4, 2, 2, 3}, 7);
    }

    @Test
    void testExample3() {
        verify(new int[]{1, 100000}, 100001);
    }

    @Test
    void testMinLength() {
        verify(new int[]{1, 1}, 2);
    }

    @Test
    void testAllEqual() {
        verify(new int[]{5, 5, 5, 5}, 10);
    }

    @Test
    void testMirror() {
        verify(new int[]{100, 1, 1, 100}, 200);
    }

    @Test
    void testSixNodes() {
        verify(new int[]{1, 2, 3, 4, 5, 6}, 7);
    }
}
