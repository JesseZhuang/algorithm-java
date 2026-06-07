package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 525. Medium. Tags: array, hash table, prefix sum.
 * <p>
 * Given a binary array nums, find the maximum length of a contiguous subarray
 * with an equal number of 0s and 1s.
 * <p>
 * Constraints: 1 <= nums.length <= 10^5, nums[i] is 0 or 1.
 */
public final class ContiguousArray {

    private ContiguousArray() {
    }

    /**
     * Prefix sum + HashMap. O(n) time, O(n) space.
     * Treat 0 as -1. If the same prefix sum appears at indices i and j,
     * the subarray (i, j] has equal 0s and 1s.
     */
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> firstIndex = new HashMap<>(); // O(n) space: stores first occurrence of each prefix sum
        firstIndex.put(0, -1); // base case: prefix sum 0 seen before index 0
        int prefix = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += (nums[i] == 0) ? -1 : 1; // O(1): treat 0 as -1 to convert to prefix sum problem
            if (firstIndex.containsKey(prefix)) {
                maxLen = Math.max(maxLen, i - firstIndex.get(prefix)); // O(1): subarray length between first occurrence and current
            } else {
                firstIndex.put(prefix, i); // only store first occurrence to maximize length
            }
        }
        return maxLen;
    }
}
