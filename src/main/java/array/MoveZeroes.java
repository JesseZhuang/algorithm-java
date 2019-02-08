package array;

/**
 * LeetCode 283. Easy.
 * <p>
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order
 * of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>Iterative, swapping/setting, O(n) time, O(1) space.</b>
 * <li>Recursive, O(n) time, O(1) space.
 * </ul>
 */
public class MoveZeroes {
    public void moveZeroesRec(int[] nums) {
        if (nums == null) return;
        helper(nums, -1, 0, true);
    }

    /**
     * Recursive helper method.
     *
     * @param nums Array to swap.
     * @param head current range starting index.
     * @param tail current range ending index.
     * @param findZero whether to find the next 0 array element.
     */
    private void helper(int[] nums, int head, int tail, boolean findZero) {
        while (tail < nums.length) {
            if (findZero) {
                if (nums[tail] != 0) tail++;
                else {
                    if (head == -1) head = tail;
                    helper(nums, head, tail + 1, false);
                    return;
                }
            } else {
                if (nums[tail] != 0) {
                    nums[head] = nums[tail];
                    nums[tail] = 0;
                    head++;
                    helper(nums, head, tail + 1, false);
                    return;
                } else tail++;
            }
        }
    }

    public void moveZeroesIter1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int insertPos = 0;
        for (int num : nums) if (num != 0) nums[insertPos++] = num;
        while (insertPos < nums.length) nums[insertPos++] = 0;
    }

    // Loop through nums, if nums[i] is non-zero, replace the leftmost
    // zero element nums[j] with nums[i] and set nums[i] to zero if i > j.
    // Note that i == j happens when nums has leading non-zero
    // elements, nums = {2, 1, 3, 0, 5, 0, 6}. In this case, we don't perform
    // any swap and keep incrementing i and j until i > j.
    public void moveZeroesIter2(int[] nums) {
        if (nums == null) return;
        int leftMostZeroIndex = 0; // The index of the leftmost zero in nums.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > leftMostZeroIndex) { // i can only be larger than or equal to j.
                    nums[leftMostZeroIndex] = nums[i];
                    nums[i] = 0;
                }
                leftMostZeroIndex++;
            }
        }
    }

    public static void moveZeroesIter3(int[] nums) {
        if (nums == null) return;
        int head = -1, tail = 0;
        boolean findZero = true;
        while (tail < nums.length) {
            if (findZero) {
                if (nums[tail] != 0) tail++;
                else {
                    if (head == -1) head = tail++;
                    findZero = false;
                }
            } else {
                if (nums[tail] != 0) {
                    nums[head] = nums[tail];
                    nums[tail] = 0;
                    head++;
                } else tail++;
            }
        }
    }
}
