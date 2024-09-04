package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1293, hard, tags: array, bfs, matrix.
 * <p>
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up,
 * down, left, or right from and to an empty cell in one step.
 * <p>
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner
 * (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) ->
 * (1,2) -> (2,2) -> (3,2) -> (4,2).
 * Example 2:
 * <p>
 * Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 * Output: -1
 * Explanation: We need to eliminate at least two obstacles to find such a walk.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 40
 * 1 <= k <= m * n
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 * <p>
 * Hint 1: Use BFS.
 * Hint 2: BFS on (x,y,r) x,y is coordinate, r is remain number of obstacles you can remove.
 */
public class SPGridObsEli {
    // bfs, mnk time, mnk space. 25ms, 45.81Mb.
    public int shortestPath(int[][] grid, int k) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int r = grid.length, c = grid[0].length;
        Queue<int[]> q = new LinkedList<>(); // do not need pq since all edge weights same or no edge weight
        boolean[][][] visited = new boolean[r][c][k + 1];
        visited[0][0][0] = true; // do not forget
        q.add(new int[]{0, 0, 0});
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.remove();
                int cr = cur[0], cc = cur[1], ck = cur[2];
                if (cr == r - 1 && cc == c - 1) return res;
                for (int[] dir : dirs) {
                    int nr = dir[0] + cr, nc = dir[1] + cc, nk = ck; // nk = ck cannot be outside the inner loop
                    if (nr < 0 || nr > r - 1 || nc < 0 || nc > c - 1) continue; // do not forget
                    if (grid[nr][nc] == 1) nk++;
                    if (nk > k || visited[nr][nc][nk]) continue; // do not forget visited, > not >=
                    visited[nr][nc][nk] = true;
                    q.add(new int[]{nr, nc, nk});
                }
            }
            res++;
        }
        return -1; // not return res
    }
}
