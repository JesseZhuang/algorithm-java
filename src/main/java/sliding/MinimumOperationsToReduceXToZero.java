package sliding;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1658, medium, tags: array, hash table, sliding window, prefix sum.
 * Remove elements from either end of a positive integer array to reduce x to zero.
 * Keeping the longest subarray with sum total - x minimizes the number removed.
 */
@SuppressWarnings("unused")
public final class MinimumOperationsToReduceXToZero {

    private MinimumOperationsToReduceXToZero() {
    }

    // Positive-number sliding window. O(n) time, O(1) extra space.
    public static int minOperations(int[] nums, int x) {
        int target = -x;
        for (int num : nums) target += num;
        if (target < 0) return -1;
        if (target == 0) return nums.length;

        int left = 0, sum = 0, longest = -1;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > target) sum -= nums[left++];
            if (sum == target) longest = Math.max(longest, right - left + 1);
        }
        return longest == -1 ? -1 : nums.length - longest;
    }

    // Earliest prefix indices maximize the retained length. O(n) time, O(n) extra space.
    public static int minOperationsByPrefixSum(int[] nums, int x) {
        int target = -x;
        for (int num : nums) target += num;
        if (target < 0) return -1;
        if (target == 0) return nums.length;

        Map<Integer, Integer> earliest = new HashMap<>();
        earliest.put(0, -1);
        int prefix = 0, longest = -1;
        for (int right = 0; right < nums.length; right++) {
            prefix += nums[right];
            Integer left = earliest.get(prefix - target);
            if (left != null) longest = Math.max(longest, right - left);
            earliest.putIfAbsent(prefix, right);
        }
        return longest == -1 ? -1 : nums.length - longest;
    }
}
