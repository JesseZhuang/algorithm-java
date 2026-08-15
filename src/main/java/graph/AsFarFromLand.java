package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1162, medium, tags: array, dynamic programming, bfs, matrix.
 * <p>
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land,
 * find a water cell such that its distance to the nearest land cell is maximized, and return the distance.
 * If no land or water exists in the grid, return -1. The distance used is the Manhattan Distance.
 * <p>
 * Constraints:
 * <ul>
 *   <li>n == grid.length == grid[i].length</li>
 *   <li>1 <= n <= 100</li>
 *   <li>grid[i][j] is 0 or 1</li>
 * </ul>
 */
@SuppressWarnings("unused")
public final class AsFarFromLand {

    private AsFarFromLand() {}

    // Multi-source BFS, O(n^2) time, O(n^2) space.
    static class SolutionBFS {
        private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int maxDistance(int[][] grid) {
            int n = grid.length;
            Queue<int[]> queue = new LinkedList<>();
            // O(n^2) enqueue all land cells
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 1) queue.offer(new int[]{i, j});
            if (queue.isEmpty() || queue.size() == n * n) return -1; // all water or all land
            // BFS level by level, mark water cells with distance
            int dist = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                dist++;
                for (int k = 0; k < size; k++) {
                    int[] cell = queue.poll();
                    for (int[] d : DIRS) {
                        int nr = cell[0] + d[0], nc = cell[1] + d[1];
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr][nc] != 0) continue;
                        grid[nr][nc] = dist; // mark visited with distance
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            return dist - 1; // last completed level
        }
    }

    // DP two passes, O(n^2) time, O(1) extra space.
    static class Solution {
        public int maxDistance(int[][] grid) {
            int n = grid.length;
            int inf = n * 2; // max possible distance is 2*(n-1), use n*2 as infinity
            // O(n^2) first pass: top-left to bottom-right
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = 0; // land has distance 0
                    } else {
                        grid[i][j] = inf;
                        if (i > 0) grid[i][j] = Math.min(grid[i][j], grid[i - 1][j] + 1);
                        if (j > 0) grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1);
                    }
                }
            }
            // O(n^2) second pass: bottom-right to top-left, track max distance
            int max = 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (i < n - 1) grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
                    if (j < n - 1) grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);
                    max = Math.max(max, grid[i][j]);
                }
            }
            return (max == 0 || max >= inf) ? -1 : max; // 0 means all land, >= inf means all water
        }
    }
}
