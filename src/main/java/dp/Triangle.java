package dp;

import java.util.List;

/**
 * LeetCode 120, medium, tags: array, dynamic programming.
 * Given a triangle array, return the minimum path sum from top to bottom.
 * For each step, you may move to an adjacent number of the row below. More formally,
 * if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 * <p>
 * Example 1:
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11.
 * <p>
 * Example 2:
 * Input: triangle = [[-10]]
 * Output: -10
 * <p>
 * Constraints:
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 */
public class Triangle {

    // solution 1, bottom-up DP with O(n) space. O(n^2) time, O(n) space. n: number of rows.
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) dp[j] = triangle.get(n - 1).get(j); // O(n) copy last row
        for (int i = n - 2; i >= 0; i--) // O(n) rows bottom to top
            for (int j = 0; j <= i; j++) // O(i) columns
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
        return dp[0];
    }

    // solution 2, top-down DFS with memoization. O(n^2) time, O(n^2) space.
    public int minimumTotalMemo(List<List<Integer>> triangle) {
        int n = triangle.size();
        Integer[][] memo = new Integer[n][n];
        return dfs(triangle, 0, 0, memo);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j, Integer[][] memo) {
        if (i == triangle.size()) return 0;
        if (memo[i][j] != null) return memo[i][j];
        memo[i][j] = triangle.get(i).get(j) + Math.min(dfs(triangle, i + 1, j, memo), dfs(triangle, i + 1, j + 1, memo));
        return memo[i][j];
    }
}
