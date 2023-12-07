package bit;

/**
 * LeetCode 338, easy, dynamic programming, bit.
 * <p>
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 105
 * <p>
 * <p>
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n)
 * and possibly in a single pass?
 * Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 * Hints:
 * You should make use of what you have produced already.
 * Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
 * Or does the odd/even status of the number help you in calculating the number of 1s?
 */
public class CountingBits {
    // solution 1, 2ms, 48.5 Mb. No repeating calculations. O(n) time, O(1) space.
    public int[] countBits2(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) res[i] = res[i / 2] + i % 2; // res[i>>1] + (i&1)
        return res;
    }

    // O(nLgn) time, O(1) space. 18ms, 48.4 Mb. Repeating calculations.
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int sum = 0;
            int num = i;
            while (num != 0) {
                sum += num % 2;
                num = num / 2;
            }
            result[i] = sum;
        }
        return result;
    }

    // O(n) time (O(Lg32) * O(n), O(1) space. Repeating calculations. 3ms, 48.5 Mb.
    public int[] countBits1(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) result[i] = Integer.bitCount(i);
        return result;
    }

}
