package array;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * <p>
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 */
@SuppressWarnings("unused")
public class TrappingRainWater {

    // O(n) time, O(1) space. Two pointers tracking left max and right max walls.
    static class Solution {
        public int trap(int[] height) {
            if (height == null || height.length < 3) return 0;
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0, res = 0;
            while (left < right) { // converge from both ends
                if (height[left] < height[right]) { // process shorter side
                    if (height[left] >= leftMax) leftMax = height[left]; // update left wall
                    else res += leftMax - height[left]; // water trapped at left
                    left++;
                } else {
                    if (height[right] >= rightMax) rightMax = height[right]; // update right wall
                    else res += rightMax - height[right]; // water trapped at right
                    right--;
                }
            }
            return res;
        }
    }

    // O(n) time, O(n) space. Monotonic decreasing stack of indices.
    static class Solution2 {
        public int trap(int[] height) {
            if (height == null || height.length < 3) return 0;
            Deque<Integer> stack = new ArrayDeque<>(); // stores indices, monotonic decreasing
            int res = 0;
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) { // current bar is taller than stack top
                    int bottom = stack.pop(); // bottom of the trapped water
                    if (stack.isEmpty()) break; // no left wall
                    int width = i - stack.peek() - 1; // distance between left wall and right wall
                    int bounded = Math.min(height[i], height[stack.peek()]) - height[bottom]; // bounded height
                    res += width * bounded; // accumulate trapped water for this layer
                }
                stack.push(i); // push current index
            }
            return res;
        }
    }
}
