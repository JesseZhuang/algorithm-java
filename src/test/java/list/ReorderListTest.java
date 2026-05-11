package list;

import org.junit.jupiter.api.Test;
import struct.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReorderListTest {

    private final ReorderList sol = new ReorderList();

    private static int[] toArray(ListNode head) {
        int size = head == null ? 0 : head.size();
        int[] res = new int[size];
        ListNode cur = head;
        for (int i = 0; i < size; i++) {
            res[i] = cur.val;
            cur = cur.next;
        }
        return res;
    }

    private void verify(int[] input, int[] expected) {
        ListNode head = ListNode.createFromArray(input);
        sol.reorderList(head);
        ListNode expectedNode = ListNode.createFromArray(expected);
        assertEquals(expectedNode, head);
    }

    @Test
    void testEvenLength() {
        verify(new int[]{1, 2, 3, 4}, new int[]{1, 4, 2, 3});
    }

    @Test
    void testOddLength() {
        verify(new int[]{1, 2, 3, 4, 5}, new int[]{1, 5, 2, 4, 3});
    }

    @Test
    void testSingleNode() {
        verify(new int[]{1}, new int[]{1});
    }

    @Test
    void testTwoNodes() {
        verify(new int[]{1, 2}, new int[]{1, 2});
    }

    @Test
    void testThreeNodes() {
        verify(new int[]{1, 2, 3}, new int[]{1, 3, 2});
    }

    @Test
    void testSixNodes() {
        verify(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 6, 2, 5, 3, 4});
    }
}
