package bit;

/**
 * <p>
 * LeetCode 371. Easy. Tags: math, bit.
 * <p>
 * Calculate the sum of two integers a and b, but you are
 * not allowed to use the operator + and -.
 * <p>
 * Example: Given a = 1 and b = 2, return 3.
 * <p>
 * Tags: Bit Manipulation.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>O(carry) time, O(1) space, bit iterative or recursive, 0ms, 7%.</b>
 * <li>O(32) time, O(1) space, bit 32 one by one radix, 0 ms, 7%.
 * </ul>
 * Constraints:
 * <p>
 * -1000 <= a, b <= 1000
 */
public class SumTwoIntegers {

    // solution 1, iterative, 0ms, 39.35Mb. O(carry) time, O(1) space.
    public int getSumIterative(int a, int b) {
        if (a == 0 || b == 0) return a | b;
        while (b != 0) {
            int carry = a & b;
            a ^= b; // store sum without carry in a
            b = carry << 1; // store carry in b
        }
        return a;
    }

    public int getSumRecursive(int a, int b) {
        if (a == 0) return b;
        return (b == 0) ? a : getSumRecursive(a ^ b, (a & b) << 1);
    }

    public int getSumRadix32(int a, int b) {
        if (a == 0) return b;
        if (b == a) return a;
        final int R = 32;
        int carry = 0;
        int mask = 0x1;
        int result = 0;
        for (int i = 0; i < R; i++, mask <<= 1, carry <<= 1) {
            int am = a & mask, bm = b & mask;
            result |= am ^ bm ^ carry;
            carry = (am & bm) | (carry & (am | bm));
        }
        return result;
    }
}
