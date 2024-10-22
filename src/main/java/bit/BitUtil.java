package bit;

/**
 * Bitwise useful tricks.
 */
@SuppressWarnings("unused")
public class BitUtil {
    /**
     * base 2 logarithm, round down (floor). which gives the position for the highest 1 bit.
     * for example, log2(4) is 2, highest 1 bit of 4 is at 2nd from right (0 based indexing)
     * ilog2 == bit length - 1
     *
     * @param n input int.
     * @return base 2 logarithm
     */
    public static int ilog2(int n) {
        // log2 of 0 is undefined. otherwise same to rust ilog2() for any integer (0,INT_MAX]
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    public static int bitLength(int n) {
        return 32 - Integer.numberOfLeadingZeros(n);
    }

    /**
     * n & -n, two's complement property. for example 2, 0b10,; -2: 0xffff_fffe
     *
     * @param n input int.
     * @return integer with only the lowest 1 bit set to one.
     */
    public static int lowest1bit(int n) {
        return Integer.lowestOneBit(n);
    }

    /**
     * Bit wise negate.
     *
     * @param n input number
     * @return bit wise negate ~n == -n-1 == -(n+1)
     */
    public static int negate(int n) {
        return ~n;
    }
}
