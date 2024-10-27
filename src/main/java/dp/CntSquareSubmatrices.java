package dp;

/**
 * LeetCode 1277, medium, tags: array, dp, matrix.
 * <p>
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix =
 * [
 * [0,1,1,1],
 * [1,1,1,1],
 * [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 * <p>
 * Input: matrix =
 * [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * <p>
 * Hint 1
 * Create an additive table that counts the sum of elements of submatrix with the superior corner at (0,0).
 * Hint 2
 * Loop over all subsquares in O(n^3) and check if the sum make the whole array to be ones, if it checks then
 * add 1 to the answer.
 */
@SuppressWarnings("unused")
public class CntSquareSubmatrices {
    // todo, O(r*c) time and space.
    static class Solution {
        public int countSquares(int[][] matrix) {
            int row = matrix.length, col = matrix[0].length;
            int[][] dp = new int[row + 1][col + 1];
            int ans = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == 1) {
                        dp[i + 1][j + 1] = Math.min(
                                Math.min(dp[i][j + 1], dp[i + 1][j]),
                                dp[i][j]
                        ) +
                                1;
                        ans += dp[i + 1][j + 1];
                    }
                }
            }
            return ans;
        }
    }
}
