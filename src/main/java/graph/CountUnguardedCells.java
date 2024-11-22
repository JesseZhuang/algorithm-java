package graph;

/**
 * LeetCode 2257, medium.
 * let g = number of guards
 */
@SuppressWarnings("unused")
public class CountUnguardedCells {
    // mn, mn. 26ms, 78.95Mb.
    static class Solution {
        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            char[][] grid = new char[m][n];
            int res = m * n - guards.length - walls.length;
            for (int[] w : walls) grid[w[0]][w[1]] = 'W';
            for (int[] g : guards) grid[g[0]][g[1]] = 'G';
            for (int[] g : guards) {
                for (int[] d : dirs) {
                    int nx = g[0] + d[0], ny = g[1] + d[1];
                    while (!(nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == 'G' || grid[nx][ny] == 'W')) {
                        if (grid[nx][ny] != 'P') res--;
                        grid[nx][ny] = 'P';
                        nx += d[0];
                        ny += d[1];
                    }
                }
            }
            return res;
        }
    }

    // O(mn+g(m+n)), O(mn). 26ms, 67.25Mb.
    static class Solution2 {
        static final int UNGUARDED = 0, GUARDED = 1, GUARD = 2, WALL = 3;
        static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] grid; // probabl cannot trust leetcode memory profiler, char 78.7Mb, int 66.8Mb.

        void markGuarded(int r, int c) {
            int m = grid.length, n = grid[0].length;
            for (int[] d : dirs) {
                int nx = r + d[0], ny = c + d[1];
                while (!(nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == GUARD || grid[nx][ny] == WALL)) {
                    grid[nx][ny] = GUARDED;
                    nx += d[0];
                    ny += d[1];
                }
            }
        }

        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            grid = new int[m][n];
            for (int[] guard : guards) grid[guard[0]][guard[1]] = GUARD;
            for (int[] wall : walls) grid[wall[0]][wall[1]] = WALL;
            for (int[] g : guards) markGuarded(g[0], g[1]);
            int res = 0;
            for (int[] row : grid)
                for (int cell : row) if (cell == UNGUARDED) res++;
            return res;
        }
    }
}
