package dp;

/**
 * <a href="https://leetcode.com/problems/count-ways-to-build-good-strings/">LeetCode 2466</a>, medium,
 * tags: dynamic programming.
 */
public final class CountWaysGoodStrings {
    private CountWaysGoodStrings() {}

    /**
     * DP. Time O(high), Space O(high).
     */
    public static int countGoodStrings(int low, int high, int zero, int one) {
        int mod = 1_000_000_007;
        long[] dp = new long[high + 1];
        dp[0] = 1;
        for (int i = 1; i <= high; i++) {
            if (i >= zero) dp[i] += dp[i - zero];
            if (i >= one) dp[i] += dp[i - one];
            dp[i] %= mod;
        }
        long res = 0;
        for (int i = low; i <= high; i++) res += dp[i];
        return (int) (res % mod);
    }
}
