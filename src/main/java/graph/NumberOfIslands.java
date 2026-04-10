package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 200 - Number of Islands.
 * <p>
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically.
 *
 * @see <a href="https://leetcode.com/problems/number-of-islands/">LeetCode 200</a>
 */
public final class NumberOfIslands {

    private NumberOfIslands() {
    }

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * DFS approach: for each unvisited '1', increment count and flood-fill via recursion.
     * <p>
     * Time: O(m * n) — each cell visited at most once.
     * Space: O(m * n) — recursion stack in worst case (all land).
     *
     * @param grid the input grid (a deep copy is made internally to avoid mutation)
     * @return number of islands
     */
    public static int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        char[][] g = deepCopy(grid);                    // avoid mutating caller's grid
        int m = g.length, n = g[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {                   // O(m * n) scan
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '1') {
                    count++;
                    dfs(g, i, j, m, n);                 // mark entire island visited
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] g, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || g[r][c] != '1') return;
        g[r][c] = '0';                                  // mark visited
        for (int[] d : DIRS) {                           // explore 4 neighbors
            dfs(g, r + d[0], c + d[1], m, n);           // O(1) per neighbor
        }
    }

    /**
     * BFS approach: for each unvisited '1', increment count and flood-fill via queue.
     * <p>
     * Time: O(m * n) — each cell visited at most once.
     * Space: O(min(m, n)) — queue holds at most min(m, n) cells on the frontier.
     *
     * @param grid the input grid (a deep copy is made internally to avoid mutation)
     * @return number of islands
     */
    public static int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        char[][] g = deepCopy(grid);                    // avoid mutating caller's grid
        int m = g.length, n = g[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {                   // O(m * n) scan
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '1') {
                    count++;
                    bfs(g, i, j, m, n);                 // mark entire island visited
                }
            }
        }
        return count;
    }

    private static void bfs(char[][] g, int r, int c, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        g[r][c] = '0';                                  // mark visited immediately
        while (!queue.isEmpty()) {                       // process frontier
            int[] cell = queue.poll();
            for (int[] d : DIRS) {                       // explore 4 neighbors
                int nr = cell[0] + d[0], nc = cell[1] + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && g[nr][nc] == '1') {
                    g[nr][nc] = '0';                     // mark before enqueue to avoid duplicates
                    queue.offer(new int[]{nr, nc});      // O(min(m, n)) queue size
                }
            }
        }
    }

    private static char[][] deepCopy(char[][] grid) {
        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone();
        }
        return copy;
    }
}
