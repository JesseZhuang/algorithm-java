package array;

import java.util.TreeSet;

/**
 * LeetCode 220, hard, tags: array, sliding window, sorting, bucket sort, ordered set.
 * <p>
 * You are given an integer array nums and two integers indexDiff and valueDiff.
 * <p>
 * Find a pair of indices (i, j) such that:
 * <p>
 * i != j,
 * abs(i - j) <= indexDiff.
 * abs(nums[i] - nums[j]) <= valueDiff, and
 * Return true if such pair exists or false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
 * Output: true
 * Explanation: We can choose (i, j) = (0, 3).
 * We satisfy the three conditions:
 * i != j --> 0 != 3
 * abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
 * abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
 * Example 2:
 * <p>
 * Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
 * Output: false
 * Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105, N
 * -109 <= nums[i] <= 109
 * 1 <= indexDiff <= nums.length, K
 * 0 <= valueDiff <= 109
 */
public class ContainsDuplicateIII {
    // 50ms, 52.6 Mb. tree set. O(NLgK) time, O(K) space.
    // Alternatively can get floor(n+t) >= n or ceil(n-t) <= n
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> mem = new TreeSet<>();
        mem.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (mem.size() > indexDiff) mem.remove(nums[i - indexDiff - 1]);
            if (!mem.subSet(nums[i] - valueDiff, nums[i] + valueDiff + 1).isEmpty()) return true;
            mem.add(nums[i]);
        }
        return false;
    }
}
