package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxAreaOfIslandTest {

    @Test
    void example1() {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        assertEquals(6, MaxAreaOfIsland.maxAreaDFS(grid));
    }

    @Test
    void example1_bfs() {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        assertEquals(6, MaxAreaOfIsland.maxAreaBFS(grid));
    }

    @Test
    void allWater() {
        int[][] grid = {{0, 0, 0, 0, 0, 0, 0, 0}};
        assertEquals(0, MaxAreaOfIsland.maxAreaDFS(grid));
        int[][] grid2 = {{0, 0, 0, 0, 0, 0, 0, 0}};
        assertEquals(0, MaxAreaOfIsland.maxAreaBFS(grid2));
    }

    @Test
    void singleCellIsland() {
        int[][] grid = {{1}};
        assertEquals(1, MaxAreaOfIsland.maxAreaDFS(grid));
        int[][] grid2 = {{1}};
        assertEquals(1, MaxAreaOfIsland.maxAreaBFS(grid2));
    }

    @Test
    void allLand() {
        int[][] grid = {{1, 1}, {1, 1}};
        assertEquals(4, MaxAreaOfIsland.maxAreaDFS(grid));
        int[][] grid2 = {{1, 1}, {1, 1}};
        assertEquals(4, MaxAreaOfIsland.maxAreaBFS(grid2));
    }

    @Test
    void diagonalNotConnected() {
        int[][] grid = {{1, 0}, {0, 1}};
        assertEquals(1, MaxAreaOfIsland.maxAreaDFS(grid));
        int[][] grid2 = {{1, 0}, {0, 1}};
        assertEquals(1, MaxAreaOfIsland.maxAreaBFS(grid2));
    }

    @Test
    void multipleIslands() {
        int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        assertEquals(4, MaxAreaOfIsland.maxAreaDFS(grid));
        int[][] grid2 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        assertEquals(4, MaxAreaOfIsland.maxAreaBFS(grid2));
    }
}
