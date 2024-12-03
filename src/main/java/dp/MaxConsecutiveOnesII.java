package dp;

import java.util.ArrayDeque;

/**
 * LeetCode 487, LintCode 883, medium.
 */
@SuppressWarnings("unused")
public class MaxConsecutiveOnesII {
    // n, 1. easier to understand, maintain k, r, l.
    static class Solution2 {
        // 379 ms, 21.51 mb.
        public int findMaxConsecutiveOnes(int[] nums) {
            int l = 0, r = 0;
            int k = 1;
            while (r < nums.length) {
                if (nums[r++] == 0) --k;
                if (k < 0 && nums[l++] == 0) ++k; // only increment when k<0, window never shrinks
            }
            return r - l;
        }

        // 392 ms, 22.33 mb.
        public int followUp(int[] nums) {
            int l = 0, r = 0, k = 1, n = nums.length, res = 0;
            ArrayDeque<Integer> q = new ArrayDeque<>();
            while (r < n) {
                if (nums[r++] == 0) {
                    --k;
                    q.add(r - 1);
                }
                while (k < 0) {
                    l = q.remove() + 1;
                    ++k;
                }
                res = Math.max(res, r - l);
            }
            return res;
        }
    }

    // n,1. sliding window, dp. 542 ms, 22.09 mb.
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
