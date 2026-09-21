package array;

/**
 * LeetCode 2439, medium, tags: array, binary search, greedy, prefix sum.
 * <p>
 * You are given a 0-indexed array nums of n integers. In one operation, you can pick an index i where
 * 1 <= i < n and decrease nums[i] by 1 and increase nums[i-1] by 1. Return the minimum possible value
 * of the maximum integer of nums after performing any number of operations.
 * <p>
 * Example 1: Input: nums = [3,7,1,6] Output: 5
 * Example 2: Input: nums = [10,1] Output: 10
 * <p>
 * Constraints:
 * n == nums.length, 1 <= n <= 10^5, 0 <= nums[i] <= 10^9
 */
@SuppressWarnings("unused")
public final class MinimizeMaxOfArray {
    private MinimizeMaxOfArray() {}

    /** Prefix sum greedy. O(n) time, O(1) space. */
    public static class PrefixSum {
        public static int minimizeArrayValue(int[] nums) {
            long prefixSum = 0;
            long res = 0;
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                // ceil(prefixSum / (i+1))
                long ceil = (prefixSum + i) / (i + 1);
                res = Math.max(res, ceil);
            }
            return (int) res;
        }
    }

    /** Binary search on answer. O(n log(max)) time, O(1) space. */
    public static class BinarySearch {
        public static int minimizeArrayValue(int[] nums) {
            int lo = 0, hi = 0;
            for (int v : nums) hi = Math.max(hi, v);
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (feasible(nums, mid)) hi = mid;
                else lo = mid + 1;
            }
            return lo;
        }

        private static boolean feasible(int[] nums, int mid) {
            long excess = 0;
            for (int v : nums) {
                excess += mid - v;
                if (excess < 0) return false;
            }
            return true;
        }
    }
}
