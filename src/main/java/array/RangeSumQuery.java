package array;

/**
 * LeetCode 303, easy, tags: array, design, prefix sum.
 * <p>
 * Given an integer array nums, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
 * (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 0 <= left <= right < nums.length
 * At most 10^4 calls will be made to sumRange.
 */
public class RangeSumQuery {
    private final int[] sums;

    // solution 1, prefix sum, 7ms, 45.2 Mb. O(1) time for rsq, O(n) time init, O(n) space.
    public RangeSumQuery(int[] nums) { // [-2, 0, 3, -5, 2, -1]
        int l = nums.length;
        sums = new int[l + 1]; // dummy 0th element
        for (int i = 0; i < l; i++) {
            sums[i + 1] = nums[i] + sums[i]; // [0, -2, -2, 1, -4, -2, -3]
        }
    }

    public int sumRange(int i, int j) {
        // return Arrays.stream(nums, i, j + 1).sum();
        return sums[j + 1] - sums[i];
    }

}
