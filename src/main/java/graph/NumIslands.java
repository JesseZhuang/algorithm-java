package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 200, medium, tags: array, dfs, bfs, union find, matrix.
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
public class NumIslands {
    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m;
    int n;

    char[][] grid;

    // One idea is to modify '1' to '0' or some other char, modify input to reduce space.
    // O(mn) time and space. 13ms, 57.3 Mb.
    public int numIslandsDFS(char[][] grid) {// note char, not int array
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++) {
                if (!visited[r][c] && grid[r][c] == '1') { // must check before dfs
                    dfs(visited, r, c);
                    count++; // must be inside if
                }
            }
        return count;
    }

    // visited not necessary if modify input
    private void dfs(boolean[][] visited, int r, int c) {
        visited[r][c] = true;
        for (int[] dir : dirs) {
            int nr = r + dir[0], nc = c + dir[1];
            if (inRange(nr, nc) && !visited[nr][nc] && grid[nr][nc] == '1') // don't forget check char
                dfs(visited, nr, nc);
        }
    }

    private boolean inRange(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n; // cannot equal to row or col
    }

    // O(mn) time, O(1) space (trick: modify input, worst case add 4 vertexes to q). 13ms, 57.3Mb.
    public int numIslandsBFS(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        int[] offsets = {0, 1, 0, -1, 0};
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != '1') continue;
                count++;
                Queue<List<Integer>> q = new ArrayDeque<>();
                q.add(Arrays.asList(r, c));
                while (!q.isEmpty()) {
                    List<Integer> pair = q.remove();
                    int cr = pair.get(0), cc = pair.get(1);
                    for (int i = 0; i < 4; i++) {
                        int nr = cr + offsets[i], nc = cc + offsets[i + 1];
                        if (inRange(nr, nc) && grid[nr][nc] == '1') {
                            grid[nr][nc] = '0'; // must be modified immediately, otherwise maybe added again
                            q.add(Arrays.asList(nr, nc));
                        }
                    }
                }
            }
        return count;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        NumIslands t = new NumIslands();

        t.numIslandsBFS(grid);
    }
}
