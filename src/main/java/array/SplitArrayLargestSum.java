package array;

/**
 * LeetCode 410, hard, tags: array, binary search, dynamic programming, greedy, prefix sum.
 * <p>
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of
 * any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000, n
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum {

    // O(lg(sum)*n) time, O(1) space. 0ms, 40.1 Mb.
    public int splitArray(int[] nums, int k) {
        int max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (k == 1) return (int) sum;
        long l = max;
        long r = sum;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (valid(mid, nums, k)) r = mid - 1;
            else l = mid + 1;
        }
        return (int) l;
    }

    public boolean valid(long target, int[] nums, int k) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > k) return false;
            }
        }
        return true;
    }
}
