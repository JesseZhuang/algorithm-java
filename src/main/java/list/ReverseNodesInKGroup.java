package list;

import struct.ListNode;

/**
 * <p>
 * LeetCode 25, hard, tags: LinkedList, recursion.
 * <p>
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the
 * modified list. k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should
 * remain as it is.
 * <p>
 * You may not alter the values in the nodes, only nodes themselves may be changed.
 * <p>
 * Constraints:
 * <ul>
 *   <li>The number of nodes in the list is n.</li>
 *   <li>1 <= k <= n <= 5000</li>
 *   <li>0 <= Node.val <= 1000</li>
 * </ul>
 */
public final class ReverseNodesInKGroup {

    private ReverseNodesInKGroup() {
    }

    /**
     * Solution 1: Iterative. O(n) time, O(1) space.
     * Reverse each group of k nodes in place using pointer manipulation.
     */
    public static ListNode reverseKGroupIterative(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) { // O(n/k) iterations for outer loop
            // Check if there are k nodes remaining
            ListNode kth = groupPrev;
            for (int i = 0; i < k; i++) { // O(k) per group to find kth node
                kth = kth.next;
                if (kth == null) return dummy.next;
            }
            ListNode groupNext = kth.next;

            // Reverse k nodes
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            for (int i = 0; i < k; i++) { // O(k) per group to reverse
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // Connect reversed group
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }
    }

    /**
     * Solution 2: Recursive. O(n) time, O(n/k) space for recursion stack.
     * Reverse first k nodes, then recursively process the rest.
     */
    public static ListNode reverseKGroupRecursive(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        // Check if there are at least k nodes
        ListNode curr = head;
        for (int i = 0; i < k; i++) { // O(k) to check group length
            if (curr == null) return head;
            curr = curr.next;
        }

        // Reverse first k nodes
        ListNode prev = null;
        curr = head;
        for (int i = 0; i < k; i++) { // O(k) to reverse group
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // head is now the tail of reversed group; connect to result of recursive call
        head.next = reverseKGroupRecursive(curr, k); // O(n/k) recursive calls
        return prev;
    }
}
