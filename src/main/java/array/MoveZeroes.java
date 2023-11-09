package array;

/**
 * LeetCode 283, easy, tags: array, two pointers.
 * <p>
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order
 * of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>Iterative, swapping/setting, O(n) time, O(1) space.</b>
 * <li>Recursive, O(n) time, O(1) space.
 * </ul>
 */
public class MoveZeroes {

    // solution 1, O(n) time, O(1) space. 2ms, 44.62Mb.
    public void moveZeroesIter2(int[] nums) {
        if (nums == null) return;
        int zeroInd = 0; // The index of the leftmost zero in nums.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > zeroInd) { // i can only be larger than or equal to zeroInd. {2, 1, 3, 0, 5, 0, 6}
                    nums[zeroInd] = nums[i];
                    nums[i] = 0;
                }
                zeroInd++;
            }
        }
    }

    // solution 2, O(n+k) time, O(1) space. k == number of zeroes. 1ms, 44.98 Mb.
    public void moveZeroesIter1(int[] nums) {
        if (nums == null) return;
        int insert = 0;
        for (int n : nums) if (n != 0 && nums[insert++] != n) nums[insert - 1] = n;
        while (insert < nums.length) nums[insert++] = 0;
    }

}
