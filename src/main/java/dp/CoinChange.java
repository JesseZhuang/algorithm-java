package dp;

import java.util.ArrayDeque;
import java.util.Deque;

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

    //45 ms 54.2 Mb. O(M*N) time O(M) space for visited, average space for queue < M.
    public static int coinChangeBFS(int[] coins, int amount) {
        int count = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[amount + 1];
        queue.add(amount);
        visited[amount] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) { // O(size)
                int cur = queue.remove();
                if (cur == 0) return count;
                for (int coin : coins) { // O(N)
                    int next = cur - coin;
                    if (next >= 0 && !visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        // coinChangeBFS(new int[]{1}, 100);
        // coinChangeBFS(new int[]{1, 2, 5}, 100);
        System.out.println(coinChangeBFS(new int[]{1, 3, 5, 6, 7, 10, 72}, 100));
    }
}
