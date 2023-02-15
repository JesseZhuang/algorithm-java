package array;

import java.util.Stack;

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
    public int trap2p(int[] height) {
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

    // 1ms, 42.9 Mb, DP. O(n) time, O(n) space.
    public int trapDP(int[] height) {
        int res = 0, len = height.length;
        int[] leftWall = new int[len], rightWall = new int[len];
        leftWall[0] = height[0];
        for (int i = 1; i < len; i++) leftWall[i] = Math.max(height[i], leftWall[i - 1]);
        rightWall[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) rightWall[i] = Math.max(height[i], rightWall[i + 1]);
        for (int i = 1; i < len - 1; i++) res += Math.min(leftWall[i], rightWall[i]) - height[i];
        return res;
    }

    // 13ms, 43.1 Mb. Stack,  O(n) time, O(n) space (worst case stairs-like or flat structure).
    public int trapStack(int[] height) {
        int res = 0, cur = 0;
        Stack<Integer> s = new Stack<>();
        while (cur < height.length) {
            while (!s.isEmpty() && height[cur] > height[s.peek()]) {
                int top = s.pop();
                if (s.isEmpty()) break;
                int distance = cur - s.peek() - 1; // rightWall - leftWall - 1
                int h = Math.min(height[cur], height[s.peek()]) - height[top];
                res += distance * h;
            }
            s.push(cur++);
        }
        return res;
    }

}
