package dp;

/**
 * LeetCode 312, LintCode 168, hard.
 */
@SuppressWarnings("unused")
public class BurstBalloons {
    // todo, lint solution
    static class Solution {
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[][] rec = new int[n + 2][n + 2];
            int[] val = new int[n + 2];
            val[0] = val[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                val[i] = nums[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j <= n + 1; j++) {
                    for (int k = i + 1; k < j; k++) {
                        int sum = val[i] * val[k] * val[j];
                        sum += rec[i][k] + rec[k][j];
                        rec[i][j] = Math.max(rec[i][j], sum);
                    }
                }
            }
            return rec[0][n + 1];
        }
    }
}
