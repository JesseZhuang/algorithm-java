package array;

/**
 * LeetCode 713, medium, tags: array, binary search, sliding window, prefix sum.
 * Companies: salesforce.
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
            if (k <= 1) return 0;
            int res = 0, prod = 1;
            for (int l = 0, r = 0; r < nums.length; r++) {
                prod *= nums[r]; // expand the sliding window
                while (prod >= k) prod /= nums[l++]; // shrink
                res += r - l + 1;
            }
            return res;
        }
    }

    // binary search, nlgn, n. 28ms, 47.62mb.
    static class Solution2 {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k == 0) return 0; // [10,5,2,6] k:100
            double logK = Math.log(k);
            int n = nums.length;
            double[] lps = new double[n + 1]; // log prefix sum [0, lg10, lg50, lg100, lg600]
            for (int i = 0; i < n; i++) lps[i + 1] = lps[i] + Math.log(nums[i]);
            int res = 0;
            for (int cur = 0; cur < n; cur++) {
                int low = cur + 1, high = n + 1;
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (lps[mid] - lps[cur] < logK - 1e-9) low = mid + 1;
                    else high = mid;
                }
                res += low - cur - 1; // cur=1, lg(5*2*6)<lg100, lo ends at 5, 5-1-1==3
            }
            return res;
        }
    }
}
