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
public class PalindromeLL {
    // n,1. @StefanPochmann thread @Heronalps
    static class Solution {
        public boolean isPalindrome(ListNode head) { // 1->2->1->#
            ListNode rev = null, slow = head, fast = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                ListNode temp = rev;
                rev = slow; // reverse first half
                slow = slow.next;
                rev.next = temp;
            } // #<-1:rev slow:2->1->#, fast:1->#
            ListNode tail = fast == null ? slow : slow.next; // tail:1->#, odd number of nodes
            while (rev != null) {
                if (tail.val != rev.val) return false;
                tail = tail.next;
                ListNode temp = slow; // play nice, reverse first half again
                slow = rev;
                rev = rev.next;
                slow.next = temp;
            } // 1->2->1->#
            return true;
        }
    }
}
