package array;

/**
 * LeetCode 581, LintCode 1157, medium, tags: array, two pointers, stack, greedy, sorting, monotonic stack.
 * <p>
 * Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray
 * in non-decreasing order, then the whole array will be sorted in non-decreasing order.
 * <p>
 * Return the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * <p>
 * Follow up: Can you solve it in O(n) time complexity?
 */
public class ShortestUnsorted {
    // solution 1, two pointer, n time, 1 space. 1ms, 44.2Mb.
    // solution 2, sort then two pointer, nlgn time, n space.
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length, lo = -1, hi = -2, min = nums[n - 1], max = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n - 1 - i]);
            if (nums[i] < max) hi = i;
            if (nums[n - 1 - i] > min) lo = n - 1 - i;
        }
        return hi - lo + 1;
    }
}
