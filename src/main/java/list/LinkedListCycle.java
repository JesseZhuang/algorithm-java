package list;

import struct.ListNode;

/**
 * LeetCode 141, Easy, tags: hash table, linked list, two pointers.
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
 * is connected to. Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * Example 2:
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * Example 3:
 * <p>
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * <p>
 * Constraints:
 * <p>
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 * <p>
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 * https://en.wikipedia.org/wiki/Cycle_detection
 */
public class LinkedListCycle {
    // best: O(N) time, O(1) space, 0ms, 43.6Mb.
    // Other solutions: 1) use hash set O(N) space.
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        // note do not check slow
        while (fast != null && fast.next != null) { // fast.next != null && fast.next.next != null: NPE
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true; // do not flip true and false
        }
        return false;
    }
}
