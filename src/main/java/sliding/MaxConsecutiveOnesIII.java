package sliding;

/**
 * LeetCode 1004, medium, tags: array, binary search, sliding window, prefix sum.
 * <p>
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the
 * array if you can flip at most k 0's.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,<u>1,1,1,1,1,1</u>]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,<u>1,1,1,1,1,1,1,1,1,1</u>,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 */
@SuppressWarnings("unused")
public final class MaxConsecutiveOnesIII {

    private MaxConsecutiveOnesIII() {
    }

    // solution 1, sliding window. O(n) time, O(1) space.
    public static int longestOnes(int[] nums, int k) {
        int left = 0, res = 0, zeros = 0; // zeros: count of 0's in current window
        for (int right = 0; right < nums.length; right++) { // O(n) expand right
            if (nums[right] == 0) zeros++;
            while (zeros > k) { // shrink left until window valid
                if (nums[left] == 0) zeros--;
                left++;
            }
            res = Math.max(res, right - left + 1); // update max window size
        }
        return res;
    }

    // solution 2, binary search + prefix sum of zeros. O(n log n) time, O(n) space.
    public static int longestOnes2(int[] nums, int k) {
        int n = nums.length;
        int[] zeroPrefix = new int[n + 1]; // zeroPrefix[i]: number of 0's in nums[0..i-1]
        for (int i = 0; i < n; i++) zeroPrefix[i + 1] = zeroPrefix[i] + (nums[i] == 0 ? 1 : 0);
        int res = 0;
        // binary search on window size
        int lo = 0, hi = n; // O(log n) binary search
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFit(zeroPrefix, mid, k)) { // check if any window of size mid has <= k zeros
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    // check if any window of given size has at most k zeros. O(n) scan.
    private static boolean canFit(int[] zeroPrefix, int size, int k) {
        for (int right = size; right < zeroPrefix.length; right++) { // slide window of given size
            int zerosInWindow = zeroPrefix[right] - zeroPrefix[right - size]; // zeros in [right-size, right-1]
            if (zerosInWindow <= k) return true;
        }
        return false;
    }
}
