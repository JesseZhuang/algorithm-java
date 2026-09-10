package hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode 1679. Medium. Tags: array, hash table, two pointers, sorting.
 * <p>
 * Given an integer array nums and an integer k, in one operation you can pick
 * two numbers from the array whose sum equals k and remove them. Return the
 * maximum number of operations you can perform.
 */
public final class MaxNumberOfKSumPairs {

    private MaxNumberOfKSumPairs() {
    }

    /**
     * Hash-map approach: single pass, count complements.
     * O(n) time, O(n) space.
     */
    public static int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>(); // O(n) space
        int ops = 0;
        for (int num : nums) { // O(n) time
            int complement = k - num;
            Integer count = freq.get(complement);
            if (count != null && count > 0) {
                ops++;
                freq.put(complement, count - 1);
            } else {
                freq.merge(num, 1, Integer::sum);
            }
        }
        return ops;
    }

    /**
     * Sort + two-pointer approach.
     * O(n log n) time, O(1) extra space (ignoring sort internals).
     */
    public static int maxOperationsTwoPointers(int[] nums, int k) {
        Arrays.sort(nums); // O(n log n)
        int lo = 0, hi = nums.length - 1;
        int ops = 0;
        while (lo < hi) { // O(n)
            int sum = nums[lo] + nums[hi];
            if (sum == k) {
                ops++;
                lo++;
                hi--;
            } else if (sum < k) {
                lo++;
            } else {
                hi--;
            }
        }
        return ops;
    }
}
