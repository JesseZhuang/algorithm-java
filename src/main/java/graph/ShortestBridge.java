package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 934, medium, tags: array, dfs, bfs, matrix.
 * <p>
 * Given an n x n binary matrix grid with exactly two islands, return the smallest number
 * of 0s that must be flipped to connect the two islands.
 * <p>
 * Constraints:
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] is either 0 or 1.
 * There are exactly two islands in grid.
 */
@SuppressWarnings("unused")
public final class ShortestBridge {
    private ShortestBridge() {}

    // DFS + multi-source BFS, O(n^2) time, O(n^2) space.
    static class Solution {
        private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int shortestBridge(int[][] grid) {
            int n = grid.length;
            Queue<int[]> queue = new LinkedList<>();
            boolean found = false;
            // DFS to find and mark first island as 2, add its cells to queue.
            for (int i = 0; i < n && !found; i++) {
                for (int j = 0; j < n && !found; j++) {
                    if (grid[i][j] == 1) {
                        dfs(grid, i, j, queue);
                        found = true;
                    }
                }
            }
            // Multi-source BFS to expand from the first island until reaching the second.
            int steps = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] cell = queue.poll();
                    for (int[] d : DIRS) {
                        int nr = cell[0] + d[0], nc = cell[1] + d[1];
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr][nc] == 2) continue;
                        if (grid[nr][nc] == 1) return steps;
                        grid[nr][nc] = 2;
                        queue.offer(new int[]{nr, nc});
                    }
                }
                steps++;
            }
            return -1; // should not reach here
        }

        private void dfs(int[][] grid, int r, int c, Queue<int[]> queue) {
            int n = grid.length;
            if (r < 0 || r >= n || c < 0 || c >= n || grid[r][c] != 1) return;
            grid[r][c] = 2;
            queue.offer(new int[]{r, c});
            for (int[] d : DIRS) {
                dfs(grid, r + d[0], c + d[1], queue);
            }
        }
    }
}
