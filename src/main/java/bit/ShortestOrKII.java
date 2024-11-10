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
 * 1 <= nums.length <= 2 * 10^5
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
    // n,1. todo editorial
    static class Solution {

        public int minimumSubarrayLength(int[] nums, int k) {
            int minLength = Integer.MAX_VALUE;
            int windowStart = 0;
            int windowEnd = 0;
            int[] bitCounts = new int[32]; // Tracks count of set bits at each position

            // Expand window until end of array
            while (windowEnd < nums.length) {
                // Add current number to window
                updateBitCounts(bitCounts, nums[windowEnd], 1);

                // Contract window while OR value is valid
                while (
                        windowStart <= windowEnd &&
                                convertBitCountsToNumber(bitCounts) >= k
                ) {
                    // Update minimum length found so far
                    minLength = Math.min(minLength, windowEnd - windowStart + 1);

                    // Remove leftmost number and shrink window
                    updateBitCounts(bitCounts, nums[windowStart], -1);
                    windowStart++;
                }

                windowEnd++;
            }

            return minLength == Integer.MAX_VALUE ? -1 : minLength;
        }

        // Updates bit count array when adding/removing a number from window
        private void updateBitCounts(int[] bitCounts, int number, int delta) {
            for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
                // Check if bit is set at current position
                if (((number >> bitPosition) & 1) != 0) {
                    bitCounts[bitPosition] += delta;
                }
            }
        }

        // Converts bit count array back to number using OR operation
        private int convertBitCountsToNumber(int[] bitCounts) {
            int result = 0;
            for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
                if (bitCounts[bitPosition] != 0) {
                    result |= 1 << bitPosition;
                }
            }
            return result;
        }
    }
}
