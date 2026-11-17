package array;

import java.util.Arrays;

/** Greedy solution for LeetCode 2592, Maximize Greatness of an Array. */
public final class MaximizeGreatness {

    private MaximizeGreatness() {
    }

    /**
     * Counts the maximum number of elements that can be made strictly greater than their paired values.
     *
     * @param nums values to pair after rearrangement; sorted in place
     * @return the maximum number of strictly greater pairs
     *
     * Time: O(n log n) for sorting. Extra space: O(log n) for the sort's stack and O(1) for the scan.
     */
    public static int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);

        int matched = 0;
        for (int candidate = 0; candidate < nums.length; candidate++) {
            if (nums[candidate] > nums[matched]) {
                matched++;
            }
        }
        return matched;
    }
}
