package stack;

/**
 * LeetCode 921, medium, tags: stack, string, greedy.
 * <p>
 * A parentheses string is valid if and only if:
 * <p>
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 * <p>
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "())"
 * Output: 1
 * Example 2:
 * <p>
 * Input: s = "((("
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'.
 */
@SuppressWarnings("unused")
public class MinAddParen {
    // n, 1. two variables.
    static class Solution {
        public int minAddToMakeValid(String S) {
            int uo = 0, uc = 0; // unmatched open and close brackets
            for (char c : S.toCharArray()) {
                if (c == '(') uo++;
                else if (uo > 0) uo--;
                else uc++;
            }
            return uc + uo;
        }
    }
}

