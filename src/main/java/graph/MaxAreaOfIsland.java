package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 695 - Max Area of Island.
 * <p>
 * Given an m x n binary matrix grid, return the size of the largest island.
 * An island is a group of 1's connected 4-directionally. If no island, return 0.
 *
 * @see <a href="https://leetcode.com/problems/max-area-of-island/">LeetCode 695</a>
 */
public final class MaxAreaOfIsland {

    private MaxAreaOfIsland() {
    }

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * DFS approach: for each unvisited 1, compute area via recursion, track max.
     * <p>
     * Time: O(m * n) — each cell visited at most once.
     * Space: O(m * n) — recursion stack in worst case (all land).
     */
    public static int maxAreaDFS(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {           // O(m)
            for (int j = 0; j < n; j++) {       // O(n)
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j, m, n));
                }
            }
        }
        return max;
    }

    private static int dfs(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) return 0;
        grid[i][j] = 0; // mark visited
        int area = 1;
        for (int[] d : DIRS) { // O(m*n) total across all calls
            area += dfs(grid, i + d[0], j + d[1], m, n);
        }
        return area;
    }

    /**
     * BFS approach: for each unvisited 1, compute area via queue, track max.
     * <p>
     * Time: O(m * n) — each cell visited at most once.
     * Space: O(min(m, n)) — queue size bounded by shorter dimension.
     */
    public static int maxAreaBFS(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {           // O(m)
            for (int j = 0; j < n; j++) {       // O(n)
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    q.offer(new int[]{i, j});
                    int area = 0;
                    while (!q.isEmpty()) {      // O(m*n) total across all calls
                        int[] cell = q.poll();
                        area++;
                        for (int[] d : DIRS) {
                            int nr = cell[0] + d[0], nc = cell[1] + d[1];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                                grid[nr][nc] = 0;
                                q.offer(new int[]{nr, nc});
                            }
                        }
                    }
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }
}
