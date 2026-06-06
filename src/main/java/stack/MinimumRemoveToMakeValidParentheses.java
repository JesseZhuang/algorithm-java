package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1249, medium, tags: string, stack.
 * <p>
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * - It is the empty string, contains only lowercase characters, or
 * - It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * - It can be written as (A), where A is a valid string.
 * <p>
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * <p>
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * <p>
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is either'(' , ')', or lowercase English letter.
 */
public final class MinimumRemoveToMakeValidParentheses {
    private MinimumRemoveToMakeValidParentheses() {}

    /**
     * Solution 1: Stack approach.
     * Track indices of unmatched parentheses using a stack, collect them in a set,
     * then rebuild the string skipping those indices.
     * Time: O(n), Space: O(n)
     */
    public static String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> toRemove = new HashSet<>();

        // First pass: identify unmatched parentheses
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    toRemove.add(i); // unmatched ')'
                } else {
                    stack.pop(); // matched with '('
                }
            }
        }

        // Remaining indices in stack are unmatched '('
        while (!stack.isEmpty()) {
            toRemove.add(stack.pop());
        }

        // Build result string, skipping indices in toRemove
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!toRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString(); // Time O(n), Space O(n)
    }

    /**
     * Solution 2: Two-pass approach.
     * Left-to-right pass removes excess ')', right-to-left pass removes excess '('.
     * Time: O(n), Space: O(n)
     */
    public static String minRemoveToMakeValidTwoPass(String s) {
        // First pass: left to right, remove unmatched ')'
        StringBuilder sb = new StringBuilder();
        int openCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                if (openCount == 0) {
                    continue; // skip unmatched ')'
                }
                openCount--;
            }
            sb.append(c);
        }

        // Second pass: right to left, remove unmatched '('
        StringBuilder result = new StringBuilder();
        openCount = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == ')') {
                openCount++;
            } else if (c == '(') {
                if (openCount == 0) {
                    continue; // skip unmatched '('
                }
                openCount--;
            }
            result.append(c);
        }

        return result.reverse().toString(); // Time O(n), Space O(n)
    }
}
