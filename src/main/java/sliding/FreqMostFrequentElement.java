package sliding;

import java.util.Arrays;

/**
 * LeetCode 1838, medium, tags: array, binary search, greedy, sliding window, sorting, prefix sum.
 * <p>
 * The frequency of an element is the number of times it occurs in an array.
 * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and
 * increment the element at that index by 1. Return the maximum possible frequency of an element after performing
 * at most k operations.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,9,6], k = 2
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 */
@SuppressWarnings("unused")
public final class FreqMostFrequentElement {

    private FreqMostFrequentElement() {
    }

    // solution 1, sliding window. O(n log n) time, O(1) extra space.
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, res = 1;
        long windowSum = 0;
        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            // cost to make all elements in [left, right] equal to nums[right]
            while ((long) nums[right] * (right - left + 1) - windowSum > k) {
                windowSum -= nums[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    // solution 2, binary search + prefix sum. O(n log n) time, O(n) space.
    public static int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];
        int res = 1;
        // binary search on window size
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canAchieve(nums, prefix, mid, k)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    private static boolean canAchieve(int[] nums, long[] prefix, int size, int k) {
        for (int right = size - 1; right < nums.length; right++) {
            int left = right - size + 1;
            long windowSum = prefix[right + 1] - prefix[left];
            long cost = (long) nums[right] * size - windowSum;
            if (cost <= k) return true;
        }
        return false;
    }
}
