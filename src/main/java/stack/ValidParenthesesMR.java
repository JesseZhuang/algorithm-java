package stack;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * 1 <= s.length <= 10^5
 * s[i] is either'(' , ')', or lowercase English letter.
 * Hints:
 * Each prefix of a balanced parentheses has a number of open parentheses greater or equal than closed parentheses,
 * similar idea with each suffix.
 * Check the array from left to right, remove characters that do not meet the property mentioned above,
 * same idea in backward way.
 */
public class ValidParenthesesMR {

    // solution 1, counts, two pass, O(N) time, O(1) space. 15ms, 42.9MB.
    public String minRemoveToMakeValid(String s) {
        int left = 0, right = 0, l = s.length();
        // need to look ahead to know whether a ( should be included or not
        for (int i = 0; i < l; i++) if (s.charAt(i) == ')') right++;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (left == right) continue; // invalid (, skip
                left++;
            } else if (c == ')') {
                right--;
                if (left == 0) continue; // invalid ), skip
                left--;
            }
            res.append(c);
        }
        return res.toString();
    }

    // solution 2, 74 ms, 55.1 Mb. O(N) time, O(N) space. stack.
    // Another method is to use a set for the open left parentheses to be removed
    public String minRemoveToMakeValidStack(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        String[] arr = s.split("");
        for (int i = 0; i < arr.length; i++) {
            if ("(".equals(arr[i])) stack.push(i); // remember index
            else if (")".equals(arr[i])) {
                if (!stack.isEmpty()) stack.pop(); // valid )
                else arr[i] = ""; // invalid )
            }
        }
        while (!stack.isEmpty()) arr[stack.pop()] = ""; // extra (: invalid
        return String.join("", arr);
    }

    // 32ms, 53.7Mb.
    public String minRemoveToMakeValidStack2(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder(s);
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '(') stack.push(i);
            else if (res.charAt(i) == ')') {
                if (!stack.isEmpty()) stack.pop();
                else res.setCharAt(i, '#');
            }
        }
        while (!stack.isEmpty()) res.setCharAt(stack.pop(), '#');
        return res.toString().replaceAll("#", "");
    }
}
