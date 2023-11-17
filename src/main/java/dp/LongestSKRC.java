package dp;

import java.util.Arrays;

/**
 * LeetCode 395, medium, tags: hash table, string, divide and conquer, sliding window.
 * <p>
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of
 * each character in this substring is greater than or equal to k.
 * <p>
 * if no such substring exists, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * <p>
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^4, n
 * s consists of only lowercase English letters.
 * let u == number of unique letters in the string.
 * 1 <= k <= 10^5
 */
public class LongestSKRC {
    // solution 1, sliding window, n(un) time, 1(26) space. 7ms, 41.03Mb.
    // other solutions: brute force n^2 time 1 space, divide and conquer n^2 time, n stack space
    public static int longestSubstring1(String s, int k) {
        int count[] = new int[26], u = countUnique(s), res = 0;
        for (int curUniqCnt = 1; curUniqCnt <= u; curUniqCnt++) {// note <= not <
            Arrays.fill(count, 0);
            for (int l = 0, r = 0, uC = 0, cAtLeastK = 0; r < s.length(); ) {
                if (uC <= curUniqCnt) {// expand the sliding window
                    int idx = s.charAt(r++) - 'a';
                    if (count[idx]++ == 0) uC++;
                    if (count[idx] == k) cAtLeastK++;
                } else {// shrink the sliding window
                    int idx = s.charAt(l++) - 'a';
                    if (count[idx]-- == k) cAtLeastK--;
                    if (count[idx] == 0) uC--;
                }
                if (uC == curUniqCnt && uC == cAtLeastK)
                    res = Math.max(r - l, res);
            }
        }
        return res;
    }

    // get the number of unique letters in the string s
    static int countUnique(String s) {
        boolean seen[] = new boolean[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int ci = s.charAt(i) - 'a';
            if (!seen[ci]) {
                res++;
                seen[ci] = true;
            }
        }
        return res;
    }

}
