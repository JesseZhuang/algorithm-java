package array;

/**
 * Support range updates in O(1) time. Get back array elements in O(N) time.
 * Reverse operation with prefix sum.
 *
 * @see <a href="https://wcipeg.com/wiki/Prefix_sum_array_and_difference_array">PEG wiki</a>
 */
public class DifferenceArray {
    private int[] diff;

    public DifferenceArray(int[] nums) {
        diff = new int[nums.length + 1];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) diff[i] = nums[i] - nums[i - 1];
    }

    /**
     * add delta to nums in [left, right] range, inclusive
     *
     * @param left  left boundary
     * @param right right boundary
     * @param delta delta value to add
     */
    public void update(int left, int right, int delta) {
        diff[left] += delta;
        diff[right + 1] -= delta;
    }

    /**
     * return the original array
     *
     * @return the original array
     */
    public int[] getOriginal() {
        int[] nums = new int[diff.length - 1];
        nums[0] = diff[0];
        for (int i = 1; i < nums.length; i++) nums[i] = nums[i - 1] + diff[i];
        return nums;
    }
}
