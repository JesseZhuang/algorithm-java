package dp;

/**
 * LeetCode 516 - Longest Palindromic Subsequence
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 */
public final class LongestPalindromicSubsequence {

    private LongestPalindromicSubsequence() {
    }

    /**
     * 2D DP solution.
     * dp[i][j] = length of longest palindromic subsequence in s[i..j]
     *
     * @param s input string
     * @return length of longest palindromic subsequence
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // O(n^2) time, O(n^2) space
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    /**
     * Space-optimized 1D DP solution.
     *
     * @param s input string
     * @return length of longest palindromic subsequence
     */
    public static int longestPalindromeSubseqOptimized(String s) {
        int n = s.length();
        int[] dp = new int[n];

        // O(n^2) time, O(n) space
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            int prev = 0; // stores dp[i+1][j-1] from the 2D version
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j]; // save current dp[j] before overwrite
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = prev + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp;
            }
        }

        return dp[n - 1];
    }
}
