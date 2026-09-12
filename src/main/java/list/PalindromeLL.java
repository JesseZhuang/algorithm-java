package list;

import struct.ListNode;

/**
 * LeetCode 234, LintCode 223, easy, tags: linked list, two pointers, stack, recursion.
 * <p>
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 9
 * <p>
 * <p>
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
@SuppressWarnings("unused")
public final class PalindromeLL {
    private PalindromeLL() {
    }

    // O(n) time, O(1) space. Reverse first half in-place, compare, restore.
    public static boolean isPalindrome(ListNode head) { // 1->2->1->#
        ListNode rev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) { // O(n/2)
            fast = fast.next.next;
            ListNode temp = rev;
            rev = slow; // reverse first half
            slow = slow.next;
            rev.next = temp;
        } // #<-1:rev slow:2->1->#, fast:1->#
        ListNode tail = fast == null ? slow : slow.next; // odd: skip middle
        while (rev != null) { // O(n/2)
            if (tail.val != rev.val) return false;
            tail = tail.next;
            ListNode temp = slow;
            slow = rev;
            rev = rev.next;
            slow.next = temp;
        } // 1->2->1->#
        return true;
    }

    // O(n) time, O(n) space. Collect first half with stack, compare with second half.
    public static boolean isPalindromeStack(ListNode head) {
        ListNode slow = head, fast = head;
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
        while (fast != null && fast.next != null) { // O(n/2)
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next; // odd: skip middle
        while (slow != null) { // O(n/2)
            if (!stack.pop().equals(slow.val)) return false;
            slow = slow.next;
        }
        return true;
    }
}
