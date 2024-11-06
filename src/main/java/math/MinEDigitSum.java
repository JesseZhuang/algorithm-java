package math;

import static util.Constants.I32_MAX;

/**
 * LeetCode 3300, easy, tags: array, math.
 * <p>
 * You are given an integer array nums.
 * <p>
 * You replace each element in nums with the sum of its digits.
 * <p>
 * Return the minimum element in nums after all replacements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,12,13,14]
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * nums becomes [1, 3, 4, 5] after all replacements, with minimum element 1.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4]
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * nums becomes [1, 2, 3, 4] after all replacements, with minimum element 1.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [999,19,199]
 * <p>
 * Output: 10
 * <p>
 * Explanation:
 * <p>
 * nums becomes [27, 10, 19] after all replacements, with minimum element 10.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100, n
 * 1 <= nums[i] <= 10^4, d(digits): [1,5]
 * <p>
 * Hint 1
 * Convert to string and calculate the sum for each element.
 */
@SuppressWarnings("unused")
public class MinEDigitSum {
    static class Solution {
        public int minElement(int[] nums) {
            int res = I32_MAX;
            for (int n : nums) {
                int sum = 0;
                while (n > 0) {
                    sum += n % 10;
                    n /= 10;
                }
                res = Math.min(res, sum);
            }
            return res;
        }
    }
}
