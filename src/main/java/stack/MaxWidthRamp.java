package stack;

import java.util.Stack;

/**
 * LeetCode 962, medium, tags: array, stack, monotonic stack.
 * <p>
 * A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such
 * a ramp is j - i.
 * <p>
 * Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [6,0,8,2,1,5]
 * Output: 4
 * Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
 * Example 2:
 * <p>
 * Input: nums = [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5 * 10^4
 */
@SuppressWarnings("unused")
public class MaxWidthRamp {
    // brute force, n^2, 1. sort, nlgn, n+sort.
    // monotonic stack, n, n.
    static class Solution1 {
        public int maxWidthRamp(int[] nums) {
            int n = nums.length;
            Stack<Integer> s = new Stack<>();
            for (int i = 0; i < n; i++)
                if (s.isEmpty() || nums[s.peek()] > nums[i]) s.push(i);
            int res = 0;
            for (int j = n - 1; j >= 0; j--) {
                while (!s.isEmpty() && nums[s.peek()] <= nums[j]) {
                    res = Math.max(res, j - s.peek());
                    s.pop();
                }
            }
            return res;
        }
    }

    // two pointer, n, n.
    static class Solution2 {
        public int maxWidthRamp(int[] nums) {
            int n = nums.length;
            int[] rm = new int[n]; // rightmax, max in [i,n-1]
            rm[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) rm[i] = Math.max(rm[i + 1], nums[i]);
            int l = 0, r = 0, res = 0;
            while (r < n) {
                while (l < r && nums[l] > rm[r]) l++;
                res = Math.max(res, r - l);
                r++;
            }
            return res;
        }
    }
}
