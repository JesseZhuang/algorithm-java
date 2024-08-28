package dp;

/**
 * LeetCode 600, hard, tags: dynamic programming.
 * <p>
 * Given a positive integer n, return the number of the integers in the range [0, n]
 * whose binary representations do not contain consecutive ones.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5
 * Output: 5
 * Explanation:
 * Here are the non-negative integers <= 5 with their corresponding binary representations:
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 2
 * Example 3:
 * <p>
 * Input: n = 2
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^9
 */
public class NNIWOCOnes {
    // solution 1, dp, O(32) O(1) time and space
    // other O(N), O(lgN) methods see https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
    public int findIntegers(int n) {
        int[] dp = new int[32];
        dp[0] = 1;
        dp[1] = 2;
        // ans(xxxx) = ans(xxx) + ans(xx) xxxx -> 0xxx + 10xx
        for (int i = 2; i < 32; i++) dp[i] = dp[i - 1] + dp[i - 2];
        int res = 0;
        boolean pre = false; // pre: previous bit was 1?
        for (int k = 30; k >= 0; k--) {
            if ((n & (1 << k)) != 0) { // is the ith bit set?
                res += dp[k];
                if (pre) return res;
                pre = true;
            } else pre = false;
        }
        return res + 1; // e.g., 1010 dp[3](1000)+dp[1](10)+1(1010 itself)
    }
}
