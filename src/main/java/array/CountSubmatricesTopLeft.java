package array;

/**
 * LeetCode 3070, medium, tags: array, matrix, prefix sum.
 * <p>
 * Given an m x n grid and an integer k, return the number of submatrices that have the top-left element at
 * grid[0][0] and a sum less than or equal to k.
 */
public class CountSubmatricesTopLeft {
    // O(mn) time, O(1) extra space (in-place prefix sum).
    // 6ms, 161.22Mb.
    static class Solution {
        public int countSubmatrices(int[][] grid, int k) {
            int m = grid.length;
            int n = grid[0].length;
            int res = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int up = r > 0 ? grid[r - 1][c] : 0;
                    int left = c > 0 ? grid[r][c - 1] : 0;
                    int diag = (r > 0 && c > 0) ? grid[r - 1][c - 1] : 0;
                    grid[r][c] += up + left - diag;
                    if (grid[r][c] <= k) {
                        res++;
                    } else {
                        break;
                    }
                }
            }
            return res;
        }
    }
}
