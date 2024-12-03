package array;

import util.IntArrayUtil;

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
     * solution 1, 2ms, memory 73.2 Mb. Kadane's algorithm.
     * O(N) time, O(1) space. If use array to track status, O(N) space.
     */
    static class Solution1 {
        int maxSubarrayDP(int[] nums) { //-2,1,-3,4,-1,2,1,-5,4
            int maxHere = 0, res = Integer.MIN_VALUE; // max sum ending at current index
            for (int n : nums) {
                maxHere = Math.max(n, maxHere + n); // -2,1,-2,4,3,5,6,1,4; note that the first item is num, not 0
                res = Math.max(res, maxHere);       // -2,1, 1,4,4,5,6,6,6
            }
            return res;
        }
    }

    static class Solution2 {
        int maxSubarrayDC2(int[] nums) {
            // pre[i] denotes maximum sum subarray ending at i and suf[i] denotes the maximum subarray starting at i
            int[] pre, suf;
            pre = Arrays.copyOf(nums, nums.length);
            suf = Arrays.copyOf(nums, nums.length);
            for (int i = 1; i < nums.length; i++) pre[i] += Math.max(0, pre[i - 1]);
            for (int i = nums.length - 2; i >= 0; i--) suf[i] += Math.max(0, suf[i + 1]);
            return maxSubarrayDC2Helper(nums, 0, nums.length - 1, pre, suf);
        }

        /**
         * 219 ms, memory 125 Mb.
         * divide and conquer, O(N) time, O(N) space. T(N) = 2T(N/2) + O(1).
         */
        int maxSubarrayDC2Helper(int[] nums, int left, int right, int[] pre, int[] suf) {
            // note stop a little earlier than DC1 to avoid pre[mid-1] index out of bound when left+1==right
            if (left == right) return nums[left];
            int mid = (left + right) / 2;
            return IntArrayUtil.maxOfArrayWithStream(new int[]{maxSubarrayDC2Helper(nums, left, mid, pre, suf),
                    maxSubarrayDC2Helper(nums, mid + 1, right, pre, suf), pre[mid] + suf[mid + 1]});
        }
    }

}
