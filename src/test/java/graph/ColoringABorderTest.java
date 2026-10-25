package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ColoringABorderTest {

    @Test
    void recolorsOnlyConnectedCellsInExample() {
        int[][] grid = {{1, 1}, {1, 2}};

        assertSame(grid, ColoringABorder.colorBorder(grid, 0, 0, 3));
        assertArrayEquals(new int[][]{{3, 3}, {3, 2}}, grid);
    }

    @Test
    void preservesInteriorOfSolidComponent() {
        int[][] grid = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        assertArrayEquals(new int[][]{
                {2, 2, 2},
                {2, 1, 2},
                {2, 2, 2}
        }, ColoringABorder.colorBorder(grid, 1, 1, 2));
    }

    @Test
    void doesNotChangeGridWhenColorIsOriginalColor() {
        int[][] grid = {
                {1, 1, 2},
                {1, 1, 2},
                {2, 2, 1}
        };
        int[][] expected = {
                {1, 1, 2},
                {1, 1, 2},
                {2, 2, 1}
        };

        assertSame(grid, ColoringABorder.colorBorder(grid, 0, 0, 1));
        assertArrayEquals(expected, grid);
    }

    @Test
    void leavesDisconnectedSameColorCellsUnchanged() {
        int[][] grid = {
                {1, 2, 1},
                {1, 2, 1},
                {2, 2, 1}
        };

        assertArrayEquals(new int[][]{
                {3, 2, 1},
                {3, 2, 1},
                {2, 2, 1}
        }, ColoringABorder.colorBorder(grid, 0, 0, 3));
    }

    @Test
    void recolorsSingleCell() {
        int[][] grid = {{4}};

        assertArrayEquals(new int[][]{{7}}, ColoringABorder.colorBorder(grid, 0, 0, 7));
    }

    @Test
    void treatsOneRowAsAllBorder() {
        int[][] grid = {{1, 1, 1, 1, 1}};

        assertArrayEquals(new int[][]{{2, 2, 2, 2, 2}},
                ColoringABorder.colorBorder(grid, 0, 2, 2));
    }

    @Test
    void treatsOneColumnAsAllBorder() {
        int[][] grid = {{1}, {1}, {1}, {1}, {1}};

        assertArrayEquals(new int[][]{{2}, {2}, {2}, {2}, {2}},
                ColoringABorder.colorBorder(grid, 2, 0, 2));
    }

    @Test
    void recolorsBordersTouchingAnInteriorDifferentColor() {
        int[][] grid = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };

        assertArrayEquals(new int[][]{
                {3, 3, 3, 3, 3},
                {3, 1, 3, 1, 3},
                {3, 3, 2, 3, 3},
                {3, 1, 3, 1, 3},
                {3, 3, 3, 3, 3}
        }, ColoringABorder.colorBorder(grid, 1, 1, 3));
    }

    @Test
    void handlesFiftyByFiftySolidGrid() {
        int[][] grid = new int[50][50];
        int[][] expected = new int[50][50];
        for (int row = 0; row < 50; row++) {
            for (int col = 0; col < 50; col++) {
                grid[row][col] = 1;
                expected[row][col] = row == 0 || row == 49 || col == 0 || col == 49 ? 3 : 1;
            }
        }

        assertArrayEquals(expected, ColoringABorder.colorBorder(grid, 25, 25, 3));
    }
}
