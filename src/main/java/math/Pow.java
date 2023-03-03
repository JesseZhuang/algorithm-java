package math;

/**
 * LeetCode 50, medium, tags: math, recursion.
 * <p>
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * Constraints:
 * <p>
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n is an integer.
 * -10^4 <= x^n <= 10^4
 */
public class Pow {

    // recursive, 0ms, 40.6Mb. O(Lg n) time and space.
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) return 1 / x * myPow(1 / x, -(n + 1));
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    // 0ms, 41.1Mb. O(lgn) time O(1) space.
    public double myPowIter(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double pow = 1;
        while (n != 0) {
            if ((n & 1) != 0) pow *= x;
            x *= x;
            n >>>= 1;
        }
        return pow;
    }

}
