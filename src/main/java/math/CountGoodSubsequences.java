package math;

/**
 * <a href="https://leetcode.com/problems/count-the-number-of-good-subsequences/">LeetCode 2539</a>, hard,
 * tags: string, hash table, math, combinatorics, counting.
 */
public final class CountGoodSubsequences {
    private CountGoodSubsequences() {}

    private static final int MOD = 1_000_000_007;
    private static final int N = 10001;
    private static final long[] f = new long[N];
    private static final long[] g = new long[N];

    static {
        f[0] = g[0] = 1;
        for (int i = 1; i < N; i++) {
            f[i] = f[i - 1] * i % MOD;
            g[i] = pow(f[i], MOD - 2);
        }
    }

    private static long pow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }

    private static long comb(int n, int k) {
        return f[n] % MOD * g[k] % MOD * g[n - k] % MOD;
    }

    /**
     * Enumerate frequency f from 1 to max count, compute product of (C(count,f)+1) - 1 for each f.
     * Time O(n), Space O(n).
     */
    public static int countGoodSubsequences(String s) {
        int[] cnt = new int[26];
        int maxCnt = 0;
        for (char c : s.toCharArray()) maxCnt = Math.max(maxCnt, ++cnt[c - 'a']);
        long ans = 0;
        for (int i = 1; i <= maxCnt; i++) {
            long x = 1;
            for (int v : cnt) {
                if (v >= i) x = x * ((comb(v, i) + 1) % MOD) % MOD;
            }
            ans = (ans + x - 1) % MOD;
        }
        return (int) ans;
    }
}
