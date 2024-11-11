package bit;

/**
 * LintCode 179, medium, tags: bit.
 * <p>
 * Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between
 * i and j in N equal to M (e g , M becomes a substring of N start from i to j)
 * <p>
 * In the function, the numbers N and M will given in decimal, you should also return a decimal number.
 * You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， you can
 * assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because
 * M could not fully fit between bit 3 and bit 2.
 * <p>
 * Assumption: i-j should just fit M, otherwise if i=2,j=6, M has 3 bits,e.g., 111. It could be unclear which 3 bits
 * to set.
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input: N=(10_000_000_000)2 M=(10101)2 i=2 j=6: 1024,21,2,6
 * Output: N=(10001010100)2, 1108
 * Example 2:
 * <p>
 * Input: N=(10000000000)2 M=(11111)2 i=2 j=6
 * Output: N=(10001111100)2
 * <p>
 * Challenge
 * Minimum number of operations?
 */
@SuppressWarnings("unused")
public class UpdateBits {
    // 1, 1. straight forward method O(j-i), O(1). set each bit between i and j.
    static class Solution {
        public int updateBits(int n, int m, int i, int j) {
            int mask;
            if (j < 31) mask = ~((1 << (j + 1)) - (1 << i));
            else mask = (1 << i) - 1;
            return m << i | mask & n; // clear i-j in n then put m in there
        }
    }
}
