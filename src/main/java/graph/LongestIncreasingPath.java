package graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 329. Longest Increasing Path in a Matrix (hard).
 * Tags: array, dynamic programming, dfs, bfs, graph, topological sort, memoization, matrix.
 * <p>
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * From each cell, you can move in four directions: left, right, up, or down.
 */
public final class LongestIncreasingPath {

    private LongestIncreasingPath() {
    }

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // O(1) 4-direction check

    /**
     * DFS + memoization approach.
     * Time O(m*n), Space O(m*n).
     */
    public static int longestIncreasingPathDFS(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n]; // O(m*n) space
        int result = 1;
        for (int i = 0; i < m; i++) { // O(m*n) iterate all cells
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(matrix, memo, i, j, m, n));
            }
        }
        return result;
    }

    private static int dfs(int[][] matrix, int[][] memo, int i, int j, int m, int n) {
        if (memo[i][j] != 0) return memo[i][j];
        int longest = 1;
        for (int[] d : DIRS) { // O(1) 4-direction check
            int ni = i + d[0], nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && matrix[ni][nj] > matrix[i][j]) {
                longest = Math.max(longest, 1 + dfs(matrix, memo, ni, nj, m, n));
            }
        }
        memo[i][j] = longest;
        return longest;
    }

    /**
     * Topological sort (BFS) approach — peel layers from local minima.
     * Time O(m*n), Space O(m*n).
     */
    public static int longestIncreasingPathBFS(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] inDegree = new int[m][n]; // O(m*n) space

        // Build in-degree: count neighbors with smaller value pointing to this cell
        for (int i = 0; i < m; i++) { // O(m*n) iterate all cells
            for (int j = 0; j < n; j++) {
                for (int[] d : DIRS) { // O(1) 4-direction check
                    int ni = i + d[0], nj = j + d[1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && matrix[ni][nj] < matrix[i][j]) {
                        inDegree[i][j]++;
                    }
                }
            }
        }

        // Enqueue all local minima (in-degree == 0)
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) { // O(m*n)
            for (int j = 0; j < n; j++) {
                if (inDegree[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // BFS layer by layer
        int layers = 0;
        while (!queue.isEmpty()) { // O(m*n) total across all layers
            int size = queue.size();
            layers++;
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                for (int[] d : DIRS) { // O(1) 4-direction check
                    int ni = cell[0] + d[0], nj = cell[1] + d[1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && matrix[ni][nj] > matrix[cell[0]][cell[1]]) {
                        if (--inDegree[ni][nj] == 0) {
                            queue.offer(new int[]{ni, nj});
                        }
                    }
                }
            }
        }
        return layers;
    }
}
