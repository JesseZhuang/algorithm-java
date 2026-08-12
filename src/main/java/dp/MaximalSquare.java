package dp;

/**
 * LeetCode 221, medium, tags: dynamic programming, matrix.
 * <p>
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's
 * and return its area.
 * <p>
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * <p>
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * <p>
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public final class MaximalSquare {

    private MaximalSquare() {
    }

    /**
     * DP with 1D space optimization.
     * dp[j] = side length of largest square with bottom-right at (i, j).
     * Transition: dp[j] = min(dp[j], dp[j-1], prev) + 1 if matrix[i][j] == '1'.
     * <p>
     * O(m*n) time, O(n) space.
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n + 1];
        int maxSide = 0;
        for (int i = 0; i < m; i++) {
            int prev = 0; // dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (matrix[i][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), prev) + 1;
                    maxSide = Math.max(maxSide, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxSide * maxSide;
    }
}
