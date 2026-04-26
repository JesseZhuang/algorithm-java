package dp;

/**
 * LeetCode 72, medium, tags: string, dynamic programming.
 * <p>
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * You have the following three operations permitted on a word:
 * <ul>
 *   <li>Insert a character</li>
 *   <li>Delete a character</li>
 *   <li>Replace a character</li>
 * </ul>
 * <p>
 * Constraints:
 * <ul>
 *   <li>0 <= word1.length, word2.length <= 500</li>
 *   <li>word1 and word2 consist of lowercase English letters.</li>
 * </ul>
 */
public final class EditDistance {
    private EditDistance() {}

    /**
     * Solution 1: 1D DP (space-optimized). O(mn) time, O(n) space.
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for (int j = 0; j <= n; j++) dp[j] = j; // base case: empty word1
        for (int i = 1; i <= m; i++) { // O(m) rows
            int prev = dp[0]; // diagonal from previous row
            dp[0] = i;
            for (int j = 1; j <= n; j++) { // O(n) columns
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;
                } else {
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j - 1]));
                    // prev=replace, dp[j]=delete, dp[j-1]=insert
                }
                prev = temp;
            }
        }
        return dp[n];
    }

    /**
     * Solution 2: 2D DP. O(mn) time, O(mn) space.
     */
    public static int minDistance2D(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i; // base case: empty word2
        for (int j = 0; j <= n; j++) dp[0][j] = j; // base case: empty word1
        for (int i = 1; i <= m; i++) { // O(m) rows
            for (int j = 1; j <= n; j++) { // O(n) columns
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    // dp[i-1][j-1]=replace, dp[i-1][j]=delete, dp[i][j-1]=insert
                }
            }
        }
        return dp[m][n];
    }
}
