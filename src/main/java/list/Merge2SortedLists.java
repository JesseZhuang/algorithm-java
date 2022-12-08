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
 * <li>Iterative/Recursive, O(N) time, O(1) space.
 * </ul>
 */
public class Merge2SortedLists {
    // 1ms 43.1 MB. merge sort merge step.
    public ListNode mergeTwoListsIter(ListNode l1, ListNode l2) {
        ListNode cur1 = l1, cur2 = l2;
        if (cur1 == null) return l2;
        if (cur2 == null) return l1;
        ListNode head;
        if (l1.val <= l2.val) {
            head = cur1 = l1;
            cur2 = l2;
        } else {
            head = cur1 = l2;
            cur2 = l1;
        }
        while (cur2 != null) {
            while (cur1.next != null && cur1.next.val <= cur2.val) cur1 = cur1.next;
            if (cur1.next == null) {
                cur1.next = cur2;
                break;
            } else {
                ListNode temp2 = cur2.next;

                cur2.next = cur1.next;
                cur1.next = cur2;

                cur1 = cur1.next;
                cur2 = temp2;
            }
        }
        return head;
    }

    // 0ms 41.8Mb.
    public static ListNode mergeTwoListsIter2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = smallerOne(l1, l2), cur = head;
        if (head == l1) l1 = l1.next;
        else l2 = l2.next;
        while (l1 != null && l2 != null) {
            cur.next = smallerOne(l1, l2);
            if (cur.next == l1) l1 = l1.next;
            else l2 = l2.next;
            cur = cur.next;
        }
        cur.next = smallerOne(l1, l2);
        return head;
    }

    private static ListNode smallerOne(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.val <= b.val) return a;
        else return b;
    }

    // elegant, 0ms 41.9 Mb.
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
