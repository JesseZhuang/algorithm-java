package list;

import struct.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 2130, medium, tags: linked list, two pointers, stack.
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin
 * of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 * <p>
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2.
 * These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 * <p>
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 * <p>
 * Example 1:
 * Input: head = [5,4,2,1]
 * Output: 6
 * <p>
 * Example 2:
 * Input: head = [4,2,2,3]
 * Output: 7
 * <p>
 * Example 3:
 * Input: head = [1,100000]
 * Output: 100001
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is an even integer in the range [2, 10^5].
 * 1 <= Node.val <= 10^5
 */
public final class MaximumTwinSumLinkedList {

    private MaximumTwinSumLinkedList() {
    }

    // O(n) time, O(1) space. Reverse second half approach.
    public static int pairSum(ListNode head) {
        // O(n/2) find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // O(n/2) reverse second half
        ListNode prev = null, cur = slow;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        // O(n/2) pair up and find max
        int max = 0;
        ListNode left = head, right = prev;
        while (right != null) {
            max = Math.max(max, left.val + right.val);
            left = left.next;
            right = right.next;
        }
        return max;
    }

    // O(n) time, O(n) space. Stack approach.
    public static int pairSumStack(ListNode head) {
        // O(n/2) push first half onto stack
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        // O(n/2) pop and pair with second half
        int max = 0;
        while (slow != null) {
            max = Math.max(max, stack.pop() + slow.val);
            slow = slow.next;
        }
        return max;
    }
}
