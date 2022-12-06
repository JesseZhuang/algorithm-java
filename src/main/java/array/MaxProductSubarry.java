package array;

/**
 * LeetCode 152, medium, tags: array, dynamic programming.
 * <p>
 * Given an integer array nums, find a subarray
 * that has the largest product, and return the product.
 * <p>
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * <p>
 * Constraints:
 * <pre>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * </pre>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class MaxProductSubarry {
    /**
     * Kadane's algorithm. DP. O(N) time, O(1) space. 7ms, 45.3 Mb.
     */
    public int maxProduct(int[] nums) {
        int maxEndingHere = 1, minEndingHere = 1, maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int prod1 = maxEndingHere * nums[i], prod2 = minEndingHere * nums[i];
            maxEndingHere = Math.max(nums[i],  Math.max(prod1, prod2));
            minEndingHere = Math.min(nums[i],  Math.min(prod1, prod2));
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }
}
