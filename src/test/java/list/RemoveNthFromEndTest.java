package list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import struct.ListNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RemoveNthFromEndTest {
    private static RemoveNthFromEnd solution;

    @BeforeAll
    static void setup() {
        solution = new RemoveNthFromEnd();
    }

    @Test
    void testRemoveNthFromEnd_fiveElements_removeSecond() {
        ListNode head = buildList(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{1, 2, 3, 5}, toArray(result));
    }

    @Test
    void testRemoveNthFromEnd_singleElement_removeFirst() {
        ListNode head = buildList(new int[]{1});
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertNull(result);
    }

    @Test
    void testRemoveNthFromEnd_twoElements_removeLast() {
        ListNode head = buildList(new int[]{1, 2});
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertArrayEquals(new int[]{1}, toArray(result));
    }

    @Test
    void testRemoveNthFromEnd_twoElements_removeFirst() {
        ListNode head = buildList(new int[]{1, 2});
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{2}, toArray(result));
    }

    @Test
    void testRemoveNthFromEnd_threeElements_removeSecond() {
        ListNode head = buildList(new int[]{1, 2, 3});
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{1, 3}, toArray(result));
    }

    @Test
    void testRemoveNth_fiveElements_removeSecond() {
        ListNode head = buildList(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.removeNth(head, 2);
        assertArrayEquals(new int[]{1, 2, 3, 5}, toArray(result));
    }

    @Test
    void testRemoveNth_singleElement_removeFirst() {
        ListNode head = buildList(new int[]{1});
        ListNode result = solution.removeNth(head, 1);
        assertNull(result);
    }

    @Test
    void testRemoveNth_twoElements_removeLast() {
        ListNode head = buildList(new int[]{1, 2});
        ListNode result = solution.removeNth(head, 1);
        assertArrayEquals(new int[]{1}, toArray(result));
    }

    @Test
    void testRemoveNth_twoElements_removeFirst() {
        ListNode head = buildList(new int[]{1, 2});
        ListNode result = solution.removeNth(head, 2);
        assertArrayEquals(new int[]{2}, toArray(result));
    }

    @Test
    void testRemoveNth_threeElements_removeSecond() {
        ListNode head = buildList(new int[]{1, 2, 3});
        ListNode result = solution.removeNth(head, 2);
        assertArrayEquals(new int[]{1, 3}, toArray(result));
    }

    private static ListNode buildList(int[] vals) {
        if (vals == null || vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode curr = head;
        for (int i = 1; i < vals.length; i++) {
            curr.next = new ListNode(vals[i]);
            curr = curr.next;
        }
        return head;
    }

    private static int[] toArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
