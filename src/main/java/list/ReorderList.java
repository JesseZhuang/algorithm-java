package list;

import struct.ListNode;

/**
 * LeetCode 143, medium, tags: linked list, two pointers, stack, recursion.
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/03/04/reorder1linked-list.jpg
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * Example 2: https://assets.leetcode.com/uploads/2021/03/09/reorder2-linked-list.jpg
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 5 * 10^4].
 * 1 <= Node.val <= 1000
 */
public class ReorderList {
    // 1ms, 45.3 MB. O(N) time, O(1) space.
    public void reorderList(ListNode head) {
        // if (head == null) return;
        ListNode slow = head, fast = head, head1 = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } // at the end slow will be before the section that needs to be reversed
        ListNode headToReverse = slow.next, head2 = null;
        slow.next = null;
        while (headToReverse != null) { // reverse the second half, 12345 -> 123;54
            ListNode temp = headToReverse.next;
            headToReverse.next = head2;
            head2 = headToReverse;
            headToReverse = temp;
        }
        while (head2 != null) {// zig zag funny merge, not as easy to remember
//            ListNode temp = head1.next;
//            head1.next = head2;
//            head1 = head2;
//            head2 = temp;

            ListNode temp1 = head1.next, temp2 = head2.next;
            head1.next = head2;
            head2.next = temp1;
            head1 = temp1;
            head2 = temp2;
        }
    }
}
