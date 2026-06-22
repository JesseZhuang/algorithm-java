package graph;

import java.util.*;

/**
 * LeetCode 1631 Medium. Tags: Array, Binary Search, Depth-First Search, Breadth-First Search,
 * Union Find, Heap (Priority Queue), Matrix.
 * <p>
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size
 * rows x cols, where heights[row][col] represents the height of cell (row, col). You are
 * situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell,
 * (rows-1, cols-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish
 * to find a route that requires the minimum effort.
 * <p>
 * A route's effort is the maximum absolute difference in heights between two consecutive
 * cells of the route.
 * <p>
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 * <p>
 * Example 1:
 * <p>
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * <p>
 * Example 2:
 * <p>
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells,
 * which is better than route [1,3,5,3,5].
 * <p>
 * Example 3:
 * <p>
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 * <p>
 * Constraints:
 * <p>
 * rows == heights.length
 * cols == heights[i].length
 * 1 <= rows, cols <= 100
 * 1 <= heights[i][j] <= 10^6
 */
@SuppressWarnings("unused")
public final class PathWithMinimumEffort {

    private PathWithMinimumEffort() {
    }

    /**
     * Solution 1: Dijkstra's algorithm using min-heap.
     * <p>
     * Use a priority queue to explore cells in order of minimum effort.
     * For each cell, track the minimum effort needed to reach it.
     * <p>
     * Time: O(m*n*log(m*n)) where m=rows, n=cols
     * Space: O(m*n) for the distance array and priority queue
     */
    public static int minimumEffortPathDijkstra(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // Special case: single cell
        if (rows == 1 && cols == 1) {
            return 0;
        }

        // Distance array to track minimum effort to reach each cell
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        // Min-heap: (effort, row, col)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 0});

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int effort = curr[0];
            int row = curr[1];
            int col = curr[2];

            // If we reached the destination
            if (row == rows - 1 && col == cols - 1) {
                return effort;
            }

            // Skip if we've already found a better path to this cell
            if (effort > dist[row][col]) {
                continue;
            }

            // Explore neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }

                // Calculate new effort: max of current effort and edge weight
                int edgeWeight = Math.abs(heights[newRow][newCol] - heights[row][col]);
                int newEffort = Math.max(effort, edgeWeight);

                // If we found a better path to the neighbor
                if (newEffort < dist[newRow][newCol]) {
                    dist[newRow][newCol] = newEffort;
                    pq.offer(new int[]{newEffort, newRow, newCol});
                }
            }
        }

        return dist[rows - 1][cols - 1];
    }

    /**
     * Solution 2: Binary search on the answer + BFS.
     * <p>
     * Binary search on the effort value [0, 10^6].
     * For each mid value, use BFS to check if we can reach destination
     * with all edges having difference <= mid.
     * <p>
     * Time: O(m*n*log(max_height)) where m=rows, n=cols
     * Space: O(m*n) for the visited array and queue
     */
    public static int minimumEffortPathBinarySearch(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // Special case: single cell
        if (rows == 1 && cols == 1) {
            return 0;
        }

        int left = 0;
        int right = 1_000_000; // Max height difference
        int result = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canReachWithEffort(heights, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    /**
     * Helper method: Check if we can reach destination with max effort <= maxEffort.
     * Uses BFS to explore all reachable cells.
     */
    private static boolean canReachWithEffort(int[][] heights, int maxEffort) {
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            // If we reached the destination
            if (row == rows - 1 && col == cols - 1) {
                return true;
            }

            // Explore neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds and visited
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || visited[newRow][newCol]) {
                    continue;
                }

                // Check if edge weight is within maxEffort
                int edgeWeight = Math.abs(heights[newRow][newCol] - heights[row][col]);
                if (edgeWeight <= maxEffort) {
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return false;
    }
}
