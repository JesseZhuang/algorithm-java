package stack;

import java.util.Stack;

/**
 * LeetCode 2696, easy, tags: string, stack, simulation.
 * <p>
 * You are given a string s consisting only of uppercase English letters.
 * <p>
 * You can apply some operations to this string where, in one operation, you can remove any occurrence of
 * one of the substrings "AB" or "CD" from s.
 * <p>
 * Return the minimum possible length of the resulting string that you can obtain.
 * <p>
 * Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABFCACDB"
 * Output: 2
 * Explanation: We can do the following operations:
 * - Remove the substring "ABFCACDB", so s = "FCACDB".
 * - Remove the substring "FCACDB", so s = "FCAB".
 * - Remove the substring "FCAB", so s = "FC".
 * So the resulting length of the string is 2.
 * It can be shown that it is the minimum length that we can obtain.
 * Example 2:
 * <p>
 * Input: s = "ACBBD"
 * Output: 5
 * Explanation: We cannot do any operations on the string so the length remains the same.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s consists only of uppercase English letters.
 * <p>
 * Hint 1
 * Can we use brute force to solve the problem?
 * Hint 2
 * Repeatedly traverse the string to find and remove the substrings “AB” and “CD” until no more occurrences exist.
 */
@SuppressWarnings("unused")
public class MinStringLenRS {

    // stack, n, n. 4ms, 43.06mb.
    static class SolutionStack {
        public int minLength(String s) {
            Stack<Character> st = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (!st.isEmpty() &&
                        ((s.charAt(i) == 'B' && st.peek() == 'A') || (s.charAt(i) == 'D' && st.peek() == 'C')))
                    st.pop();
                else st.push(s.charAt(i));
            }
            return st.size();
        }
    }

    // write pointer, n, n. 2ms, 43.32mb.
    static class Solution {
        public int minLength(String s) {
            int wp = 0; // write pointer
            char[] ca = s.toCharArray();
            for (int readPtr = 0; readPtr < s.length(); readPtr++) {
                // Write the current character to the current write position
                ca[wp] = ca[readPtr];
                // Check if we have a valid pattern to remove
                if (wp > 0
                        && (ca[wp - 1] == 'A' || ca[wp - 1] == 'C')
                        && ca[wp] == ca[wp - 1] + 1
                ) wp--; // A or C will be overwritten
                else wp++; // No match, so move the write pointer forward
            }
            return wp; // The writePtr now represents the length of the remaining string
        }
    }
}
