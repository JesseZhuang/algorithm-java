package dp;

import java.util.BitSet;

/**
 * LeetCode 416, medium, tags: array, dynamic programming, bit manipulation.
 * Given an integer array nums, return true if you can partition the array into two subsets
 * such that the sum of the elements in both subsets is equal.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200, n
 * 1 <= nums[i] <= 100
 */
public class PartitionEqualSubsetSum {

    // solution 1, dp boolean array. O(n*target) time, O(target) space.
    public boolean canPartitionDP(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num; // O(n)
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1]; // dp[j]: whether sum j is reachable
        dp[0] = true;
        for (int num : nums) // O(n)
            for (int j = target; j >= num; j--) // O(target), iterate backward to avoid using num twice
                dp[j] = dp[j] || dp[j - num];
        return dp[target];
    }

    // solution 2, bitset. O(n*target) time, O(target) space.
    public boolean canPartitionBitSet(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num; // O(n)
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        BitSet bits = new BitSet(target + 1);
        bits.set(0);
        for (int num : nums) // O(n)
            bits.or(shiftLeft(bits, num, target)); // O(target), OR with shifted copy
        return bits.get(target);
    }

    private BitSet shiftLeft(BitSet bits, int shift, int limit) {
        BitSet shifted = new BitSet(limit + 1);
        for (int i = bits.nextSetBit(0); i >= 0 && i + shift <= limit; i = bits.nextSetBit(i + 1))
            shifted.set(i + shift);
        return shifted;
    }
}
