package string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 394, medium, tags: string, stack, recursion.
 * <p>
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are
 * well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits
 * are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * <p>
 * Example 2:
 * <p>
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * <p>
 * Example 3:
 * <p>
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
@SuppressWarnings("unused")
public class DecodeString {

    // O(n * max_k) time, O(n) space. Stack-based.
    static class Solution {
        public String decodeString(String s) {
            Deque<StringBuilder> strStack = new ArrayDeque<>();
            Deque<Integer> countStack = new ArrayDeque<>();
            StringBuilder cur = new StringBuilder();
            int k = 0;
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    k = k * 10 + (c - '0');
                } else if (c == '[') {
                    strStack.push(cur);
                    countStack.push(k);
                    cur = new StringBuilder();
                    k = 0;
                } else if (c == ']') {
                    StringBuilder prev = strStack.pop();
                    int repeat = countStack.pop();
                    for (int i = 0; i < repeat; i++) prev.append(cur);
                    cur = prev;
                } else {
                    cur.append(c);
                }
            }
            return cur.toString();
        }
    }

    // O(n * max_k) time, O(n) space. Recursive descent.
    static class Solution2 {
        private int i;

        public String decodeString(String s) {
            i = 0;
            return decode(s);
        }

        private String decode(String s) {
            StringBuilder res = new StringBuilder();
            while (i < s.length() && s.charAt(i) != ']') {
                if (Character.isDigit(s.charAt(i))) {
                    int k = 0;
                    while (i < s.length() && Character.isDigit(s.charAt(i))) {
                        k = k * 10 + (s.charAt(i) - '0');
                        i++;
                    }
                    i++; // skip '['
                    String nested = decode(s);
                    i++; // skip ']'
                    for (int j = 0; j < k; j++) res.append(nested);
                } else {
                    res.append(s.charAt(i));
                    i++;
                }
            }
            return res.toString();
        }
    }
}
