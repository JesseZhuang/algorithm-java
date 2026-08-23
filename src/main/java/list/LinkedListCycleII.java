package list;

import struct.ListNode;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/**
 * LeetCode 142, Medium, tags: hash table, linked list, two pointers.
 * <p>
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
 * is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * <p>
 * Do not modify the linked list.
 * <p>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * <p>
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * <p>
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 * <p>
 * Constraints:
 * The number of the nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked-list.
 * <p>
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
public final class LinkedListCycleII {

    private LinkedListCycleII() {
    }

    /**
     * Solution 1: Floyd's Tortoise and Hare algorithm.
     * Once slow and fast meet inside the cycle, reset slow to head and advance both one step at a time.
     * They will meet at the cycle entry node.
     * <p>
     * Time: O(n), Space: O(1).
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * Solution 2: HashSet approach.
     * Track visited nodes; the first revisited node is the cycle entry.
     * <p>
     * Time: O(n), Space: O(n).
     */
    public static ListNode detectCycleHashSet(ListNode head) {
        Set<ListNode> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        ListNode cur = head;
        while (cur != null) {
            if (!seen.add(cur)) return cur;
            cur = cur.next;
        }
        return null;
    }
}
