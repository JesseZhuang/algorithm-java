package graph;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 1091 - Shortest Path in Binary Matrix.
 * Find shortest clear path (8-directional) in an n x n binary matrix from (0,0) to (n-1,n-1).
 */
public final class ShortestPathBinaryMatrix {

    private ShortestPathBinaryMatrix() {
    }

    private static final int[][] DIRS = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},           {0, 1},
        {1, -1},  {1, 0},  {1, 1}
    };

    /**
     * BFS approach.
     * Time: O(n^2) — each cell visited at most once.
     * Space: O(n^2) — queue and visited grid.
     */
    public static int bfs(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1; // start or end blocked
        if (n == 1) return 1; // single cell

        boolean[][] visited = new boolean[n][n]; // track visited cells
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int dist = 1; // path length includes starting cell

        while (!queue.isEmpty()) {
            int size = queue.size(); // process level by level
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] d : DIRS) {
                    int nr = cell[0] + d[0], nc = cell[1] + d[1];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue; // bounds check
                    if (visited[nr][nc] || grid[nr][nc] != 0) continue;   // skip visited/blocked
                    if (nr == n - 1 && nc == n - 1) return dist + 1;      // reached target
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
            dist++;
        }
        return -1; // no path found
    }

    /**
     * A* with Chebyshev distance heuristic.
     * Time: O(n^2 log n) — priority queue operations over at most n^2 cells.
     * Space: O(n^2) — distance array and priority queue.
     */
    public static int aStar(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
        if (n == 1) return 1;

        int[][] dist = new int[n][n]; // g-cost: actual distance from start
        for (int[] row : dist) java.util.Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 1;

        // PQ entries: [f-cost, row, col] where f = g + h (Chebyshev heuristic)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{1 + chebyshev(0, 0, n), 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[1], c = cur[2];
            int g = dist[r][c];
            if (r == n - 1 && c == n - 1) return g; // reached target
            if (cur[0] > g + chebyshev(r, c, n)) continue; // stale entry, skip

            for (int[] d : DIRS) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (grid[nr][nc] != 0) continue;
                int newG = g + 1; // uniform cost per step
                if (newG < dist[nr][nc]) {
                    dist[nr][nc] = newG;
                    int f = newG + chebyshev(nr, nc, n); // f = g + h
                    pq.offer(new int[]{f, nr, nc});
                }
            }
        }
        return -1;
    }

    /** Chebyshev distance to (n-1, n-1) — admissible heuristic for 8-directional movement. */
    private static int chebyshev(int r, int c, int n) {
        return Math.max(n - 1 - r, n - 1 - c);
    }
}
