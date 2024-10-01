package dp;

import java.util.Arrays;

/**
 * LeetCode 3260, hard, tags: math, string, dp, greedy, number theory.
 * Companies: Reddit.
 * <p>
 * You are given two positive integers n and k.
 * <p>
 * An integer x is called k-palindromic if:
 * <p>
 * x is a palindrome
 * x is divisible by k.
 * Return the largest integer having n digits (as a string) that is k-palindromic.
 * <p>
 * Note that the integer must not have leading zeros.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, k = 5
 * <p>
 * Output: "595"
 * <p>
 * Explanation:
 * <p>
 * 595 is the largest k-palindromic integer with 3 digits.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1, k = 4
 * <p>
 * Output: "8"
 * <p>
 * Explanation:
 * <p>
 * 4 and 8 are the only k-palindromic integers with 1 digit.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 5, k = 6
 * <p>
 * Output: "89898"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 105
 * 1 <= k <= 9
 * <p>
 * Hint 1
 * It must have a solution since we can have all digits equal to k.
 * Hint 2
 * Use string dp, store modulus along with length of number currently formed.
 * Hint 3
 * Is it possible to solve greedily using divisibility rules?
 */
@SuppressWarnings("unused")
public class LargestPalindromeDivK {

    // dp solution 1, 100*n, 10*n. 177ms, 60.58mb.
    static class Solution {
        int[][] dp; // init -1, dp[ind][premod]: at ind,premod whether there is a digit make n divisible by k
        int[] power, res;
        int n, k;

        public int find(int ind, int premod, int half) {
            if (ind == half) return premod == 0 ? 1 : 0; // Base case
            if (dp[ind][premod] != -1) return dp[ind][premod];
            for (int digit = 9; digit >= 0; digit--) {
                int newmod = premod;
                if (n % 2 != 0 && ind == half - 1) newmod += (digit * power[ind]) % k;
                else {
                    newmod += (digit * power[ind]) % k;
                    newmod += (digit * power[n - 1 - ind]) % k; // palindrome mirror
                }
                newmod %= k;
                int flag = find(ind + 1, newmod, half);
                if (flag == 1) {
                    res[ind] = digit;
                    break;
                }
            }
            if (res[ind] == -1) dp[ind][premod] = 0;
            else dp[ind][premod] = 1;
            return dp[ind][premod];
        }

        public String largestPalindrome(int n, int k) {
            this.n = n;
            this.k = k;
            int half = (n + 1) / 2;
            power = new int[n]; // powers of 10 mod k, see rolling hash
            power[0] = 1 % k;
            for (int i = 1; i < n; i++) power[i] = (power[i - 1] * 10) % k;
            res = new int[n];
            Arrays.fill(res, -1);
            dp = new int[half][10];
            for (int[] row : dp) Arrays.fill(row, -1);

            int prev = 0;
            find(0, prev, half);
            char[] ans = new char[n];
            for (int i = 0; i < half; i++) {
                ans[i] = (char) ('0' + res[i]);
                ans[n - 1 - i] = ans[i];
            }
            return new String(ans);
        }
    }

    // wikipedia divisibility rule, cannot memorize ... n, 1. 11ms, 44.92mb.
    static class Solution2 {
        public String largestPalindrome(int n, int k) {
            char[] res = new char[n];
            Arrays.fill(res, '9');
            if (k == 1 || k == 3 || k == 9) return String.valueOf(res); // 9999
            if (k == 2) {
                res[0] = res[n - 1] = '8'; // 8..9..8
            } else if (k == 4) {
                res[0] = res[n - 1] = '8'; //88..9..88
                if (n > 1) res[1] = res[n - 2] = '8';
            } else if (k == 5) res[0] = res[n - 1] = '5';
            else if (k == 8) { // 888..9...888
                res[0] = res[n - 1] = '8';
                if (n > 1) res[1] = res[n - 2] = '8';
                if (n > 2) res[2] = res[n - 3] = '8';
            } else if (k == 6) {
                if (n <= 2) return "6".repeat(n); // 6, 66
                if ((n & 1) == 1) res[0] = res[n >> 1] = res[n - 1] = '8';  // 88.9.8.9.88
                else {
                    res[0] = res[n - 1] = '8';
                    res[(n >> 1) - 1] = res[n >> 1] = '7';
                }
            } else if (k == 7) {
                if (n <= 2) return "7".repeat(n);
                for (int i = 9; i >= 0; i--) {
                    if ((n & 1) == 0) res[(n >> 1) - 1] = (char) (i + '0');
                    res[n >> 1] = (char) (i + '0');
                    if (isDivisibleBy7(String.valueOf(res))) return String.valueOf(res);
                }
            }
            return String.valueOf(res);
        }

        private boolean isDivisibleBy7(String s) {
            int mod = 0;
            for (char digit : s.toCharArray())
                mod = (mod * 10 + (digit - '0')) % 7;
            return mod == 0;
        }
    }


}
