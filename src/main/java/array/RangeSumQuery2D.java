package array;

/**
 * LeetCode 304, medium, tags: array, design, matrix, prefix sum.
 * Given a 2D matrix, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1)
 * and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 * <p>
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside
 * the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * You must design an algorithm where sumRegion works on O(1) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3],
 * [1, 1, 2, 2], [1, 2, 2, 4]]
 * Output
 * [null, 8, 11, 12]
 * <p>
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -104 <= matrix[i][j] <= 104
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * At most 104 calls will be made to sumRegion.
 */
public class RangeSumQuery2D {

    int[][] sums; // sums[r][c] == sum of matrix [0,0] to [r-1][c-1]

    // O(mn) time and space init. 124 ms, 66.6 Mb.
    public RangeSumQuery2D(int[][] matrix) {
        sums = new int[matrix.length + 1][matrix[0].length + 1]; // extra dummy row col for simplification
        for (int r = 1; r <= matrix.length; r++)
            for (int c = 1; c <= matrix[0].length; c++)
                sums[r][c] = sums[r - 1][c] + sums[r][c - 1] - sums[r - 1][c - 1] + matrix[r - 1][c - 1];
    }

    // O(1) time and space.
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[++row2][++col2] - sums[row2][++col1 - 1] - sums[++row1 - 1][col2] + sums[row1 - 1][col1 - 1];
    }

}
