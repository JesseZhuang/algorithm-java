package bit;

/**
 * LeetCode 29, medium, tags: math, bit manipulation.
 * <p>
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would
 * be truncated to 8, and -2.7335 would be truncated to -2.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed
 * integer range: [−2^31, 2^31 − 1]. For this problem, if the quotient is strictly greater than 2^31 - 1,
 * then return 2^31 - 1, and if the quotient is strictly less than -2^31, then return -2^31.
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 * <p>
 * Constraints:
 * <p>
 * -2^31 <= dividend, divisor <= 2^31 - 1
 * divisor != 0
 */
public class DivideInt {

    // 1ms, 39.5 Mb. O(lgN^2) time, O(1) space.
    public static int divide(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1; // the only overflow case
        int a = Math.abs(dividend), b = Math.abs(divisor), res = 0, x;
        while (a - b >= 0) { // O(lgN)
            for (x = 0; a - (b << x << 1) >= 0; x++) ; // O(lgN), b << (x+1) wrong, 1 << 32 == 1, 1 << 31 << 1 == 0
            res += 1 << x;
            a -= b << x;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

    // O(32? probably also lgN^2) time, O(1) space. 1ms, 39.8Mb.
    public int divide2(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;
        int a = Math.abs(dividend), b = Math.abs(divisor), res = 0;
        for (int x = 31; x >= 0; x--)
            if ((a >>> x) - b >= 0) {
                res += 1 << x;
                a -= b << x;
            }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE); // 0x7fff_ffff
//        System.out.println(Integer.MIN_VALUE); // 0x8000_0000 - 2^31, 1<<31
//        System.out.println(Integer.toHexString(1 << 31)); // 0x8000_0000 - 2^31, 1<<31
//        System.out.println((Math.abs(1 << 31)));
//        System.out.println((Math.abs((1 << 31) - 1)));
//        System.out.println((1 << 31 - 1) == (1 << 30));
        System.out.println(divide(1 << 31, 1)); // x stops at 31, res == 1<<31, a ==0, b == 1
//        System.out.println(divide(1 << 31, 2));
//        System.out.println(2 << 0 << 1);
//        System.out.println(2 << 1 << 1);
//        System.out.println(2 << 2 << 1);
//        System.out.println(divide(-15, 2));
//        System.out.println(divide(15, 2)); // 4+2+1 == 7
        // Java defines shift to mask (wrap around) to the 0-31 range
//        System.out.println(1 << 32);
//        System.out.println(1 << 31 << 1);
    }
}
