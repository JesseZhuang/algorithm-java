package graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 329, hard, tags: array, dynamic programming, dfs, bfs, graph, topological sort, memoization, matrix.
 * <p>
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * <p>
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or
 * move outside the boundary (i.e., wrap-around is not allowed).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * <p>
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 * <p>
 * Input: matrix = [[1]]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 */
public class LongestIPathMatrix {
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 4 directions

    // 8ms, 44.18Mb. dfs+dp, O(mn) time and space.
    public int longestIncreasingPathDFS(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int len = dfs(matrix, r, c, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for (int[] dir : dirs) {
            int nr = i + dir[0], nc = j + dir[1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || matrix[nr][nc] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, nr, nc, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }

    // solution 2, 15ms, 44.2Mb. bfs, mn time and space.
    public int longestIncreasingPathBFS(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] indegree = new int[m][n];
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr][nc] < matrix[r][c])
                        indegree[r][c]++;
                }

        Queue<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                if (indegree[r][c] == 0) q.add(new int[]{r, c});
        int res = 0;
        while (!q.isEmpty()) {
            int l = q.size();
            for (int i = 0; i < l; i++) {
                int[] rc = q.remove();
                int r = rc[0], c = rc[1];
                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr][nc] > matrix[r][c]) {
                        indegree[nr][nc]--;
                        if (indegree[nr][nc] == 0) q.add(new int[]{nr, nc});
                    }
                }
            }
            res++;
        }
        return res;
    }
}
