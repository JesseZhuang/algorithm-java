package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 974. Medium. Tags: array, hash table, prefix sum.
 * <p>
 * Given an integer array nums and an integer k, return the number of
 * non-empty subarrays that have a sum divisible by k.
 */
public class SubarraySumsDivisibleByK {

    /**
     * Prefix sum + HashMap with modular arithmetic. O(n) time, O(k) space.
     * Running prefix sum, compute remainder = ((prefix % k) + k) % k to
     * handle negatives (Java % can return negative). Count previous prefixes
     * with same remainder.
     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1);
        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            int remainder = ((prefix % k) + k) % k;
            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.merge(remainder, 1, Integer::sum);
        }
        return count;
    }

    /**
     * Brute force. O(n^2) time, O(1) space.
     */
    public int subarraysDivByKBrute(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) count++;
            }
        }
        return count;
    }
}
