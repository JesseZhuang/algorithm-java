package graph;

/**
 * LeetCode 542 LintCode 974, medium, tags: array, dynamic programming, bfs, matrix.
 * <p>
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two cells sharing a common edge is 1.
 * <p>
 * Example 1:
 * <p>
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * <p>
 * Example 2:
 * <p>
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 * <p>
 * Constraints:
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 */
@SuppressWarnings("unused")
public final class ZeroOneMatrix {
    private ZeroOneMatrix() {
    }

    // DP, O(m*n) time, O(1) space (in-place).
    static class Solution {
        public int[][] updateMatrix(int[][] mat) {
            int m = mat.length, n = mat[0].length;
            int INF = m + n;
            // top-left to bottom-right
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (mat[r][c] > 0) {
                        int top = r > 0 ? mat[r - 1][c] : INF;
                        int left = c > 0 ? mat[r][c - 1] : INF;
                        mat[r][c] = Math.min(top, left) + 1;
                    }
                }
            }
            // bottom-right to top-left
            for (int r = m - 1; r >= 0; r--) {
                for (int c = n - 1; c >= 0; c--) {
                    if (mat[r][c] > 0) {
                        int bottom = r < m - 1 ? mat[r + 1][c] : INF;
                        int right = c < n - 1 ? mat[r][c + 1] : INF;
                        mat[r][c] = Math.min(mat[r][c], Math.min(bottom + 1, right + 1));
                    }
                }
            }
            return mat;
        }
    }
}
