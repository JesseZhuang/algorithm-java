package array;

/**
 * LeetCode 713, medium, tags: array, binary search, sliding window, prefix sum.
 * <p>
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of
 * all the elements in the subarray is strictly less than k.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 10^6
 * <p>
 * Hint 1
 * For each j, let opt(j) be the smallest i so that nums[i] * nums[i+1] * ... * nums[j] is less than k.
 * opt is an increasing function.
 */
@SuppressWarnings("unused")
public class SubarrayProdLK {
    // sliding window, n, 1. 4ms, 48mb.
    static class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            // Handle edge cases where k is 0 or 1 (no subarrays possible)
            if (k <= 1) return 0;
            int res = 0, prod = 1;
            // Use two pointers to maintain a sliding window
            for (int left = 0, right = 0; right < nums.length; right++) {
                // Expand the window by including the element at the right pointer
                prod *= nums[right];
                // Shrink the window from the left while the prod is greater than or equal to k
                while (prod >= k) prod /= nums[left++];
                // [10,5,2,6] k:100 for window [10,5] valid subarrays are [10,5],[5]
                // for window [5,2,6]: [5,2,6], [2,6], [6], counting subarrays ending at right index
                res += right - left + 1;  // right - left + 1 represents the current window size
            }
            return res;
        }
    }

    // binary search, motivation: product may be too large overflow 1000^50000. nlgn, n. 28ms, 47.62mb.
    static class Solution2 {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k == 0) return 0; // [10,5,2,6] k:100
            double logK = Math.log(k);
            int m = nums.length + 1;
            double[] lps = new double[m]; // log prefix sum
            // important lps[0]=0, will compare lps[mid]-lps[cur]
            // e.g., lps[3]-lps[0]:lg100 meaning nums[1,3]:lg100==lgK
            for (int i = 0; i < nums.length; i++) lps[i + 1] = lps[i] + Math.log(nums[i]);
            int res = 0;
            for (int cur = 0; cur < m; cur++) {
                int low = cur + 1, high = m; // important to +1 for low and high
                // e.g, cur:1, lg(5*2*6)<lgK should count all three, lo ends at 5, 5-1-1==3
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (lps[mid] - lps[cur] < logK - 1e-9) low = mid + 1;
                    else high = mid;
                }
                res += low - cur - 1;
            }
            return res;
        }
    }
}
