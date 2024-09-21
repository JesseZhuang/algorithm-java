package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 279, medium, tags: dynamic programming, math, bfs.
 * <p>
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * <p>
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer
 * with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^4
 */
public class PerfectSquares {

    static List<Integer> dp = new ArrayList<>(List.of(0));

    // solution 1, Legendre's three-square Lagrange's four-square theorem
    // O(sqrt(N)) time, O(1) space, 1ms, 39.32Mb.
    public static int numSquares1(int n) { // 1,2,3,4 possibilities
        int sr = (int) Math.sqrt(n);
        if (sr * sr == n) return 1;
        while (n % 4 == 0) // 4^a(8b+7) O(log_4 n) time
            n /= 4;
        if (n % 8 == 7) return 4;
        for (int i = 1; i * i <= n; i++) { // O(sqrt(n)) time
            int sq = i * i, base = (int) Math.sqrt(n - sq);
            if (base * base == n - sq) return 2;
        }
        return 3;
    }

    // solution 2, dp, O(N*sqrt(N)) time, O(N) space. 3ms, 40.73Mb. with static dp, 3ms, 39.3MB.
    public static int numSquares2(int n) {
        // dp [0, 1, 2, 3, 1, 2, 3, 4, 2, 1, 2, 3, 3] for 12
        while (dp.size() <= n) { // grow dp to size n+1
            int m = dp.size(), next = Integer.MAX_VALUE;
            // sum of dp[m-i*i] and a perfect square number i*i, iterate and find min for next number to add to dp
            for (int i = 1; i * i <= m; i++) next = Math.min(next, dp.get(m - i * i) + 1);
            dp.add(next); // do not forget, otherwise infinite loop
        }
        return dp.get(n);
    }

    public static void main(String[] args) {
        System.out.println(numSquares2(12));
    }
}
