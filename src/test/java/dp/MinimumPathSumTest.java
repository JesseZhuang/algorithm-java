package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumPathSumTest {
    MinimumPathSum tbt;

    @BeforeEach
    void setUp() {
        tbt = new MinimumPathSum();
    }

    @Test
    void testExample() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        assertEquals(7, tbt.minPathSumDP(grid));
    }

    @Test
    void testSingleCell() {
        int[][] grid = {{5}};
        assertEquals(5, tbt.minPathSumDP(grid));
    }

    @Test
    void testSingleRow() {
        int[][] grid = {{1, 2, 3}};
        assertEquals(6, tbt.minPathSumDP(grid));
    }

    @Test
    void testSingleColumn() {
        int[][] grid = {{1}, {2}, {3}};
        assertEquals(6, tbt.minPathSumDP(grid));
    }

    @Test
    void testTwoByTwo() {
        int[][] grid = {{1, 2}, {1, 1}};
        assertEquals(3, tbt.minPathSumDP(grid));
    }

    @Test
    void testAllZeros() {
        int[][] grid = {{0, 0}, {0, 0}};
        assertEquals(0, tbt.minPathSumDP(grid));
    }

    @Test
    void testLargeValues() {
        int[][] grid = {{100, 100, 100}, {100, 1, 100}, {100, 1, 1}};
        assertEquals(203, tbt.minPathSumDP(grid));
    }

    @Test
    void testPreferDown() {
        int[][] grid = {{1, 100}, {1, 1}};
        assertEquals(3, tbt.minPathSumDP(grid));
    }
}
