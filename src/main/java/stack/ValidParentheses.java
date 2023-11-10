package stack;

import java.util.Stack;

/**
 * LeetCode 20, LintCode 423, easy, tags: string, stack.
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the
 * input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 * Hints:
 * Use a stack of characters.
 * When you encounter an opening bracket, push it to the top of the stack.
 * When you encounter a closing bracket, check if the top of the stack was the opening for it.
 * If yes, pop it from the stack. Otherwise, return false.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {
    // 1ms, 40.5Mb. O(n) time and space.
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(')');
            else if (s.charAt(i) == '[') stack.push(']');
            else if (s.charAt(i) == '{') stack.push('}');
            else if (stack.isEmpty() || s.charAt(i) != stack.pop()) return false;
        }
        return stack.isEmpty(); // still need to check emptiness
    }
}
