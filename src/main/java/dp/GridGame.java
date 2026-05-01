package dp;

/**
 * <a href="https://leetcode.com/problems/grid-game/">LeetCode 2017</a>, medium,
 * tags: array, prefix sum, dynamic programming, matrix.
 */
public final class GridGame {
    private GridGame() {}

    /**
     * Robot1 minimizes what robot2 can collect. Robot2 can only collect from the untouched
     * top-right suffix or bottom-left prefix.
     * Time O(n), Space O(1).
     */
    public static long gridGame(int[][] grid) {
        int n = grid[0].length;
        long first = 0;
        for (int v : grid[0]) first += v;
        long second = 0, res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            first -= grid[0][i];
            res = Math.min(res, Math.max(first, second));
            second += grid[1][i];
        }
        return res;
    }
}
