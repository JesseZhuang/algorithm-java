package dp;

/**
 * LeetCode 546, hard, tags: array, dp, memoization.
 * <p>
 * You are given several boxes with different colors represented by different positive numbers.
 * <p>
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some
 * continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 * <p>
 * Return the maximum points you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Example 2:
 * <p>
 * Input: boxes = [1,1,1]
 * Output: 9
 * Example 3:
 * <p>
 * Input: boxes = [1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 */
@SuppressWarnings("unused")
public class RemoveBoxes {

    // n^4, n^3, @yu6
    static class Solution2 {
        public int removeBoxes(int[] boxes) {
            int n = boxes.length;
            int[][][] dp = new int[n][n][n];
            for (int i = 0; i < n; i++)
                for (int streak = 0; streak <= i; streak++)
                    dp[i][i][streak] = (streak + 1) * (streak + 1);
            for (int len = 1; len < n; len++) {
                for (int l = 0; l + len < n; l++) {
                    int r = l + len;
                    for (int streak = 0; streak <= l; streak++) {
                        int res = (streak + 1) * (streak + 1) + dp[l + 1][r][0];
                        for (int m = l + 1; m <= r; m++)
                            if (boxes[m] == boxes[l])
                                res = Math.max(res, dp[l + 1][m - 1][0] + dp[m][r][streak + 1]);
                        dp[l][r][streak] = res;
                    }
                }
            }
            return dp[0][n - 1][0];
        }
    }

    // n^4, n^3, @hiepit, see pictures in that solution to understand better
    // dp(l,r,k) max points in boxes[l,r] having k same color boxes with boxes[l]
    // for example boxes = [3, 3, 1, 3, 3], dp(l=3,r=4,k=2) is max points in boxes[3..4] if we have extra 2 boxes
    // the same color with boxes[3] on the left side, it's the same as we find the max points in boxes = [3, 3, 3, 3]
    static class Solution1 {
        int[][][] memo;

        public int removeBoxes(int[] boxes) {
            int n = boxes.length;
            memo = new int[n][n][n];
            return dp(boxes, 0, n - 1, 0);
        }

        int dp(int[] boxes, int l, int r, int k) {
            if (l > r) return 0;
            if (memo[l][r][k] > 0) return memo[l][r][k];
            int lOrg = l, kOrg = k;
            // Increase both `l` and `k` if they have consecutive colors with `boxes[l]`
            while (l + 1 <= r && boxes[l] == boxes[l + 1]) {
                l += 1;
                k += 1;
            }
            int res = (k + 1) * (k + 1) + dp(boxes, l + 1, r, 0); // remove all boxes same color with boxes[l]
            for (int m = l + 1; m <= r; ++m) // Try to merge non-contiguous boxes of the same color together
                if (boxes[m] == boxes[l]) // merge [l+1,m-1] then [m,r]
                    res = Math.max(res, dp(boxes, l + 1, m - 1, 0) + dp(boxes, m, r, k + 1));
            return memo[lOrg][r][kOrg] = res;
        }
    }

}
