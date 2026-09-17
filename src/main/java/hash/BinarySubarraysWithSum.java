package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 930. Medium. Tags: array, hash table, sliding window, prefix sum.
 * <p>
 * Given a binary array nums and an integer goal, return the number of
 * non-empty subarrays with a sum equal to goal.
 * <p>
 * Constraints: 1 <= nums.length <= 3 * 10^4, nums[i] is 0 or 1, 0 <= goal <= nums.length.
 */
public final class BinarySubarraysWithSum {

    private BinarySubarraysWithSum() {
    }

    /**
     * Prefix sum + HashMap. O(n) time, O(n) space.
     * For each prefix sum, add the frequency of (prefix - goal) seen so far.
     */
    public static int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> freq = new HashMap<>(); // O(n) space
        freq.put(0, 1); // base case: empty prefix has sum 0
        int prefix = 0;
        int count = 0;
        for (int num : nums) { // O(n)
            prefix += num;
            count += freq.getOrDefault(prefix - goal, 0); // O(1): subarrays ending here with sum == goal
            freq.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    /**
     * Sliding window. O(n) time, O(1) space.
     * exactly(goal) = atMost(goal) - atMost(goal - 1).
     */
    public static int numSubarraysWithSum2(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    /** Counts subarrays with sum <= k. O(n) time, O(1) space. */
    private static int atMost(int[] nums, int k) {
        if (k < 0) return 0;
        int left = 0;
        int sum = 0;
        int count = 0;
        for (int right = 0; right < nums.length; right++) { // O(n)
            sum += nums[right];
            while (sum > k) { // O(1) amortized
                sum -= nums[left++];
            }
            count += right - left + 1; // number of valid subarrays ending at right
        }
        return count;
    }
}
