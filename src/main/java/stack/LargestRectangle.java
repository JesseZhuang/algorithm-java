package stack;

import java.util.Stack;

/**
 * LeetCode 85, hard, tags: array, stack, monotonic stack.
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2: https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg
 * <p>
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= heights.length <= 10^5, n
 * 0 <= heights[i] <= 10^4
 */
public class LargestRectangle {
    // 165ms, 54.5Mb. O(n) time and space. use a stack.
    public static int largestRectangleAreaStack(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack();
        int maxArea = 0;
        for (int i = 0; i <= n; i++) {
            int h = i == n ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int curHeight = heights[stack.pop()];
                int prevIndex = stack.isEmpty() ? -1 : stack.peek();
                int area = curHeight * (i - prevIndex - 1);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        return maxArea;
    }

    // 10ms, 54.2 Mb. O(n) time and space.
    public int largestRectangleAreaArray(int[] heights) {
        // the left and right wall for largest rectangle containing heights[i]
        int[] leftWall = new int[heights.length];
        int[] rightWall = new int[heights.length];
        rightWall[heights.length - 1] = heights.length;
        leftWall[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) p = leftWall[p];
            leftWall[i] = p;
        }
        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) p = rightWall[p];
            rightWall[i] = p;
        }
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++)
            maxArea = Math.max(maxArea, heights[i] * (rightWall[i] - leftWall[i] - 1));
        return maxArea;
    }

    public static void main(String[] args) {
        largestRectangleAreaStack(new int[]{2, 1, 5, 6, 2, 3});
    }
}
