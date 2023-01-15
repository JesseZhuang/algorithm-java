package array;

/**
 * LeetCode 48, medium, tags: array, math, matrix.
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * Example 2: https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg
 * <p>
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 * <p>
 * Bonus Question: If you're not too confident with matrices and linear algebra, get some more practice by also
 * coding a method that transposes the matrix on the other diagonal, and another that reflects from top to bottom.
 * You can test your functions by printing out the matrix before and after each operation. Finally, use your functions
 * to find three more solutions to this problem. Each solution uses two matrix operations.
 */
public class RotateImage {
    // 0ms, 40.9MB. O(N) O(n^2) time, O(1) space.
    public void rotate1(int[][] matrix) {
        int n = matrix.length; // using 5x5 matrix as example,
        for (int i = 0; i < n / 2; i++) // layers need to do rotation: 5x5, 3x3
            for (int j = i; j < n - i - 1; j++) { // elements to rotate in this layer {0,0}{0,1}{0,2}{0,3}; {1,1}{1,2}
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i]; // [x1][y1] = [x2][y2]. x1 == y2, y1 x2 symmetrical y1 = n-1-x2
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
    }

    // 0ms, 40.7Mb. transpose then reflect. O(N) O(n^2) time, O(1) space.
    // Another method: reverse up down then transpose
    // for counterclockwise rotate, first reverse left right then transpose
    // for 180, reflect left right then up down
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) // transpose
            for (int j = i + 1; j < n; j++) { // note boundaries
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        for (int i = 0; i < n; i++) // reflect, mirror left and right
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
    }
}
