package dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 322, medium, tags: array, dynamic programming, bfs.
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
 * 1 <= coins.length <= 12, N
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4, M
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange tbt = new CoinChange();
        // coinChangeBFS(new int[]{1}, 100);
        // coinChangeBFS(new int[]{1, 2, 5}, 100);
        System.out.println(tbt.coinChangeBFS(new int[]{1, 3, 5, 6, 7, 10, 72}, 100));
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        for (int i = 0; i < q.size() && i < 10; i++) { // q.size() evaluated dynamically every iteration
            System.out.println(i + ", " + q.remove());
            q.add(2);
            q.add(3);
        }
    }

    // solution 1, 10 ms 44.57 Mb. O(N*M) time, O(M) space. coins length is N, amount is M.
    public int coinChangeDP1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];// dp[i]: min coins for amount i
        for (int i = 1; i < dp.length; i++) dp[i] = Integer.MAX_VALUE; // dp[0]=0
        // dp[i]=min(dp[i-coins[j])+1, j:[0,n-1] i>=coins[j]
        for (int coin : coins)
            for (int i = coin; i <= amount; i++)
                if (dp[i - coin] != Integer.MAX_VALUE) // visited previously
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //solution 2, 22 ms 44.69 Mb. O(M*N) time O(M) space for visited, average space for queue < M.
    public int coinChangeBFS(int[] coins, int amount) {
        int count = 0;
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[amount + 1];
        q.add(amount);
        visited[amount] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) { // O(size)
                int cur = q.remove();
                if (cur == 0) return count;
                for (int coin : coins) { // O(N)
                    int next = cur - coin;
                    if (next < 0 || visited[next]) continue; // check next to avoid out of bound
                    q.add(next);
                    visited[next] = true;
                }
            }
            count++; //important, ++ after explored all coins with all in the q
        }
        return -1;
    }


}
