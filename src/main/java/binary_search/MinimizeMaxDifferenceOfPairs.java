package binary_search;

import java.util.Arrays;

/**
 * LeetCode 2616, medium, tags: array, binary search, greedy.
 * <p>
 * You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the
 * maximum difference amongst all the pairs is minimized. Return the minimized maximum difference.
 * <p>
 * Example 1:
 * Input: nums = [10,1,2,7,1,3], p = 2
 * Output: 1
 * <p>
 * Example 2:
 * Input: nums = [4,2,1,2], p = 1
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= p <= nums.length / 2
 */
public final class MinimizeMaxDifferenceOfPairs {

    private MinimizeMaxDifferenceOfPairs() {
    }

    /**
     * Sort + Binary Search on Answer + Greedy. O(n log n + n log M) time, O(1) space.
     * M = max(nums) - min(nums).
     */
    public static int minimizeMax(int[] nums, int p) {
        if (p == 0) return 0;
        Arrays.sort(nums); // O(n log n)
        int lo = 0, hi = nums[nums.length - 1] - nums[0]; // binary search bounds
        while (lo < hi) { // O(log M) iterations
            int mid = lo + (hi - lo) / 2;
            if (countPairs(nums, mid) >= p) { // O(n) greedy check
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * Greedy: count max pairs with difference <= threshold. O(n) time.
     */
    private static int countPairs(int[] nums, int threshold) {
        int count = 0;
        int i = 0;
        while (i < nums.length - 1) { // O(n) scan
            if (nums[i + 1] - nums[i] <= threshold) {
                count++;
                i += 2; // skip both elements
            } else {
                i++;
            }
        }
        return count;
    }
}
