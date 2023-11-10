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
 * <p>
 * Follow up: Could you minimize the total number of operations done?
 * <p>
 * Hints:
 * In-place means we should not be allocating any space for extra array. But we are allowed to modify the existing
 * array. However, as a first step, try coming up with a solution that makes use of additional space. For this problem
 * as well, first apply the idea discussed using an additional array and the in-place solution will pop up eventually.
 * <p>
 * A two-pointer approach could be helpful here. The idea would be to have one pointer for iterating the array and
 * another pointer that just works on the non-zero elements of the array.
 */
public class MoveZeroes {

    // solution 1, O(n) time, O(1) space. 2ms, 44.62Mb.
    public void moveZeroesIter2(int[] nums) {
        for (int i = 0, zeroInd = 0; i < nums.length; i++) { // The index of the leftmost zero in nums.
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
        int insert = 0, l = nums.length;
        for (int i = 0; i < l; i++)
            if (nums[i] != 0 && insert++ != i) nums[insert - 1] = nums[i];
        while (insert < nums.length) nums[insert++] = 0;
    }

}
