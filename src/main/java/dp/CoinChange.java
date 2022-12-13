package dp;

/**
 * LeetCode 332, medium, tags: array, dynamic programming, bfs.
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: coins = [1], amount = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */
public class CoinChange {
    // 24 ms 45.2 Mb. O(N*M) time, O(M) space. coins length is N, amount is M.
    public int coinChangeDP1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];// min coins consist number i
        for (int i = 1; i < dp.length; i++) dp[i] = Integer.MAX_VALUE;
        for (int coin : coins)
            for (int i = coin; i <= amount; i++)
                if (dp[i - coin] != Integer.MAX_VALUE) // check on possible overflow problem
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
