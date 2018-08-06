package list;

import struct.ListNode;

/**
 * <p>
 * LeetCode 206. Easy.
 * <p>
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <pre>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * </pre>
 * <p>
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>iterative or recursive, O(N) time, O(1) space.
 * </ul>
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
        if (head == null || head.next == null)
            return head;
        // reverse the rest of the list
        ListNode rev = reverseListRecursive1(head.next);
        // add head to the tail
        head.next.next = head;
        // set tail to null,
        // otherwise head and tail are pointing at each other for next
        head.next = null;
        return rev;
    }

    public ListNode reverseListRecursive2(ListNode head) {
        return helper(head, null);
    }

    private ListNode helper(ListNode head, ListNode rev) {
        if (head == null)
            return rev;
        ListNode temp = rev;
        rev = head;
        head = head.next;
        rev.next = temp;
        return helper(head, rev);
    }
}
