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
    // at every bit set to 1, replace it with a 0 then counting the number remaining bits to the right of it.
    // "[1]110" -> "[0]XXX", += dp[3]; "1[1]10" -> "1[0]XX", += dp[2]; "11[1]0" -> "11[0]X", already invalid
    // "[1]010" -> "[0]XXX", += dp[3]; "10[1]0" -> "10[0]X", += dp[1]; add 1010 itself
    public int findIntegers(int n) {
        int[] f = new int[32]; // fibonacci for 32 bits, not for int n, bit->num non-consecutive
        f[0] = 1; // f indicates total number of non-neg int wo consecutive ones in [0,1<<i)
        f[1] = 2;
        // ans(xxxx) = ans(xxx) + ans(xx) xxxx -> 0xxx + 10xx: f[4]=f[3]+f[2]
        for (int i = 2; i < 32; i++) f[i] = f[i - 1] + f[i - 2]; // i<32 not i<n
        int res = 0;
        boolean pre = false; // pre: whether previous bit was 1
        for (int k = 30; k >= 0; k--) { // n<10^9 only need to check [30,0] bit shifts, left most is sign bit
            if ((n & 1 << k) != 0) { // whether the kth bit is 1
                res += f[k];
                if (pre) return res; // do not count itself, no +1 since n itself has consecutive ones
                pre = true; // do not forget
            } else pre = false; // do not forget
        }
        return res + 1;
    }
}
