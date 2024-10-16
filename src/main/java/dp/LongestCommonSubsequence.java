package dp;

/**
 * LeetCode 1143, medium. Tags: dynamic programming, string.
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * Example 1:
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * <p>
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * <p>
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 * <p>
 * hint: Try dynamic programming.
 * DP[i][j] represents the longest common subsequence of text1[0 ... i] & text2[0 ... j].
 * DP[i][j] = DP[i - 1][j - 1] + 1 , if text1[i] == text2[j] DP[i][j] = max(DP[i - 1][j], DP[i][j - 1]) , otherwise
 * <p>
 * see resources/longest.common.subsequence.png.
 */
@SuppressWarnings("unused")
public class LongestCommonSubsequence {

    // solution 1, dp, 11ms, 39.8 Mb. O(M*N) time, O(min(M,N)) space.
    static class Solution1 {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            if (m < n) return longestCommonSubsequence(text2, text1); // ensure m>=n
            int[] dp = new int[n + 1];
            for (int i = 0; i < m; ++i) {
                for (int j = 0, pr = 0, prpc; j < n; ++j) {
                    prpc = pr; // dp[i][j] when j->j+1, dp[i][j+1]->dp[i][j] prev row->prov row prev col
                    pr = dp[j + 1]; // dp[i][j+1]
                    // setting dp[i+1][j+1]
                    dp[j + 1] = text1.charAt(i) == text2.charAt(j) ? prpc + 1 : Math.max(dp[j], pr);
                }
            }
            return dp[n];
        }
    }

    static class Solution2 {
        /**
         * See longest.common.subsequence.png in resources.
         * 13 ms, 46.3 Mb. O(M*N) time, O(M*N) space.
         */
        public int longestCommonSubsequence(String text1, String text2) {
            int[][] dp = new int[text1.length() + 1][text2.length() + 1];
            for (int i = 0; i < text1.length(); ++i)
                for (int j = 0; j < text2.length(); ++j)
                    if (text1.charAt(i) == text2.charAt(j)) dp[i + 1][j + 1] = 1 + dp[i][j];
                    else dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]); // max(prevRow, prevCol)
            return dp[text1.length()][text2.length()];
        }
    }

    static class Solution3 {
        public int longestCommonSubsequenceRecursive(String text1, String text2) {
            return longestCommonSubsequence(text1, text2, 0, 0);
        }

        // time limit exceeded. O(2^N) time, O(1) space.
        private int longestCommonSubsequence(String text1, String text2, int i, int j) {
            if (i == text1.length() || j == text2.length()) return 0;
            if (text1.charAt(i) == text2.charAt(j))
                return 1 + longestCommonSubsequence(text1, text2, i + 1, j + 1);
            else return Math.max(// T(N,M) = 2(T(M,N)) + 1, similar to hanoi tower, 2^n, exponential
                    longestCommonSubsequence(text1, text2, i + 1, j),
                    longestCommonSubsequence(text1, text2, i, j + 1)
            );
        }
    }

}
