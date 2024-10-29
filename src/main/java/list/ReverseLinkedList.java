package list;

import struct.ListNode;

/**
 * <p>
 * LeetCode 206, easy, tags: LinkedList, recursion.
 * Companies: Hertz.
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

    // best: 0ms 42.4MB. O(N) time, O(1) space.
    public ListNode reverseListIterative2(ListNode head) {
        ListNode res = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = res;
            res = head;
            head = next;
        }
        return res;
    }

    public ListNode reverseListRecursive1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode rev = reverseListRecursive1(head.next); // reverse the rest of the list
        head.next.next = head; // head.next now points to the tail, put head after that
        head.next = null; // tail.next is head and head.next is tail, set head.next to null to break the cycle
        return rev;
    }
}
