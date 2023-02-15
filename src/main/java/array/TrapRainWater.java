package array;

/**
 * LeetCode 42, hard, tags: array, two pointer, dynamic programming, stack, monotonic stack.
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
 * water it can trap after raining.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class TrapRainWater {

    // 1ms, 43Mb. two pointer. O(n) time, O(1) space.
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, leftWall = 0, rightWall = 0, res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (leftWall < height[left]) leftWall = height[left];
                else res += leftWall - height[left];
                ++left;
            } else {
                if (rightWall < height[right]) rightWall = height[right];
                else res += rightWall - height[right];
                --right;
            }
        }
        return res;
    }
}
