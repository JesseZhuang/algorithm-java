package array;

/**
 * LeetCode 238, medium, tags: array, prefix sum.
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
 * of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space
 * for space complexity analysis.)
 */
@SuppressWarnings("unused")
public final class ProductExceptSelf {
    private ProductExceptSelf() {}

    // solution 1, build prefix products into result in forward pass, multiply suffix in reverse. O(n) time, O(1) space.
    public static class Solution {
        public int[] productExceptSelf(int[] nums) { // [1,2,3,4]
            int[] res = new int[nums.length];
            res[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                res[i] = res[i - 1] * nums[i - 1]; // prefix products: 1,1,2,6
            }
            int right = 1;
            for (int i = nums.length - 2; i >= 0; i--) {
                right *= nums[i + 1]; // accumulate product right of i
                res[i] *= right;
            }
            return res;
        }
    }

    // solution 2, separate prefix[] and suffix[] arrays then combine. O(n) time, O(n) space.
    public static class Solution2 {
        public int[] productExceptSelf(int[] nums) { // [1,2,3,4]
            int n = nums.length;
            int[] prefix = new int[n]; // product of elements to the left
            int[] suffix = new int[n]; // product of elements to the right
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                prefix[i] = i > 0 ? prefix[i - 1] * nums[i - 1] : 1;
            }
            // prefix [1,1,2,6]
            for (int i = n - 1; i >= 0; i--) {
                suffix[i] = i == n - 1 ? 1 : suffix[i + 1] * nums[i + 1];
                result[i] = prefix[i] * suffix[i];
            }
            // suffix [24,12,4,1], result [24,12,8,6]
            return result;
        }
    }
}
