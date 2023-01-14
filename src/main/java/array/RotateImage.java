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
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // only the corner elements move like this
        for (int l = n; l > 1; l -= 2) { // rotation layer's size, O(n/2)
            int start = (n - l) / 2;
            for (int i = 0; i < l - 1; i++) { // rotations in each layer, O(l)
                int temp1 = matrix[start + i][start];
                for (int j = 0; j < dirs.length; j--) { // j: rotation index, 4 steps needed each rotation
                    int nr = start + i + dirs[j][0] * (l - 1), nc = start + dirs[j][1] * (l - 1);
                    int temp2 = matrix[nr][nc];
                    matrix[nr][nc] = temp1;
                    temp1 = temp2;
                }
            }
        }
    }
}
