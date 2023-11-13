package tree;

/**
 * LeetCode 308, LintCode 817, hard, tags: segment tree, array, design, matrix.
 * <p>
 * Given a 2D matrix, handle multiple queries of the following types:
 * <p>
 * Update the value of a cell in matrix.
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and
 * lower right corner (row2, col2).
 * Implement the NumMatrix class:
 * <p>
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * void update(int row, int col, int val) Updates the value of matrix[row][col] to be val.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input
 * ["NumMatrix", "sumRegion", "update", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
 * Output
 * [null, 8, null, 10]
 * <p>
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)
 * numMatrix.update(3, 2, 2); // matrix changes from left image to right image
 * numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -1000 <= matrix[i][j] <= 1000
 * 0 <= row < m
 * 0 <= col < n
 * -1000 <= val <= 1000
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * At most 5000 calls will be made to sumRegion and update.
 */
public class RangeSumQuery2D {
    BinaryIndexedTree[] trees;
    int m, n;

    // solution 1, BIT. O(mn lgn) init, O(lgn) update, O(m lgn) sumRegion. O(mn) space. lint code 3566ms, 35.90Mb.
    public RangeSumQuery2D(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        trees = new BinaryIndexedTree[m];
        for (int r = 0; r < m; r++) {
            BinaryIndexedTree tree = new BinaryIndexedTree(matrix[r]);
            trees[r] = tree;
        }
    }

    public void update(int row, int col, int val) {
        BinaryIndexedTree tree = trees[row];
        int prev = tree.getSum(col) - tree.getSum(col - 1);
        tree.update(col, val - prev);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int r = row1; r <= row2; r++) {
            BinaryIndexedTree tree = trees[r];
            res += tree.getSum(col2) - tree.getSum(col1 - 1);
        }
        return res;
    }
}
