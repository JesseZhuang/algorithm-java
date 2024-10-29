package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 2684, medium, tags: array, dp, matrix.
 * <p>
 * You are given a 0-indexed m x n matrix grid consisting of positive integers.
 * <p>
 * You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
 * <p>
 * From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1)
 * such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
 * Return the maximum number of moves that you can perform.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
 * Output: 3
 * Explanation: We can start at the cell (0, 0) and make the following moves:
 * - (0, 0) -> (0, 1).
 * - (0, 1) -> (1, 2).
 * - (1, 2) -> (2, 3).
 * It can be shown that it is the maximum number of moves that can be made.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
 * Output: 0
 * Explanation: Starting from any cell in the first column we cannot perform any moves.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * 1 <= grid[i][j] <= 10^6
 * <p>
 * Hint 1
 * Consider using dynamic programming to find the maximum number of moves that can be made from each cell.
 * Hint 2
 * The final answer will be the maximum value in cells of the first column.
 */
@SuppressWarnings("unused")
public class MaxMovesGrid {
    // bfs, mn, m
    static class Solution {
        private final int[] dirs = {-1, 0, 1};

        public int maxMoves(int[][] grid) {
            int M = grid.length, N = grid[0].length;
            Queue<int[]> q = new LinkedList<>(); // r,c,moves
            boolean[] vis;
            for (int i = 0; i < M; i++) q.add(new int[]{i, 0, 0});
            int res = 0;
            while (!q.isEmpty()) {
                int sz = q.size();
                vis = new boolean[M];
                while (sz-- > 0) {
                    int[] v = q.remove();
                    int r = v[0], c = v[1], moves = v[2];
                    res = Math.max(res, moves);
                    for (int dir : dirs) {
                        int nr = r + dir, nc = c + 1;
                        if (nr < 0 || nr > M - 1 || nc > N - 1 || grid[r][c] >= grid[nr][nc] || vis[nr]) continue;
                        vis[nr] = true;
                        q.offer(new int[]{nr, nc, moves + 1});
                    }
                }
            }
            return res;
        }
    }

    // dp, mn. m
    // dp[r][c] max moves+1 for row r, col c
    static class Solution2 {
        public int maxMoves(int[][] grid) {
            final int[] dirs = {-1, 0, 1};
            int M = grid.length, N = grid[0].length;
            int[] dp = new int[M], ndp;
            Arrays.fill(dp, 1); // first col reachable
            int res = 0;
            for (int c = 1; c < N; c++) {
                ndp = new int[M];
                for (int r = 0; r < M; r++) {
                    for (int d : dirs) {
                        int pr = r + d;
                        if (pr < 0 || pr > M - 1 || grid[r][c] <= grid[pr][c - 1] || dp[pr] < 1) continue;
                        ndp[r] = Math.max(ndp[r], dp[pr] + 1);
                    }
                    res = Math.max(res, ndp[r] - 1);
                }
                dp = ndp;
            }
            return res;
        }
    }
}
