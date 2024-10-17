package math;

import com.google.common.math.LongMath;

/**
 * Stirling number calculations. The Stirling numbers of the first and second kind can be understood as inverses of
 * one another when viewed as triangular matrices.
 */
@SuppressWarnings("unused")
public class StirlingNum {
    final static int MOD = (int) 1e9 + 7;

    /**
     * first kind. unsigned c(n,k). s(n,k)=(-1)^(n-k)*c(n,k).
     * recurrence c(n,k)=(n-1)*c(n-1,k)+c(n-1,k-1)
     * number of permutations of n labelled objects with exactly k non-empty cycles(circles).
     * arrange 1,2,3 in a circle, two ways: 1,2,3 or 1,3,2.
     * For example, c(4,2)=11
     * 1-cycle and 3-cycle: 1,234;1,243; 2,134;2,143; 3,124;3,142; 4,123;4,132, total 8
     * two 2-cycles: 12,34; 13,24; 14,23, total 3
     */
    public static class First {
        public static int recur(int n, int k) {
            if (n < 0 || k < 0) throw new IllegalArgumentException("invalid: n,k should be non-negative");
            if (n == k) return 1;
            if (n < k || k == 0) return 0;
            // n>k && n>0 && k>0
            long res = (long) (n - 1) * recur(n - 1, k) % MOD;
            res = (res + recur(n - 1, k - 1)) % MOD;
            return (int) res;
        }

        public static int dp(int n, int k) {
            int[] dp = new int[k + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                int[] ndp = new int[k + 1];
                for (int j = 1; j <= k; j++)
                    ndp[j] = (int) ((long) dp[j] * (i - 1) % MOD + dp[j - 1]) % MOD;
                // insert i into the i-1 gaps/positions of the j circles or create a new circle
                dp = ndp;
            }
            return dp[k];
        }
    }

    /**
     * second kind. S(n,k).
     */
    public static class Second {
        /**
         * n*2^n, 2^n. repetitive calculations.
         * stirling number of the second kind, count the number of ways to
         * partition a set of n labelled objects into k nonempty unlabelled subsets.
         * recurrence: S(n,k) = k*S(n-1,k)+S(n-1,k-1), pick a set or in a new set
         *
         * @param n, n objects, distinguishable.
         * @param k, k non-empty subsets. non-distinguishable.
         * @return strling number of the second kind.
         */
        public static int recur(int n, int k) {
            if (n < 0 || k < 0) throw new IllegalArgumentException("invalid: n,k should be non-negative");
            if (n == k) return 1;
            else if (n < k || k == 0) return 0;
            // n>k && n>0 && k>0
            long res = (long) k * recur(n - 1, k) % MOD; // add to one of the k existing subsets
            res = (res + recur(n - 1, k - 1)) % MOD; // create a new subset
            return (int) res;
        }

        public static int dp(int n, int k) {
            int[] dp = new int[k + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                int[] ndp = new int[k + 1];
                for (int j = 1; j <= k; j++)
                    ndp[j] = (int) ((long) dp[j] * j % MOD + dp[j - 1]) % MOD;
                dp = ndp;
            }
            return dp[k];
        }

        // 1/k! * sigma (-1)^(k-i)*(k choose i)*i^n. O(>nk, repetitive), O(1).
        public static int iter1(int n, int k) {
            int i1 = (k % 2 == 0) ? 1 : -1;
            long res = 0;
            for (int i = 0; i <= k; i++, i1 *= -1)
                res += i1 * LongMath.binomial(k, i) * LongMath.pow(i, n);
            res /= LongMath.factorial(k);
            res %= MOD;
            return (int) res;
        }

        // sigma (-1)^(k-i)*i^n/((k-i)!i!) where i in [0,k], this equation needs double. O(nk), O(1).
        public static int iter2(int n, int k) {
            double res = 0;
            Factorial f = new Factorial();
            int b1k = k;
            long t2, b1, b2 = 1;
            for (int i = 0, t1 = (k % 2 == 0) ? 1 : -1; i <= k; i++, t1 *= -1, b1k--, b2 *= i) {
                t2 = (long) Math.pow(i, n);
                b1 = f.factorialInt(b1k);
                res = (res + (double) (t1 * t2) / (b1 * b2) % MOD) % MOD;
            }
            return (int) (res + 0.5);
        }
    }
}
