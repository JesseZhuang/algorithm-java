package array;

/**
 * LeetCode 123, hard, tags: array, dp.
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions
 * at the same time. You must sell before buying again.
 * Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 */
@SuppressWarnings("unused")
public class BuySellStockIII {
    // 4 vars. 4ms, 60.57mb. n, 1.
    static class Solution1 {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int buy1 = -prices[0], sell1 = 0; // bought once, sold once
            int buy2 = -prices[0], sell2 = 0;
            for (int i = 1; i < n; ++i) {
                buy1 = Math.max(buy1, -prices[i]); // bought before or bought on i-th day
                sell1 = Math.max(sell1, buy1 + prices[i]);
                buy2 = Math.max(buy2, sell1 - prices[i]);
                sell2 = Math.max(sell2, buy2 + prices[i]);
            }
            return sell2;
        }
    }

    // generalize to k transactions. 3ms, 61mb. n, n.
    static class Solution2 {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int K = 2;
            int[][] dp = new int[K + 1][n]; // max profit for i transactions on j-th day
            for (int i = 1; i < dp.length; i++) { // size k+1
                int maxDiff = -prices[0];
                for (int j = 1; j < dp[0].length; j++) {
                    // i-1 transactions j-1 th day max profit - prices[j-1]
                    maxDiff = Math.max(maxDiff, dp[i - 1][j - 1] - prices[j - 1]);
                    // no action or sell on j-th day
                    dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                }
            }
            return dp[K][n - 1];
        }
    }
}
