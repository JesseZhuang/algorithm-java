package array;

/**
 * LeetCode 209, medium, tags: array, binary search, sliding window, prefix sum.
 * <p>
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * Example 1:
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * Example 2:
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * <p>
 * Example 3:
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity
 * is O(n log(n)).
 */
@SuppressWarnings("unused")
public final class MinSizeSubarraySum {

    private MinSizeSubarraySum() {
    }

    // Solution 1: Sliding window. O(n) time, O(1) space.
    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;

        // O(n) - each element visited at most twice (once by right, once by left)
        for (int right = 0; right < n; right++) {
            sum += nums[right];

            // Shrink window from left while sum >= target
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // Solution 2: Prefix sum + binary search. O(n log n) time, O(n) space.
    public static int minSubArrayLenBinarySearch(int target, int[] nums) {
        int n = nums.length;

        // Build prefix sum array. O(n) time, O(n) space.
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int minLen = Integer.MAX_VALUE;

        // For each starting position, binary search for the ending position. O(n log n) time.
        for (int i = 0; i < n; i++) {
            // Find the smallest j where prefixSum[j] - prefixSum[i] >= target
            // Which means prefixSum[j] >= target + prefixSum[i]
            int targetSum = target + prefixSum[i];

            // Binary search in [i+1, n]. O(log n) time.
            int left = i + 1, right = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (prefixSum[mid] >= targetSum) {
                    minLen = Math.min(minLen, mid - i);
                    right = mid - 1; // Try to find smaller length
                } else {
                    left = mid + 1;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
