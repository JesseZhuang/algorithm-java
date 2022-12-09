package bit;

/**
 * LeetCode 191, easy, divide and conquer, bit.
 * <p>
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the
 * Hamming weight).
 * <p>
 * Note:
 * <p>
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be given as a signed integer type. It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the input represents the signed integer. -3.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * Example 2:
 * <p>
 * Input: n = 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * Example 3:
 * <p>
 * Input: n = 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The input must be a binary string of length 32.
 * <p>
 * <p>
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class NumberOf1Bits {
    // 0ms 39.4 Mb. Java standard library.
    public int hammingWeightJava(int n) {
        return Integer.bitCount(n);
    }

    // same as java standard library, Hacker's delight book, figure 5-1 and 5-2.
    public int hammingWeight(int n) {
        n = n - ((n >>> 1) & 0x55555555); // count adjacent 2 bits, store in 2 bit spaces
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);// count from previous results, store in 4 bit spaces
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = n + (n >>> 8);
        n = n + (n >>> 16);
        return n & 0x3f;
    }

    public int hackerDelight(int n) {
        // n & 0xAAAAAAAA >>> 1 is more natural, but generating two large constants in register, cost an instruction
        // if the machine lacks the and not instruction, evolves to a way to use one fewer instruction
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f); // masking can be omitted if no danger sum to carry over
        n = (n & 0x00ff00ff) + ((n >>> 4) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 4) & 0x0000ffff);
        return n;
    }

    // 1ms 42.3Mb. O(32), 32 bit integer.
    public int hammingWeight32(int n) {// treat n as unsigned int
        int weight = 0;
        for (int i = 0; i < 32; i++) {// while (n != 0), 2147483648 -> -2147483648 in java
            // arithmetic right shift
            if (n >> 1 << 1 != n) weight++; // if ((n&1) == 1)
            // logical riht shift does not add more 1s
            n = n >>> 1;
        }
        return weight;
    }
}
