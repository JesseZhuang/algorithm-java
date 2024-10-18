package dp;

/**
 * https://leetcode.com/discuss/study-guide/1152328/01-Knapsack-Problem-and-Dynamic-Programming
 * https://leetcode.com/discuss/study-guide/1200320/Thief-with-a-knapsack-a-series-of-crimes
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * <p>
 * Given a set of n items numbered from 1 up to n, each with a weight wi and a value vi, along with a maximum
 * weight capacity W, maximize the sum of the values of the items in the knapsack so that the sum of the weights
 * is less than or equal to the knapsack's capacity.
 * <p>
 * When C capacity is large compared to n, e.g., n:100, C:10^9, meet in the middle algorithm (wiki) takes 2^(n/2) space
 * and time n*2^(n/2).
 */
@SuppressWarnings("unused")
public class KnapSack {
    /**
     * dp with 2d array. nC time and space. For 0/1 knapsack problem.
     *
     * @param C capacity of the knapsack.
     * @param w weights of the items.
     * @param v values of the items.
     * @return maximum value can fit in the knapsack.
     */
    public static int max2D(int C, int[] w, int[] v) {
        int n = w.length;
        int[][] dp = new int[n + 1][C + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                if (w[i] <= j) // exclude or include
                    dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
                else dp[i + 1][j] = dp[i][j]; // cannot fix, exclude
            }
        }
        return dp[n][C];
    }

    public static int max1D(int C, int[] w, int[] v) {
        int dp[] = new int[C + 1], n = w.length;
        for (int i = 0; i < n; ++i)
            for (int j = C; j >= w[i]; --j) // backwards, j-w[i] not updated yet, no double counting
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
        return dp[C];
    }
}
