package math;

import java.math.BigInteger;

/**
 * LeetCode 172. Easy.
 * <p>
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Note: Your solution should be in logarithmic time complexity.
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>recursive or iterative, O(log5_n) time, O(1) space.
 * </ul>
 */
public class FactorialTrailingZeroes {
    /** O log5_n, not tail recursive */
    public int trailingZeroes(int n) {
        /*
         * wrong, thought only 2,5,10 will produce trailing zeroes. 25! has 6
         * trailing 0s (not 5), because 25*4 = 100;
         */
        // return n / 5;

        // may not be accurate
        // return n / 5 + (int) (Math.log(n) / Math.log(5)) - 1;

        return n <= 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    /** tail recursive */
    public int trailingZeroesRecursive(int n) {
        return helper(n, 0);
    }

    private int helper(int n, int acc) {
        if (n <= 0)
            return acc;
        return helper(n / 5, acc + n / 5);
    }
    /** iterative */
    public int trailingZeroesIterative(int n) {
        int acc = 0;
        while (n > 0) {
            acc += n / 5;
            n /= 5;
        }
        return acc;
    }

    public int trailingZeroesIterative2(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *= 5) count += n/i;
        return count;
    }

    public static void main(String[] args) {
        int n = 126;
        for (int i = 1; i < n; i++) {
            System.out.print(i + " ");
            BigInteger f = BigInteger.valueOf(1);
            for (int j = 1; j <= i; j++) f = f.multiply(BigInteger.valueOf(j));
            /*
             * results after 20! seem to be wrong with BigInteger(String val,
             * int radix) and converting the long variable to hex or binary
             * string.
             */
            System.out.println(f);
        }
    }
}

