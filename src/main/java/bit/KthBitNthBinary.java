package bit;

/**
 * LeetCode 1545, medium, tags: string, recursion, simulation.
 * <p>
 * Given two positive integers n and k, the binary string Sn is formed as follows:
 * <p>
 * S1 = "0"
 * Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all
 * the bits in x (0 changes to 1 and 1 changes to 0).
 * <p>
 * For example, the first four strings in the above sequence are:
 * <p>
 * S1 = "0"
 * S2 = "0_1_1"
 * S3 = "011_1_001"
 * S4 = "0111001_1_0110001"
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, k = 1
 * Output: "0"
 * Explanation: S3 is "0111001".
 * The 1st bit is "0".
 * Example 2:
 * <p>
 * Input: n = 4, k = 11
 * Output: "1"
 * Explanation: S4 is "011100110110001".
 * The 11th bit is "1".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= 2^n - 1
 * <p>
 * Hint 1
 * Since n is small, we can simply simulate the process of constructing S1 to Sn.
 */
@SuppressWarnings("unused")
public class KthBitNthBinary {
    // 1, 1. @lee215
    static class Solution {
        public char findKthBit(int n, int k) {
            int base = k & 1 ^ 1; // base case in recursive method below, return 0 if n==1, if middle return 1
            // assume k=x*pow(2) x is odd, number of 0,1 groups in x>>1 is flip count
            int flipCnt = (k / (k & -k)) >> 1;
            int flipOdd = flipCnt & 1; // flip count is odd?
            return (char) (flipOdd ^ base + '0');
            // return str(int((bin(k).split('1')[-2]=='') == k%2))
            // return (char)((k / (k & -k) >> 1 & 1) ^ (k & 1 ^ 1) + '0');
        }
    }

    // n,1.
    static class Solution2 {
        public char findKthBit(int n, int k) {
            int flip = 0, len = (1 << n) - 1; // inverted count, length of string
            while (k > 1) {
                // If k is in the middle, return based on inversion count
                if (k - 1 == len / 2) return flip % 2 == 0 ? '1' : '0';
                else if (k - 1 > len / 2) { // second half
                    k = len + 1 - k; // Mirror position
                    flip++;
                }
                len /= 2;
            }
            return flip % 2 == 0 ? '0' : '1'; // S1="0", consider inversion count
        }
    }

    // n,n
    static class Solution3 {
        public char findKthBit(int n, int k) {
            if (n == 1) return '0'; // Base case: for S1, return '0'
            int len = (1 << n) - 1; // length of string
            // If k is in the first half of the string, recurse with n-1
            if (k - 1 < len / 2) return findKthBit(n - 1, k);
                // If k is exactly in the middle, return '1'
            else if (k - 1 == len / 2) return '1';
                // If k is in the second half of the string
            else {
                // Find the corresponding bit in the first half and invert it
                char res1 = findKthBit(n - 1, len + 1 - k);
                return (res1 == '0') ? '1' : '0';
            }
        }
    }
}
