package list;

import struct.ListNode;

/**
 * LeetCode 148, medium, tags: linked list, two pointers, divide and conquer, sorting, merge sort.
 * <p>
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg
 * <p>
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2: https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg
 * <p>
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 5 * 10^4].
 * -10^5 <= Node.val <= 10^5
 * <p>
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortList {
    // O(nLgn) time, O(1) space. bottom up merge sort, no space for recursive stack. 12 ms, 54.5 Mb.
    public static ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int n = 0;
        while (head != null) {
            head = head.next;
            n++;
        }
        // 4,2,1,3 -> 2,4,1,3 -> 1,2,3,4
        for (int step = 1; step < n; step <<= 1) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur, right = split(left, step);
                cur = split(right, step);
                prev = merge(left, right, prev);
            }
        }
        return dummy.next;
    }

    private static ListNode split(ListNode head, int step) {
        if (head == null) return null;
        for (int i = 1; head.next != null && i < step; i++) head = head.next;
        ListNode right = head.next;
        head.next = null;
        return right;
    }

    private static ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode cur = prev;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) cur.next = left;
        else if (right != null) cur.next = right;
        while (cur.next != null) cur = cur.next; // return tail of the sorted list
        return cur;
    }

}
