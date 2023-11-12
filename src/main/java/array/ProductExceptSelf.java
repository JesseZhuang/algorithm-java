package array;

/**
 * LeetCode 238. Product of Array Except Self. Medium. Tags: array, prefix sum.
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
 * of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space
 * for space complexity analysis.)
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li> O(N) time, O(1) space, using result array.
 * <li> O(N) time, O(N) space, extra arrays to store intermediate calculations.
 * </ul>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class ProductExceptSelf {
    // solution 1, 2ms, 52 Mb. O(n) time O(1) space.
    public int[] productExceptSelf2(int[] nums) { // [1,2,3,4]
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1]; // use result as leftOf 1,1,2,6
        }
        int right = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right *= nums[i + 1]; // accumulate product right of i in right
            res[i] *= right;
        }
        return res;
    }

    // solution 2,  O(n) time and space. 4 ms, 57.8 Mb
    public int[] productExceptSelf1(int[] nums) { // [1,2,3,4]
        int[] leftOf = new int[nums.length]; // product for elements on the left
        int[] rightOf = new int[nums.length]; // product for elements on the right
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            leftOf[i] = i > 0 ? leftOf[i - 1] * nums[i - 1] : 1;
        }
        // leftOf [1,1,2,6]
        for (int i = nums.length - 1; i >= 0; i--) {
            rightOf[i] = i == nums.length - 1 ? 1 : rightOf[i + 1] * nums[i + 1];
            result[i] = rightOf[i] * leftOf[i];
        }
        // rightOf [24,12,4,1], result [24,12,8,6]
        return result;
    }
}