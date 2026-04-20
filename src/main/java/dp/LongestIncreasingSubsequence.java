package dp;

import java.util.Arrays;

/**
 * LeetCode 300, medium. Tags: array, binary search, dynamic programming.
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * Reference: https://en.wikipedia.org/wiki/Patience_sorting
 */
public final class LongestIncreasingSubsequence {

    private LongestIncreasingSubsequence() {
    }

    /**
     * Solution 1: DP with binary search (patience sorting). O(n log n) time, O(n) space.
     * Maintain a tails array where tails[i] is the smallest tail element for an increasing
     * subsequence of length i+1.
     */
    public static int lengthOfLISBinarySearch(int[] nums) {
        int[] tails = new int[nums.length]; // O(n) space: smallest tail for each subsequence length
        int size = 0; // tracks length of longest increasing subsequence found
        for (int num : nums) { // O(n) iterations
            int lo = 0, hi = size;
            while (lo < hi) { // O(log n) binary search for insertion point
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < num) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = num; // replace existing or extend tails
            if (lo == size) size++; // found a new longest subsequence
        }
        return size;
    }

    /**
     * Solution 2: Classic DP. O(n^2) time, O(n) space.
     * dp[i] = length of longest increasing subsequence ending at index i.
     */
    public static int lengthOfLISDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // O(n) space: LIS length ending at each index
        Arrays.fill(dp, 1); // base case: every element is a subsequence of length 1
        int max = 1;
        for (int i = 1; i < n; i++) { // O(n) outer loop
            for (int j = 0; j < i; j++) { // O(n) inner loop -> O(n^2) total
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // extend subsequence ending at j
                }
            }
            max = Math.max(max, dp[i]); // track global maximum
        }
        return max;
    }
}
