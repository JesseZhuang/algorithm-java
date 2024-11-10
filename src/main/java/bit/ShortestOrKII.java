package bit;

/**
 * LeetCode 3097, medium, tags: array, bit manipulation, sliding window.
 * <p>
 * You are given an array nums of non-negative integers and an integer k.
 * <p>
 * An array is called special if the bitwise OR of all of its elements is at least k.
 * <p>
 * Return the length of the shortest special non-empty
 * subarray
 * of nums, or return -1 if no special subarray exists.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3], k = 2
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The subarray [3] has OR value of 3. Hence, we return 1.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,1,8], k = 10
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * The subarray [2,1,8] has OR value of 11. Hence, we return 3.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2], k = 0
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The subarray [1] has OR value of 1. Hence, we return 1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 10^5, n
 * 0 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 * Hint 1
 * For each nums[i], we can maintain each subarray’s bitwise OR result ending with it.
 * Hint 2
 * The property of bitwise OR is that it never unsets any bits and only sets new bits
 * Hint 3
 * So the number of different results for each nums[i] is at most the number of bits 32.
 */
@SuppressWarnings("unused")
public class ShortestOrKII {
    // n,1. sliding window
    static class Solution {
        final static int K = 32 - Integer.numberOfLeadingZeros((int) 1e9);
        int[] bitCnt;

        public int minimumSubarrayLength(int[] nums, int k) {
            int res = Integer.MAX_VALUE;
            int start = 0, end = 0;
            bitCnt = new int[K]; // Tracks count of set bits at each position
            while (end < nums.length) {
                updateBitCnt(nums[end], 1);
                while (start <= end && bitCntToNum() >= k) {
                    res = Math.min(res, end - start + 1);
                    updateBitCnt(nums[start], -1);
                    start++;
                }
                end++;
            }
            return res == Integer.MAX_VALUE ? -1 : res;
        }

        // update the bit count array when adding/removing a number from window
        private void updateBitCnt(int number, int delta) {
            for (int pos = 0; pos < K; pos++)
                if (((number >> pos) & 1) != 0) bitCnt[pos] += delta;
        }

        // convert the bit count array back to number using OR operation
        private int bitCntToNum() {
            int res = 0;
            for (int pos = 0; pos < K; pos++)
                if (bitCnt[pos] != 0) res |= 1 << pos;
            return res;
        }
    }
}
