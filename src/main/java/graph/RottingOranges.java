package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/rotting-oranges/">LeetCode 994</a>, medium,
 * tags: array, graph, breadth-first search.
 */
public final class RottingOranges {
    private RottingOranges() {}

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * BFS from all rotten oranges simultaneously.
     * Time O(m*n), Space O(m*n).
     */
    public static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length, fresh = 0, res = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) q.offer(new int[]{i, j});
                else if (grid[i][j] == 1) fresh++;
            }
        while (!q.isEmpty() && fresh > 0) {
            res++;
            for (int size = q.size(); size > 0; size--) {
                int[] cell = q.poll();
                for (int[] d : DIRS) {
                    int nx = cell[0] + d[0], ny = cell[1] + d[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                    if (grid[nx][ny] != 1) continue;
                    fresh--;
                    grid[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return fresh == 0 ? res : -1;
    }
}
