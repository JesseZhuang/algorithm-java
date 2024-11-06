package string;

import static string.Zfunction.zfunction;

/**
 * LeetCode 3303, heard, biweekly 140 Q4, tags: string, string matching.
 * <p>
 * You are given two strings s and pattern.
 * <p>
 * A string x is called almost equal to y if you can change at most one character in x to make it identical to y.
 * <p>
 * Return the smallest starting index of a
 * substring
 * in s that is almost equal to pattern. If no such index exists, return -1.
 * <p>
 * A substring is a contiguous non-empty sequence of characters within a string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcdefg", pattern = "bcdffg"
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The substring s[1..6] == "bcdefg" can be converted to "bcdffg" by changing s[4] to "f".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "ababbababa", pattern = "bacaba"
 * <p>
 * Output: 4
 * <p>
 * Explanation:
 * <p>
 * The substring s[4..9] == "bababa" can be converted to "bacaba" by changing s[6] to "c".
 * <p>
 * Example 3:
 * <p>
 * Input: s = "abcd", pattern = "dba"
 * <p>
 * Output: -1
 * <p>
 * Example 4:
 * <p>
 * Input: s = "dde", pattern = "d"
 * <p>
 * Output: 0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pattern.length < s.length <= 10^5
 * s and pattern consist only of lowercase English letters.
 * <p>
 * <p>
 * Follow-up: Could you solve the problem if at most k consecutive characters can be changed?
 * <p>
 * Hint 1
 * Let dp1[i] represent the maximum length of a substring of s starting at index i that is also a prefix of pattern.
 * Hint 2
 * Let dp2[i] represent the maximum length of a substring of s ending at index i that is also a suffix of pattern.
 * Hint 3
 * Consider a window of size pattern.length. If dp1[i] + i == i + pattern.length - 1 - dp2[i + pattern.length - 1],
 * what does this signify?
 */
@SuppressWarnings("unused")
public class FirstAmostEqualSubstring {
    // m+n, m+n.
    static class Solution {
        public int minStartingIndex(String s, String pattern) {
            int n = s.length(), m = pattern.length();
            int[] z1 = zfunction(pattern + s);
            int[] z2 = zfunction(new StringBuilder(s + pattern).reverse().toString());
            for (int i = 0; i <= n - m; ++i)
                if (z1[m + i] + 1 + z2[n - i] >= m) return i;
            return -1;
        }
    }
}
