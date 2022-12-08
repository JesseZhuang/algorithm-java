package list;

import struct.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 23 Hard. Tags: linked list, divide and conquer, heap (priority queue), merge sort.
 * <p>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 * Constraints:
 * <p>
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 */
public class MergeKSortedLists {

    // O(NLgk) time, O(k) space. 14 ms, 47.9 Mb.
    public ListNode mergeKListsHeap(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        ListNode dummy = new ListNode(0), current = dummy;
        for (ListNode node : lists) if (node != null) minHeap.add(node);
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.remove();
            current.next = node;
            current = current.next;
            node = node.next;
            if (node != null) minHeap.add(node);
        }
        return dummy.next;
    }

    // O(NLgk) time, O(Lgk) stack space. 3 ms, 47 Mb.
    public ListNode mergeKListsMergeRecursive(ListNode[] lists) {
        if (lists.length == 0) return null;
        return mergeHelper(lists, 0, lists.length - 1); // length >= 1
    }

    private ListNode mergeHelper(ListNode[] lists, int left, int right) {
        if (right - left == 0) return lists[left];
        if (right - left == 1) return Merge2SortedLists.mergeTwoListsRec(lists[left], lists[right]);
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeHelper(lists, left, mid), l2 = mergeHelper(lists, mid + 1, right);
        return Merge2SortedLists.mergeTwoListsRec(l1, l2);
    }

    // merge bottom up, 5ms, 47.5Mb. O(NLgk) time, O(1) space (modified input).
    public ListNode mergeKListsMergeBU(ListNode[] lists) {
        int size = lists.length;
        for (int interval = 1; interval < lists.length; interval *= 2) {
            for (int i = 0; i < lists.length - interval; i += 2 * interval) {
                lists[i] = Merge2SortedLists.mergeTwoListsIter2(lists[i], lists[i + interval]);
            }
        }
        return size > 0 ? lists[0] : null;
    }

}
