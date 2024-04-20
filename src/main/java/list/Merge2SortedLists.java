package list;

import struct.ListNode;

/**
 * LeetCode 21. Easy.
 * <p>
 * Merge two sorted linked lists and return it as a new list. The new list should be made by
 * splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>O(N) time, Iterative O(1) space, Recursive O(N) stack space, may stack overflow.
 * </ul>
 */
public class Merge2SortedLists {

    // solution 1, O(n) time, O(1) space. 0ms 41.8Mb.
    public static ListNode mergeTwoListsIter2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    // recursive, 0ms 41.9 Mb. O(n) time and space.
    public static ListNode mergeTwoListsRec(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoListsRec(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRec(l2.next, l1);
            return l2;
        }
    }
}
