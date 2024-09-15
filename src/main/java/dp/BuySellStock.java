package dp;

/**
 * <p>
 * LeetCode 121, easy, tags: array, dp.
 * <p>
 * Best Time to Buy and Sell Stock，Easy. Say you have an array for which
 * the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5 max. difference = 6-1 = 5 (not
 * 7-1 = 6, as selling price needs to be larger than buying price)
 * <p>
 * Example 2: Input: [7, 6, 4, 3, 1] Output: 0. In this case, no transaction is
 * done, i.e. max profit = 0.
 * <p>
 * Tags: Array, Dynamic Programming.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>use peak array, 2ms 46%, O(n) time, O(n) space.
 * <li>one scan maintain two variables (minSoFar, res), O(n) time, O(1) space.
 * <li>Kadane's algorithm, wikipedia maximum sub-array problem, O(n) time, O(1) space.
 * </ul>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */
public class BuySellStock {
    // solution 1, 2 ms 60.9 MB. O(n) time and O(1) space.
    public int maxProfitMinPrice(int[] prices) {
        int res = 0, minSoFar = Integer.MAX_VALUE;
        for (int price : prices) {
            minSoFar = Math.min(minSoFar, price);
            res = Math.max(res, price - minSoFar); // max profit if sold at current index
        }
        return res;
    }

    // solution 2, 3ms, 61 Mb. O(n) time O(1) space.
    public int maxProfitKadane(int[] prices) { // 7 1 5 3 6 4
        int maxHere = 0, res = 0;
        for (int i = 1; i < prices.length; i++) {
            // max profit if sold at current index, important first item 0, if loosing money, no buy no sell
            maxHere = Math.max(0, maxHere + (prices[i] - prices[i - 1])); // 0 4 2 5 3
            res = Math.max(maxHere, res); // 0 4 4 5 5
        }
        return res; // 5
    }
}
