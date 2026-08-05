package graph;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 778. Hard. Tags: Graph, BFS, Binary Search, Heap.
 * <p>
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point.
 * Starting at top-left, find the minimum time t such that you can reach bottom-right, where at time t you can
 * swim to any adjacent cell with elevation <= t.
 */
public final class SwimInRisingWater {

    private SwimInRisingWater() {
    }

    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * Solution 1: Min-Heap (Dijkstra-like).
     * Use a priority queue ordered by the maximum elevation along the path.
     * Time O(n^2 log n), Space O(n^2).
     */
    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        // PQ stores {maxElevation, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // O(n^2) elements max
        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); // O(log(n^2)) = O(log n) per poll
            int t = cur[0], r = cur[1], c = cur[2];
            if (r == n - 1 && c == n - 1) return t;
            for (int[] d : DIRS) { // O(4) neighbors
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.offer(new int[]{Math.max(t, grid[nr][nc]), nr, nc});
                }
            }
        }
        return -1; // unreachable
    }

    /**
     * Solution 2: Binary Search + BFS.
     * Binary search on the answer t. For each candidate, BFS to check reachability using only cells <= t.
     * Time O(n^2 log n), Space O(n^2).
     */
    public static int swimInWater2(int[][] grid) {
        int n = grid.length;
        int lo = Math.max(grid[0][0], grid[n - 1][n - 1]);
        int hi = n * n - 1;

        while (lo < hi) { // O(log(n^2)) = O(log n) iterations
            int mid = lo + (hi - lo) / 2;
            if (canReach(grid, n, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /** BFS checking if we can reach (n-1, n-1) from (0,0) using only cells with elevation <= t. O(n^2). */
    private static boolean canReach(int[][] grid, int n, int t) {
        if (grid[0][0] > t) return false;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            if (r == n - 1 && c == n - 1) return true;
            for (int[] d : DIRS) { // O(4) neighbors
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] <= t) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}
