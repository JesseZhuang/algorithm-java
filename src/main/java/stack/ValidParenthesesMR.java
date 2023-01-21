package stack;

/**
 * LeetCode 1249, medium, tags: string, stack.
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
 * parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 * <p>
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 * <p>
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 * Hints:
 * Each prefix of a balanced parentheses has a number of open parentheses greater or equal than closed parentheses,
 * similar idea with each suffix.
 * Check the array from left to right, remove characters that do not meet the property mentioned above,
 * same idea in backward way.
 */
public class ValidParenthesesMR {
    public String minRemoveToMakeValid(String s) {
        return null;
    }
}
