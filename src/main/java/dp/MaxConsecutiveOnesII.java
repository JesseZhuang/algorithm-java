package dp;

/**
 * LeetCode 487, LintCode 883, medium.
 */
@SuppressWarnings("unused")
public class MaxConsecutiveOnesII {
    // n, 1. easier to understand, maintain k, r, l.
    static class Solution2 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int l = 0, r = 0;
            int k = 1;
            while (r < nums.length) {
                if (nums[r++] == 0) --k;
                if (k < 0 && nums[l++] == 0) ++k; // only increment when k<0, window never shrinks
            }
            return r - l;
        }
    }

    // n,1. sliding window, dp.
    // the window is [l,i] where i is the current index, at the end i == nums.length
    // the result will only grow and never shrink, we just need to maintain the correct count of zeroes
    static class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            int l = 0, cnt = 0;
            for (int x : nums) {
                cnt += x ^ 1;
                if (cnt > 1) cnt -= nums[l++] ^ 1;
            }
            return nums.length - l;
        }
    }
}
