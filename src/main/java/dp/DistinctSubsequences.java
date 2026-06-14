package dp;

/**
 * LeetCode 115, hard, tags: string, dynamic programming.
 * <p>
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 * <p>
 * Constraints:
 * <ul>
 *   <li>0 <= s.length, t.length <= 1000</li>
 *   <li>s and t consist of English letters.</li>
 * </ul>
 */
public final class DistinctSubsequences {
    private DistinctSubsequences() {}

    /**
     * Solution 1: 2D DP. dp[i][j] = number of ways to form t[0:j] from s[0:i].
     * O(m*n) time, O(m*n) space.
     */
    public static int numDistinct2D(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = 1; // empty t can always be formed
        for (int i = 1; i <= m; i++) { // O(m) outer loop over s
            for (int j = 1; j <= n; j++) { // O(n) inner loop over t, together O(m*n)
                dp[i][j] = dp[i - 1][j]; // skip s[i-1]
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1]; // use s[i-1] to match t[j-1]
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Solution 2: 1D DP (space-optimized), traverse j in reverse.
     * O(m*n) time, O(n) space.
     */
    public static int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // base case: empty t
        for (int i = 1; i <= m; i++) { // O(m) outer loop over s
            for (int j = Math.min(i, n); j >= 1; j--) { // O(n) inner loop, reverse to avoid overwrite
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1]; // accumulate from previous state
                }
            }
        }
        return dp[n];
    }
}
