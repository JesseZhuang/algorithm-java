package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class GameOfLifeTest {

    GameOfLife.Solution solution = new GameOfLife.Solution();

    @Test
    void testExample1() {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        int[][] expected = {{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testExample2() {
        int[][] board = {{1, 1}, {1, 0}};
        int[][] expected = {{1, 1}, {1, 1}};
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testSingleCellAlive() {
        int[][] board = {{1}};
        int[][] expected = {{0}};
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testSingleCellDead() {
        int[][] board = {{0}};
        int[][] expected = {{0}};
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testAllAlive2x2() {
        int[][] board = {{1, 1}, {1, 1}};
        int[][] expected = {{1, 1}, {1, 1}};
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testBlinker() {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        solution.gameOfLife(board);
        assertArrayEquals(expected, board);
    }
}
