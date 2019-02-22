package array;

/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * <p>
 * Example: Given nums = [-2, 0, 3, -5, 2, -1]
 * <p>
 * sumRange(0, 2) -> 1<br>
 * sumRange(2, 5) -> -1<br>
 * sumRange(0, 5) -> -3
 * <p>
 * Note: You may assume that the array does not change. There are many calls to
 * sumRange function.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>no need to save 2 dimensional data. That kind of computation is quadratic.
 * <li>Only need to save all accumulative sums from 0, sum range of 3-5 can be
 * computed as sum 0-5 minus sum 0-2, O(n) time pre-compute, O(1) query return,
 * O(n) space.
 * </ul>
 */
public class RangeSum {
    // Your object will be instantiated and called as such:
    // NumArray numArray = new NumArray(nums);
    // numArray.sumRange(0, 1);
    // numArray.sumRange(1, 2);

    private int[] sums;

    public RangeSum(int[] nums) {
        this.sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) sums[i + 1] = nums[i] + sums[i];
    }

    public int sumRange(int i, int j) {
        // return Arrays.stream(nums, i, j + 1).sum();
        return sums[j + 1] - sums[i];
    }
}
