package stack;

/**
 * LeetCode 678 - Valid Parenthesis String
 *
 * Given a string s containing only '(', ')' and '*', return true if s can be valid.
 * '*' can be treated as '(', ')' or an empty string.
 */
public final class ValidParenthesisString {

    private ValidParenthesisString() {
    }

    /**
     * Solution 1: Greedy min/max open count.
     * Track the minimum and maximum possible number of unmatched open parentheses.
     *
     * Time: O(n), Space: O(1)
     */
    public static boolean checkValidString(String s) {
        int lo = 0; // min possible open count
        int hi = 0; // max possible open count
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lo++;
                hi++;
            } else if (c == ')') {
                lo--;
                hi--;
            } else {
                lo--; // treat '*' as ')'
                hi++; // treat '*' as '('
            }
            if (hi < 0) {
                return false;
            }
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }

    /**
     * Solution 2: Two-pass greedy.
     * Left-to-right pass treats '*' as '(' to check for excess ')'.
     * Right-to-left pass treats '*' as ')' to check for excess '('.
     *
     * Time: O(n), Space: O(1)
     */
    public static boolean checkValidStringTwoPass(String s) {
        // Left to right: '(' and '*' increment, ')' decrements
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '*') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        // Right to left: ')' and '*' increment, '(' decrements
        balance = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ')' || c == '*') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return true;
    }
}
