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
    // solution 1, 0ms 40.5 Mb. O(n) time O(1) space.
    public int maxProfitDP2(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        // initializing assuming index at 2, b1, b0 represent buy[i - 1], buy[i]
        int b2 = -prices[0], b1 = Math.max(-prices[0], -prices[1]), b0;
        // s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
        int s2 = 0, s1 = Math.max(s2, b2 + prices[1]), s0 = Integer.MIN_VALUE;
        if (n == 2) return s1; // edge case
        for (int i = 2; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0; // i++, b1 -> b0
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }

    // solution 2, O(n) time and space, 1ms, 40.1 Mb.
    public int maxProfitDP1(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        // cool down or rest is not really an action, so at the end we either end with a buy or a sell
        int[] sell = new int[len]; // max profit ending with a sell
        int[] buy = new int[len]; // max profit ending with a buy

        buy[0] = -prices[0]; // bought at 0
        sell[0] = 0; // sell at 0 not possible, so profit is 0
        buy[1] = -Math.min(prices[0], prices[1]); // bought at 0 or 1
        sell[1] = Math.max(0, buy[0] + prices[1]); // did nothing or buy at 0 sell at 1

        for (int i = 2; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            // do nothing at i (so stay with buy[i-1]) or sell at/before i-2 (always better than 0) buy at i
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            // do nothing at i (so stay with sell[i-1]) or bought at/before i-1, sell at i
        }
        return sell[len - 1];
    }

}
