package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/** LeetCode 1034 - Coloring A Border. */
public final class ColoringABorder {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private ColoringABorder() {
    }

    /**
     * Finds the four-directionally connected component using BFS, then recolors its border.
     * Keeps the original grid intact until border detection is complete.
     *
     * Time: O(m * n); space: O(m * n) for visited cells, the queue, and border cells.
     * Mutates and returns {@code grid}, where m and n are its row and column counts.
     */
    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int originalColor = grid[row][col];
        if (originalColor == color) return grid;

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> borders = new ArrayList<>();
        visited[row][col] = true;
        queue.offer(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            boolean isBorder = false;
            for (int[] direction : DIRECTIONS) {
                int nextRow = cell[0] + direction[0];
                int nextCol = cell[1] + direction[1];
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols
                        || grid[nextRow][nextCol] != originalColor) {
                    isBorder = true;
                } else if (!visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
            if (isBorder) borders.add(cell);
        }

        for (int[] cell : borders) {
            grid[cell[0]][cell[1]] = color;
        }
        return grid;
    }
}
