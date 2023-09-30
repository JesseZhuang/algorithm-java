package dp;

/**
 * LeetCode 309, medium, tags: array, dynamic programming.
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 * <p>
 * Input: prices = [1]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class BuySellCoolDown {
    // O(n) tiem and space, 1ms, 40.1 Mb.
    public int maxProfitDP1(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        // cool down or rest is not really an action, so at the end we either end with a buy or a sell
        int[] sell = new int[len]; // max profit ending with a sell
        int[] buy = new int[len]; // max profit ending with a buy

        buy[0] = -prices[0]; // bought at 0
        buy[1] = -Math.min(prices[0], prices[1]); // bought at 0 or 1
        sell[1] = Math.max(0, buy[0] + prices[1]); // did nothing or buy at 0 sell at 1

        for (int i = 2; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]); // hold at i or sell at/before i-2 buy at i
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]); // sold at i-1 or bought at/before i-1, sell at i
        }
        return sell[len - 1];
    }

    // 0ms 40.5 Mb. O(n) time O(1) space.
    public int maxProfitDP2(int[] prices) {
        if (prices.length <= 1) return 0;
        int b0 = -prices[0], b1 = b0; // b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
        int s0 = 0, s1 = 0, s2 = 0;  // s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0; // i++, b1 -> b0
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }
}
