package array;

import java.util.Arrays;

/**
 * LeetCode 3254, medium.
 */
@SuppressWarnings("unused")
public class PowerKSizeSubArraysI {
    // n,1.
    static class Solution {
        public int[] resultsArray(int[] nums, int k) {
            if (k == 1) return nums; // every single element is a valid subarray
            int n = nums.length, res[] = new int[n - k + 1];
            Arrays.fill(res, -1); // Initialize results to -1
            for (int i = 0, streak = 1; i < n - 1; i++) {
                if (nums[i] + 1 == nums[i + 1]) streak++;
                else streak = 1;
                if (streak >= k) res[i - k + 2] = nums[i + 1];
            }
            return res;
        }
    }
}
