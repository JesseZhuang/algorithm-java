package array;

import java.util.Arrays;

/**
 * https://www.facebook.com/careers/life/sample_interview_questions
 * <p>
 * Find the pattern and complete the function:
 * <pre>int[][] spiral(int n);</pre>
 * where n is the size of the 2D array.
 * <p>
 * Sample result:
 * <pre>
 * input = 3
 * 123
 * 894
 * 765
 *
 * input = 4
 * 01 02 03 04
 * 12 13 14 05
 * 11 16 15 06
 * 10 09 08 07
 *
 * input = 8
 * 1 2 3 4 5 6 7 8
 * 28 29 30 31 32 33 34 9
 * 27 48 49 50 51 52 35 10
 * 26 47 60 61 62 53 36 11
 * 25 46 59 64 63 54 37 12
 * 24 45 58 57 56 55 38 13
 * 23 44 43 42 41 40 39 14
 * 22 21 20 19 18 17 16 15
 * </pre>
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>find a pattern of how often you move each four direction O(n) time, 0 extra space.</b>
 * <li>recursive, O(n) time, stack space.
 * </ul>
 */
public class TwoDSprial {

    // the four directions for incrementing the numbers, {row, column}
    private static int[][] directions = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int[][] spiralIterative(int n) {
        if (n < 1) throw new IllegalArgumentException("Input number cannot be less than 1.");
        int[][] result = new int[n][n];
        int val = 0, limit = n * n, curDirection = 0, row = 0, column = 0;
        while (val++ < limit) {
            result[row][column] = val;
            row += directions[curDirection][0];
            column += directions[curDirection][1];
            if (!inBound(row, column, result)) {
                row -= directions[curDirection][0];
                column -= directions[curDirection][1];
                curDirection = (curDirection + 1) % 4;
                row += directions[curDirection][0];
                column += directions[curDirection][1];
            }
        }
        return result;
    }

    private boolean inBound(int row, int column, int[][] result) {
        int n = result.length;
        return row < n && row >= 0 && column < n && column >= 0 && result[row][column] == 0;
    }

    public int[][] spiralRecursive(int n) {
        if (n < 1) throw new IllegalArgumentException("Input number cannot be less than 1.");
        int[][] result = new int[n][n];
        recursiveHelper(n, result, 1, 0, 0);
        return result;
    }

    private void recursiveHelper(int n, int[][] result, int startNumber, int row, int column) {
        if (n < 1) return;
        if (n == 1) result[row][column] = startNumber;
        int i = 0;
        while (i++ < n) result[row][column++] = startNumber++;
        i = 0;
        column--;
        while (i++ < n - 1) result[++row][column] = startNumber++;
        i = 0;
        while (i++ < n - 1) result[row][--column] = startNumber++;
        i = 0;
        while (i++ < n - 2) result[--row][column] = startNumber++;
        recursiveHelper(n - 2, result, startNumber, row, column + 1);
    }
}
