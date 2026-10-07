package array;

/**
 * LeetCode 1827, easy, tags: array, greedy.
 * <p>
 * Return the minimum number of increments needed to make nums strictly increasing.
 * The input array is not modified.
 * <p>
 * Constraints: 1 <= nums.length <= 5000, 1 <= nums[index] <= 10000.
 * The result fits in an int.
 */
public final class MinimumOperationsToMakeArrayIncreasing {

    private MinimumOperationsToMakeArrayIncreasing() {
    }

    /** Greedy single pass. O(n) time, O(1) extra space. */
    public static int minOperations(int[] nums) {
        int previous = nums[0];
        int operations = 0;
        for (int index = 1; index < nums.length; index++) {
            int original = nums[index];
            int adjusted = Math.max(original, previous + 1);
            operations += adjusted - original;
            previous = adjusted;
        }
        return operations;
    }
}
