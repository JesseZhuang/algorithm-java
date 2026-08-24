package stack;

import java.util.Stack;

/**
 * LeetCode 85, hard, tags: array, dynamic programming, stack, matrix, monotonic stack.
 * <p>
 * Given a rows x cols binary matrix filled with '0's and '1's, find the largest rectangle
 * containing only '1's and return its area.
 * <p>
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * <p>
 * Example 2:
 * Input: matrix = [["0"]]
 * Output: 0
 * <p>
 * Example 3:
 * Input: matrix = [["1"]]
 * Output: 1
 * <p>
 * Constraints:
 * rows == matrix.length
 * cols == matrix[i].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] is '0' or '1'.
 */
public final class MaximalRectangle {

    private MaximalRectangle() {
    }

    /**
     * Histogram + monotonic stack approach. Build histogram heights row by row,
     * then apply largest rectangle in histogram (problem 84) for each row.
     * Time O(m*n), Space O(n).
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) { // O(m) rows
            for (int j = 0; j < n; j++) { // O(n), build histogram for current row
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleInHistogram(heights));
        }
        return maxArea;
    }

    /**
     * Monotonic stack to find largest rectangle in histogram.
     * Time O(n), Space O(n). Each index pushed and popped at most once.
     */
    private static int largestRectangleInHistogram(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= n; i++) { // O(n), each index pushed/popped once
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

    /**
     * DP approach tracking height, left boundary, and right boundary per cell.
     * Time O(m*n), Space O(n).
     */
    public static int maximalRectangleDP(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int[] left = new int[n];   // left boundary of rectangle at (i,j)
        int[] right = new int[n];  // right boundary (exclusive) of rectangle at (i,j)
        java.util.Arrays.fill(right, n);
        int maxArea = 0;

        for (int i = 0; i < m; i++) { // O(m) rows
            int curLeft = 0, curRight = n;

            // O(n), update height and left boundary
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            // O(n), update right boundary (scan right to left)
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }

            // O(n), compute max area
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, height[j] * (right[j] - left[j]));
            }
        }
        return maxArea;
    }
}
