package dp;

/**
 * LeetCode 188, hard, tags: array, dp.
 * <p>
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times
 * and sell at most k times.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 * <p>
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0)
 * and sell on day 6 (price = 3), profit = 3-0 = 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
@SuppressWarnings("unused")
public class BuySellStockIV {
    // credit jinrf@->yulingtianxia@, generalize to k, nk, k.
    static class Solution1 {
        public int maxProfit(int k, int[] prices) {
            //fix for memory problem in frequent trades
            if (k >= prices.length / 2) {
                int res = 0;
                for (int i = 1; i < prices.length; i++)
                    if (prices[i] > prices[i - 1]) res += prices[i] - prices[i - 1];
                return res;
            }
            int[] buy = new int[k + 1];
            int[] sell = new int[k + 1];
            for (int i = 0; i <= k; i++) {
                buy[i] = Integer.MIN_VALUE;
                sell[i] = 0;
            }
            for (int p : prices) {
                for (int j = 1; j <= k; j++) {
                    buy[j] = Math.max(buy[j], sell[j - 1] - p); // no action or buy @ p
                    sell[j] = Math.max(sell[j], p + buy[j]); // no action or sell @ p
                }
            }
            return sell[k];
        }
    }
}
