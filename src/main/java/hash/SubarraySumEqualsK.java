package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 560. Medium. Tags: array, hash table, prefix sum.
 * <p>
 * Given an integer array nums and an integer k, return the total number
 * of subarrays whose sum equals k.
 */
public class SubarraySumEqualsK {

    /**
     * Prefix sum + HashMap. O(n) time, O(n) space.
     * For each element, compute running prefix sum and check how many
     * times (prefix - k) has appeared before.
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            count += prefixCount.getOrDefault(prefix - k, 0);
            prefixCount.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    /**
     * Brute force. O(n^2) time, O(1) space.
     */
    public int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }
}
