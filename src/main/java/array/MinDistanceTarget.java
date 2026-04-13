package array;

/**
 * LeetCode 1848, easy, tags: array.
 * <p>
 * Given an integer array nums (0-indexed) along with two integers target and start, find an index i such that
 * nums[i] == target and abs(i - start) is minimized. Note that abs(x) returns the absolute value of x.
 * <p>
 * Return abs(i - start).
 * <p>
 * It is guaranteed that target exists in nums.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5], target = 5, start = 3
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], target = 1, start = 0
 * Output: 0
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^4
 * 0 <= start < nums.length
 * target is in nums.
 */
@SuppressWarnings("unused")
public class MinDistanceTarget {
    // linear scan, O(n) time, O(1) space.
    static class Solution {
        public int getMinDistance(int[] nums, int target, int start) {
            int res = nums.length;
            for (int i = 0; i < nums.length; i++)
                if (nums[i] == target) res = Math.min(res, Math.abs(i - start));
            return res;
        }
    }
}
