package dp;

/**
 * LeetCode 62, medium, tags: math, dynamic programming, combinatorics.
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either
 * down or right at any point in time.
 * <p>
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * <p>
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 * <p>
 * Example 1:
 * <p>
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 * <p>
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * <p>
 * Constraints:
 * <p>
 * 1 <= m, n <= 100
 */
public class UniquePaths {
    // O(mn) time, O(min(m,n)) space, 0ms, 38.9 Mb.
    public int uniquePathsDP(int m, int n) {
        if (m > n) return uniquePathsDP(n, m);
        int[] dp = new int[m + 1];
        dp[1] = 1;
        for (int i = 0; i < n; i++)
            for (int j = 1; j <= m; j++)
                dp[j] += dp[j - 1];
        return dp[m];
    }

    /**
     * m-1 steps down and n-1 steps to the right. So (m+n-2) steps, pick m-1 to be down.
     * Latex \binom{m+n-2}{m-1} == (m+n-2)!/(m-1)!(n-1)! == (m+n-2)*...*(n)/(m-1)!
     * O(min(m,n)) time, O(1) space. 0ms, 39.3 Mb.
     */
    public int uniquePathsCombination(int m, int n) {
        if (m > n) return uniquePathsCombination(n, m);
        long res = 1;
        for (int i = m + n - 2, j = 1; i >= n; i--, j++) res = res * i / j;
        return (int) res;
    }
}
