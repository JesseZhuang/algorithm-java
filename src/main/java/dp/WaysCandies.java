package dp;

import com.google.common.math.LongMath;
import math.Factorial;

/**
 * LeetCode 1692, hard, tags: dp.
 * <p>
 * There are n unique candies (labeled 1 through n) and k bags. You are asked to distribute all the candies into the
 * bags such that every bag has at least one candy.
 * <p>
 * There can be multiple ways to distribute the candies. Two ways are considered different if the candies in one bag
 * in the first way are not all in the same bag in the second way. The order of the bags and the order of the candies
 * within each bag do not matter.
 * <p>
 * For example, (1), (2,3) and (2), (1,3) are considered different because candies 2 and 3 in the bag (2,3) in the
 * first way are not in the same bag in the second way (they are split between the bags (2) and (1,3)). However,
 * (1), (2,3) and (3,2), (1) are considered the same because the candies in each bag are all in the same bags
 * in both ways.
 * <p>
 * Given two integers, n and k, return the number of different ways to distribute the candies. As the answer
 * may be too large, return it modulo 10^9 + 7.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: n = 3, k = 2
 * Output: 3
 * Explanation: You can distribute 3 candies into 2 bags in 3 ways:
 * (1), (2,3)
 * (1,2), (3)
 * (1,3), (2)
 * Example 2:
 * <p>
 * Input: n = 4, k = 2
 * Output: 7
 * Explanation: You can distribute 4 candies into 2 bags in 7 ways:
 * (1), (2,3,4)
 * (1,2), (3,4)
 * (1,3), (2,4)
 * (1,4), (2,3)
 * (1,2,3), (4)
 * (1,2,4), (3)
 * (1,3,4), (2)
 * Example 3:
 * <p>
 * Input: n = 20, k = 5
 * Output: 206085257
 * Explanation: You can distribute 20 candies into 5 bags in 1881780996 ways. 1881780996 modulo 109 + 7 = 206085257.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= n <= 1000
 * <p>
 * see {@link math.PossibleWaysEvent}, difference is every bag has at least one candy, whereas, there can be empty
 * stages without any performers.
 * <p>
 * see https://en.wikipedia.org/wiki/Twelvefold_way, stirling number of the second kind, count the number of ways to
 * partition a set of n labelled objects into k nonempty unlabelled subsets.
 * recurrence: s(n+1,k) = k*s(n,k)+s(n,k-1), pick a set or in a new set
 */
@SuppressWarnings("unused")
public class WaysCandies {
    // dp, nk, k.
    // not easy to collapse the other dimension, j++: 1 more ball into existing bag dp
    static class Solution2 {
        public int waysToDistribute(int n, int k) {
            final int mod = (int) 1e9 + 7;
            int[] dp = new int[k + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) { // or use ndp as in PossibleWaysEvent
                for (int j = 1, prpc = (i == 1) ? dp[0] : 0, pr; j <= k; j++) {
                    pr = dp[j]; // j increment: dp[i-1][j] -> dp[i-1][j-1], so prev row -> prev row prev col
                    dp[j] = (int) ((long) dp[j] * j % mod + prpc) % mod; // dp[i]*j may overflow
                    prpc = pr;
                }
            }
            return dp[k];
        }
    }


    // f[i][j] as the number of different ways to distribute i candies to j bags. f[0][0]=1
    // the ith candy can be in a new bag or existing bag (choose one of the j bags)
    // f[i][j] = f[i-1][j-1] + f[i-1][j] * j
    // nk, nk.
    static class Solution {
        public int waysToDistribute(int n, int k) {
            final int mod = (int) 1e9 + 7;
            int[][] f = new int[n + 1][k + 1];
            f[0][0] = 1;
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= k; j++)
                    f[i][j] = (int) ((long) f[i - 1][j] * j % mod + f[i - 1][j - 1]) % mod;
            return f[n][k];
        }
    }

    // 1/k! * sigma (-1)^(k-i)*(k choose i)*i^n
    static class Solution3 {
        public int waysToDistribute(int n, int k) {
            final int mod = (int) 1e9 + 7;
            int i1 = (k % 2 == 0) ? 1 : -1;
            long res = 0;
            for (int i = 0; i <= k; i++, i1 *= -1)
                res += i1 * LongMath.binomial(k, i) * LongMath.pow(i, n);
            res /= LongMath.factorial(k);
            res %= mod;
            return (int) res;
        }
    }

    // sigma (-1)^(k-i)*i^n/((k-i)!i!) where i in [0,k], this equation needs double
    static class Solution4 {
        public int waysToDistribute(int n, int k) {
            final int mod = (int) 1e9 + 7;
            double res = 0;
            Factorial f = new Factorial();
            int b1k = k;
            long t2, b1, b2 = 1;
            for (int i = 0, t1 = (k % 2 == 0) ? 1 : -1; i <= k; i++, t1 *= -1, b1k--, b2 *= i) {
                t2 = (long) Math.pow(i, n);
                b1 = f.factorialInt(b1k);
                res = (res + (double) (t1 * t2) / (b1 * b2));
            }
            return (int) ((long) res % mod); // res can get large ...
        }
    }

}
