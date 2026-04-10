package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfIslandsTest {

    private char[][] grid;

    @BeforeEach
    void setUp() {
        grid = null;
    }

    @Test
    void example1_singleIsland() {
        grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, NumberOfIslands.numIslandsDFS(grid));
        assertEquals(1, NumberOfIslands.numIslandsBFS(grid));
    }

    @Test
    void example2_threeIslands() {
        grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, NumberOfIslands.numIslandsDFS(grid));
        assertEquals(3, NumberOfIslands.numIslandsBFS(grid));
    }

    @Test
    void singleCellLand() {
        grid = new char[][]{{'1'}};
        assertEquals(1, NumberOfIslands.numIslandsDFS(grid));
        assertEquals(1, NumberOfIslands.numIslandsBFS(grid));
    }

    @Test
    void singleCellWater() {
        grid = new char[][]{{'0'}};
        assertEquals(0, NumberOfIslands.numIslandsDFS(grid));
        assertEquals(0, NumberOfIslands.numIslandsBFS(grid));
    }

    @Test
    void allWater() {
        grid = new char[][]{
                {'0', '0'},
                {'0', '0'}
        };
        assertEquals(0, NumberOfIslands.numIslandsDFS(grid));
        assertEquals(0, NumberOfIslands.numIslandsBFS(grid));
    }

    @Test
    void allLand() {
        grid = new char[][]{
                {'1', '1'},
                {'1', '1'}
        };
        assertEquals(1, NumberOfIslands.numIslandsDFS(grid));
        assertEquals(1, NumberOfIslands.numIslandsBFS(grid));
    }

    @Test
    void diagonalNotConnected() {
        grid = new char[][]{
                {'1', '0'},
                {'0', '1'}
        };
        assertEquals(2, NumberOfIslands.numIslandsDFS(grid));
        assertEquals(2, NumberOfIslands.numIslandsBFS(grid));
    }
}
