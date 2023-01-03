package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 417, medium, tags: array, dfs, bfs, matrix.
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean
 * touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * <p>
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where
 * heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * <p>
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east,
 * and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from
 * any cell adjacent to an ocean into the ocean.
 * <p>
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from
 * cell (ri, ci) to both the Pacific and Atlantic oceans.
 * <p>
 * Example 1:
 * <p>
 * https://assets.leetcode.com/uploads/2021/06/08/waterflow-grid.jpg
 * <p>
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 * [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 * [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 * [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 * [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 * [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 * [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 * [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 * <p>
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificWaterFlow {
    boolean[][] visitedP;
    boolean[][] visitedA;
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    List<List<Integer>> res;
    Queue<List<Integer>> q;

    // 3ms 44.3 Mb. O(mn) time and space (worst case all cells reachable)
    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        visitedP = new boolean[m][n];
        visitedA = new boolean[m][n];
        res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            dfs(heights, visitedP, i, 0, 0);
            dfs(heights, visitedA, i, n - 1, 0);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights, visitedA, m - 1, i, 0);
            dfs(heights, visitedP, 0, i, 0);
        }
        return res;
    }

    private void dfs(int[][] heights, boolean[][] visited, int r, int c, int height) {
        int m = heights.length, n = heights[0].length;
        // note test range first, do not forget visited[][]
        if (!inRange(r, c, m, n) || visited[r][c] || heights[r][c] < height) return;
        visited[r][c] = true;
        if (visitedA[r][c] && visitedP[r][c]) res.add(Arrays.asList(r, c));
        for (int[] delta : deltas) dfs(heights, visited, r + delta[0], c + delta[1], heights[r][c]);
    }

    private boolean inRange(int r, int c, int row, int col) {
        return r >= 0 && r < row && c >= 0 && c < col; // cannot equal to row or col
    }

    // 10ms, 55.2Mb.
    public List<List<Integer>> pacificAtlanticBFS(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        visitedP = new boolean[m][n];
        visitedA = new boolean[m][n];
        res = new ArrayList<>();
        q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            bfs(heights, visitedP, i, 0);
            bfs(heights, visitedA, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            bfs(heights, visitedA, m - 1, i);
            bfs(heights, visitedP, 0, i);
        }
        return res;
    }

    private void bfs(int[][] heights, boolean[][] visited, int r, int c) {
        int m = heights.length, n = heights[0].length;
        q.add(Arrays.asList(r, c));
        while (!q.isEmpty()) {
            List<Integer> pair = q.remove();
            int cr = pair.get(0), cc = pair.get(1); // careful bug, should not look at r, c in while loop
            if (visited[cr][cc]) continue;
            visited[cr][cc] = true;
            if (visitedA[cr][cc] && visitedP[cr][cc]) res.add(Arrays.asList(cr, cc));
            for (int[] delta : deltas) {
                int nr = cr + delta[0], nc = cc + delta[1];
                if (inRange(nr, nc, m, n) && heights[nr][nc] >= heights[cr][cc] && !visited[nr][nc])
                    q.add(Arrays.asList(nr, nc));
            }
        }
    }
}
