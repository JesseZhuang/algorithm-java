package list;

import struct.ListNode;

/**
 * <p>
 * LeetCode 206. Easy. Tags: LinkedList, recursion.
 * <p>
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <pre>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * </pre>
 * Constraints:
 * <p>
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

    public ListNode reverseListIterative(ListNode head) {
        if (head == null)
            return null;
        ListNode prev = null;
        while (head.next != null) {
            ListNode temp = prev;
            prev = head;
            head = head.next;
            prev.next = temp;
        }
        head.next = prev;
        return head;
    }

    // best: 0ms 42.4MB. O(N) time, O(1) space.
    public ListNode reverseListIterative2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    public ListNode reverseListRecursive1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode rev = reverseListRecursive1(head.next); // reverse the rest of the list
        head.next.next = head; // head.next now points to the tail, put head after that
        head.next = null; // tail.next is head and head.next is tail, set head.next to null to break the cycle
        return rev;
    }

    public ListNode reverseListRecursive2(ListNode head) {
        return helper(head, null);
    }

    private ListNode helper(ListNode head, ListNode rev) {
        if (head == null)
            return rev;
        ListNode temp = rev; // rev points the start of already reversed
        rev = head; // move rev forward
        head = head.next; // move head forward
        rev.next = temp; // modify rev.next to point to the already reversed, saved in temp
        return helper(head, rev); // tail recursion
    }
}
