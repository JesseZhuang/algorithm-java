package string;

import java.util.Arrays;

/**
 * LeetCode 3302, medium, tags: array, dp, two pointers, greedy.
 * <p>
 * You are given two strings word1 and word2.
 * <p>
 * A string x is called almost equal to y if you can change at most one character in x to make it identical to y.
 * <p>
 * A sequence of indices seq is called valid if:
 * <p>
 * The indices are sorted in ascending order.
 * Concatenating the characters at these indices in word1 in the same order results in a string that is almost equal
 * to word2.
 * Return an array of size word2.length representing the
 * lexicographically smallest
 * valid sequence of indices. If no such sequence of indices exists, return an empty array.
 * <p>
 * Note that the answer must represent the lexicographically smallest array, not the corresponding string formed by
 * those indices.
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "vbcca", word2 = "abc"
 * <p>
 * Output: [0,1,2]
 * <p>
 * Explanation:
 * <p>
 * The lexicographically smallest valid sequence of indices is [0, 1, 2]:
 * <p>
 * Change word1[0] to 'a'.
 * word1[1] is already 'b'.
 * word1[2] is already 'c'.
 * Example 2:
 * <p>
 * Input: word1 = "bacdc", word2 = "abc"
 * <p>
 * Output: [1,2,4]
 * <p>
 * Explanation:
 * <p>
 * The lexicographically smallest valid sequence of indices is [1, 2, 4]:
 * <p>
 * word1[1] is already 'a'.
 * Change word1[2] to 'b'.
 * word1[4] is already 'c'.
 * Example 3:
 * <p>
 * Input: word1 = "aaaaaa", word2 = "aaabc"
 * <p>
 * Output: []
 * <p>
 * Explanation:
 * <p>
 * There is no valid sequence of indices.
 * <p>
 * Example 4:
 * <p>
 * Input: word1 = "abc", word2 = "ab"
 * <p>
 * Output: [0,1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= word2.length < word1.length <= 3 * 10^5, m,n
 * word1 and word2 consist only of lowercase English letters.
 * <p>
 * Hint 1
 * Let dp[i] be the longest suffix of word2 that exists as a subsequence of suffix of the substring of word1 starting
 * at index i.
 * Hint 2
 * If dp[i + 1] < m and word1[i] == word2[m - dp[i + 1] - 1],dp[i] =  dp[i + 1] + 1. Otherwise, dp[i] =  dp[i + 1].
 * Hint 3
 * For each index i, greedily select characters using the dp array to know whether a solution exists.
 */
@SuppressWarnings("unused")
public class LexMinValidSeq {
    // n+m, m. @lee215
    public static class Solution {
        public int[] validSequence(String word1, String word2) {
            int n = word1.length(), m = word2.length();
            // last chance word1[i] to match (word2[j]), j:[0,m-1]
            int[] last = new int[m]; // bacdc, abc: [-1,0,4]; aac, aab, [-1,-1,-1]
            Arrays.fill(last, -1);
            for (int i = n - 1, j = m - 1; i >= 0 && j >= 0; --i)
                if (word1.charAt(i) == word2.charAt(j)) last[j--] = i;
            int j = 0, skip = 0;
            int[] res = new int[m];
            for (int i = 0; i < n && j < m; ++i) {
                // 1) match, greedily take it 2) skip if j==m-1 or word2[j+1] can be matched later
                if (word1.charAt(i) == word2.charAt(j) || (skip == 0 && (j == m - 1 || i < last[j + 1]))) {
                    skip += (word1.charAt(i) != word2.charAt(j)) ? 1 : 0;
                    res[j++] = i;
                }
            }
            return j == m ? res : new int[0];
        }
    }
}
