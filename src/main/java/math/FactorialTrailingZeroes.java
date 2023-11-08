package math;

import java.math.BigInteger;

/**
 * LeetCode 172, easy, tags: math.
 * <p>
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Note: Your solution should be in logarithmic time complexity.
 * <p>
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Example 3:
 * <p>
 * Input: n = 0
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 10^4
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>recursive or iterative, O(log5_n) time, O(1) space.
 * </ul>
 */
public class FactorialTrailingZeroes {
    /**
     * solution 1, O(log5_n) time and O(1) space. iterative, 0ms, 39.73Mb.
     */
    public int trailingZeroesIterative(int n) {
        int acc = 0;
        while (n > 0) {
            acc += n / 5;
            n /= 5;
        }
        return acc;
    }

    /**
     * solution 2: O(log5_n) time and space. tail recursive, 0ms, 39.19 Mb.
     */
    public int trailingZeroesTR(int n) {
        return helper(n, 0);
    }

    private int helper(int n, int acc) {
        if (n <= 0)
            return acc;
        return helper(n / 5, acc + n / 5);
    }

    /**
     * O(log5_n) time and space. normal recursive, 0ms, 39.59 Mb.
     */
    public int trailingZeroesR(int n) {
        // may not be accurate
        // return n / 5 + (int) (Math.log(n) / Math.log(5)) - 1;
        return n <= 0 ? 0 : n / 5 + trailingZeroesR(n / 5);
    }

    public int trailingZeroesIterative2(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *= 5) count += n / i;
        return count;
    }

    public static void main(String[] args) {
        int n = 126; // show factorials 1 -> 126
        for (int i = 1; i < n; i++) {
            System.out.print(i + " ");
            BigInteger f = BigInteger.valueOf(1);
            for (int j = 1; j <= i; j++) f = f.multiply(BigInteger.valueOf(j));
            System.out.println(f);
        }
    }
}

