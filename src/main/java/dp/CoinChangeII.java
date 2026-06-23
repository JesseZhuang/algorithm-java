package dp;

/**
 * LeetCode 518, medium, tags: array, dynamic programming.
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up
 * by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 * <p>
 * Input: amount = 0, coins = [7]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= coins.length <= 300, N
 * 1 <= coins[i] <= 5000
 * 0 <= amount <= 5000, M
 */
public final class CoinChangeII {

    private CoinChangeII() {
    }

    // solution 1, O(N*M) time, O(M) space. N: coins length, M: amount.
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // dp[i]: number of combinations for amount i
        dp[0] = 1;
        for (int coin : coins) // outer loop on coins avoids counting permutations
            for (int i = coin; i <= amount; i++) // O(M)
                dp[i] += dp[i - coin];
        return dp[amount];
    }

    // solution 2, O(N*M) time, O(N*M) space. dp[i][j]: combinations using first i coins for amount j.
    public static int change2D(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1]; // dp[i][j]: combinations using first i coins for amount j
        for (int i = 0; i <= n; i++) dp[i][0] = 1; // one way to make amount 0: use no coins
        for (int i = 1; i <= n; i++) // O(N)
            for (int j = 1; j <= amount; j++) { // O(M)
                dp[i][j] = dp[i - 1][j]; // not using coin i
                if (j >= coins[i - 1])
                    dp[i][j] += dp[i][j - coins[i - 1]]; // using coin i at least once
            }
        return dp[n][amount];
    }
}
