package list;

import struct.ListNode;

/**
 * LeetCode 19, medium, tags: linked list, two pointers.
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg
 * <p>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * <p>
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * Follow up: Could you do this in one pass?
 * Hint1: Maintain two pointers and update one with a delay of n steps.
 */
public class RemoveNthFromEnd {
    // 0ms 40.2 MB. O(N) time, O(1) space.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // boundary: nth could be head or head.next
        dummy.next = head;
        ListNode cur = head.next, nth = dummy;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
            if (count >= n) nth = nth.next; // n+1 not n
        }
        nth.next = nth.next.next; // size >= 1, no need null check, nth.next cannot be null
        return dummy.next;
    }

    public ListNode removeNth(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 1; i <= n + 1; i++) fast = fast.next; // careful how many positions to move fast
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}