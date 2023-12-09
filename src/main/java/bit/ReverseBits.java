package bit;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 190, easy, tags: bit, divide and conquer.
 * Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * Note:
 * <p>
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case,
 * both input and output will be given as a signed integer type. They should not affect your implementation,
 * as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 2 above, the input represents the signed integer -3 and
 * the output represents the signed integer -1073741825.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 00000010100101000001111010011100
 * Output:    964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 00000010100101000001111010011100
 * represents the unsigned integer 43261596,
 * so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * Example 2:
 * <p>
 * Input: n = 11111111111111111111111111111101
 * Output:   3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
 * so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The input must be a binary string of length 32
 * <p>
 * <p>
 * Follow up: If this function is called many times, how would you optimize it?
 * Also see number of one bit (lc 191), should be along the thinking of caching. caching for 8 bits, space 2^8(256). then
 * shift 8 bits and look up the cache 4 times for a 32-bit number: 4 time, 256 space.
 */
public class ReverseBits {
    // solution 1, 1ms, 42.16 Mb. O(32) time, O(1) space.
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    // solution 2, divide and conquer, O(lg32==5) time O(1) space. 1ms, 42.7Mb.
    public int reverseBits2(int n) {
        n = (n >>> 16) | (n << 16); // mirror flip 16 bits, mask 0xffff0000 0x0000ffff is optional
        n = ((n >>> 8) & 0x00ff00ff) | ((n << 8) & 0xff00ff00); // mirror flip 8 bits
        n = ((n >>> 4)) & 0x0f0f0f0f | ((n << 4) & 0xf0f0f0f0);
        n = ((n >>> 2)) & 0x33333333 | ((n << 2) & 0xcccccccc);
        n = ((n >>> 1)) & 0x55555555 | ((n << 1) & 0xaaaaaaaa);
        return n;
    }

    private final Map<Byte, Integer> cache = new HashMap<>();

    // follow up with cache
    public int reverseBitsFU(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) // convert int into 4 bytes
            bytes[i] = (byte) ((n >>> 8 * i) & 0xFF);
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res <<= 8;
            res += reverseByte(bytes[i]); // reverse per byte
        }
        return res;
    }

    private int reverseByte(byte b) {
        if (cache.containsKey(b)) return cache.get(b);
        int res = 0;
        byte temp = b;
        for (int i = 0; i < 8; i++) {
            res <<= 1;
            res += b & 1;
            b >>>= 1;
        }
        cache.put(temp, res);
        return res;
    }
}
