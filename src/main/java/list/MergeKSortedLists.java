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
    public ListNode mergeKLists(ListNode[] lists) {
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
}
