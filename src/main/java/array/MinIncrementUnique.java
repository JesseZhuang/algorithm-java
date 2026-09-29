package array;

import java.util.Arrays;

/**
 * LeetCode 945, medium, tags: array, greedy, sorting, counting.
 * <p>
 * You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length
 * and increment nums[i] by 1. Return the minimum number of moves to make every value in nums unique.
 * <p>
 * Example 1: Input: nums = [1,2,2] Output: 1 (increment one of the 2s to 3)
 * Example 2: Input: nums = [3,2,1,2,1,7] Output: 6 (after 6 moves: [3,4,1,2,5,7])
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5, 0 <= nums[i] <= 10^5
 */
@SuppressWarnings("unused")
public final class MinIncrementUnique {
    private MinIncrementUnique() {}

    /** Sort + greedy. O(n log n) time, O(sort) space. */
    public static int sortGreedy(int[] nums) {
        Arrays.sort(nums); // O(n log n)
        int moves = 0;
        for (int i = 1; i < nums.length; i++) { // O(n) scan
            if (nums[i] <= nums[i - 1]) {
                int target = nums[i - 1] + 1;
                moves += target - nums[i];
                nums[i] = target;
            }
        }
        return moves;
    }

    /** Counting sort / frequency sweep. O(n + max_val) time, O(n + max_val) space. */
    public static int countingSort(int[] nums) {
        int max = 0;
        for (int v : nums) max = Math.max(max, v);
        int[] count = new int[nums.length + max + 1]; // room for pushed-forward values
        for (int v : nums) count[v]++; // O(n) frequency count
        int moves = 0;
        for (int i = 0; i < count.length - 1; i++) { // O(n + max_val) sweep
            if (count[i] > 1) {
                int extras = count[i] - 1;
                count[i + 1] += extras; // push extras forward
                moves += extras;
            }
        }
        return moves;
    }
}
