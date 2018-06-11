package dp;

/**
 * <p>
 * LeetCode 121. Easy.
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
 * <li>one scan maintain two variables, O(n) time, O(1) space.
 * <li>Kadane's algorithm, wikipedia maximum sub-array problem, basically same
 * as above.
 * </ul>
 */
public class BuySellStock {
    /** 2ms 46% */
    public int maxProfitPeakArray(int[] prices) {
        // peak (max) for prices array in range [i, end]
        int[] maxOf = new int[prices.length];
        int max = Integer.MIN_VALUE;
        for (int i = prices.length - 1; i > 0; i--)
            max = maxOf[i] = Math.max(max, prices[i]);
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++)
            profit = Math.max(profit, maxOf[i + 1] - prices[i]);
        // max profit if purchased on day i
        return profit;
    }

    /** 3 ms 12.7% */
    public int maxProfitMinPrice(int[] prices) {
        int maxPro = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            // max profit if sold at current index
            maxPro = Math.max(maxPro, prices[i] - minPrice);
        }
        return maxPro;
    }

    public int maxProfitKadane(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            // max profit if sold at current index
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    @Deprecated
    private int maxProfit2Pointer(int[] prices) {
        int profit = 0;
        int i = 0, j = prices.length - 1;
        while (i < j) {
            while (i < j - 1 && prices[i + 1] < prices[i])
                i++;
            while (i < j - 1 && prices[j - 1] > prices[j])
                j--;
            // flaw for { 3, 2, 6, 5, 0, 3 }
            profit = Math.max(profit, prices[j] - prices[i++]);
        }
        return profit;
    }
}
