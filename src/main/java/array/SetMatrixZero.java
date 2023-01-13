package array;

/**
 * LeetCode 73, medium, tags: array, hash table, matrix.
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * <p>
 * You must do it in place.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/08/17/mat1.jpg
 * <p>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2: https://assets.leetcode.com/uploads/2020/08/17/mat2.jpg
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 * <p>
 * Follow up:
 * <p>
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * Hints:
 * If any cell of the matrix has a zero we can record its row and column number using additional memory.
 * But if you don't want to use extra memory then you can manipulate the array instead. i.e.
 * simulating exactly what the question says.
 * Setting cell values to zero on the fly while iterating might lead to discrepancies. What if you
 * use some other integer value as your marker? There is still a better approach for this problem with 0(1) space.
 * We could have used 2 sets to keep a record of rows/columns which need to be set to zero. But for
 * an O(1) space solution, you can use one of the rows and one of the columns to keep track of this information.
 * We can use the first cell of every row and column as a flag. This flag would determine whether a row
 * or column has been set to zero.
 */
public class SetMatrixZero {
    // 1ms, 43.6MB. O(mn) time, O(1) space.
    // Other ideas, use m*n space or m+n space to store the flags for all cells, or each row and col
    public void setZeroes(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        boolean setCol0 = false; // flag for col0, 0,0 used for row0 flag
        for (int i = 0; i < r; i++) {
            if (matrix[i][0] == 0) setCol0 = true;
            for (int j = 1; j < c; j++) // store flag in i,0 for row i and 0,j for col j
                if (matrix[i][j] == 0) matrix[i][0] = matrix[0][j] = 0;
        }
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            if (setCol0) matrix[i][0] = 0;
        }
    }
}
