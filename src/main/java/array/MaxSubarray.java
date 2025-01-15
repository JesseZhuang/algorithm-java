package array;

import java.util.Arrays;

/**
 * LeetCode 53, Medium, Tags: Array, Dynamic Programming, Divide and Conquer.
 * <p>
 * Given an integer array nums, find the subarray which has the largest sum and return its sum.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer
 * approach, which is more subtle.
 */
@SuppressWarnings("unused")
public class MaxSubarray {

    /**
     * Solution 1, dp, 1 ms, 57.33 Mb. Kadane's algorithm.
     * O(N) time, O(1) space. If you use an array to track status, O(N) space.
     */
    static class Solution1 {
        int maxSubArray(int[] nums) {
            int maxHere = 0, res = Integer.MIN_VALUE; // max sum ending at current index
            for (int n : nums) {
                maxHere = Math.max(n, maxHere + n); // note that the first item is num, not 0
                res = Math.max(res, maxHere);
            }
            return res;
        }
    }

    static class Solution2 {
        int[] pre, suf;

        int maxSubArray(int[] nums) {
            int n = nums.length;
            pre = Arrays.copyOf(nums, n);
            suf = Arrays.copyOf(nums, n);
            for (int i = 1; i < n; i++) pre[i] += Math.max(0, pre[i - 1]);
            for (int i = n - 2; i >= 0; i--) suf[i] += Math.max(0, suf[i + 1]);
            return helper(nums, 0, n - 1);
        }

        /**
         * 72 ms, 58.05 mb.
         * Divide and conquer, O(N) time, O(N) space. T(N) = 2T(N/2) + O(1).
         * Alternative DnC solution available for O(NlgN) time and O(lgN) recursive stack space.
         */
        int helper(int[] nums, int left, int right) {
            if (left == right) return nums[left];
            int mid = (left + right) / 2;
            return Arrays.stream(new int[]{
                    helper(nums, left, mid),
                    helper(nums, mid + 1, right),
                    pre[mid] + suf[mid + 1]}).max().orElseThrow();
        }
    }

}
