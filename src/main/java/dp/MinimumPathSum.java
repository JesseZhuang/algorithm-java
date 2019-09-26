package dp;

/**
 * LeetCode 64. Medium. Tags: Array, Dynamic Programming.
 * <p>
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
 * the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * <pre>
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * </pre>
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * <p>
 * <ul>
 * <li>AcyclicSP, topological sort, O(E+V) time, O(V) space.
 * <li>Dijkstra, O(ElogV) time, O(V) space.
 * <li>DP, O(EV) time, O(EV) space.
 * <li>A*, ?
 * <li>Bellman-Ford O(EV), O(V) space.
 * <li>Bellman-Ford queue based, O(EV) time, O(V) space.
 * </ul>
 */
public class MinimumPathSum {
    public int minPathSumDP(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 1; i < c; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < r; i++) {
            grid[i][0] += grid[i - 1][0];
            for (int j = 1; j < c; j++) {
                grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[r - 1][c - 1];
    }
}
