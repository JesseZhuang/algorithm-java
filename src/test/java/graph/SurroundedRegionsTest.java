package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SurroundedRegionsTest {

    private final SurroundedRegions sol = new SurroundedRegions();

    @Test
    void testExample1() {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        char[][] expected = {
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        sol.solveDfs(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testSingleCellX() {
        char[][] board = {{'X'}};
        char[][] expected = {{'X'}};
        sol.solveDfs(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testAllOBorder2x2() {
        char[][] board = {
                {'O', 'O'},
                {'O', 'O'}
        };
        char[][] expected = {
                {'O', 'O'},
                {'O', 'O'}
        };
        sol.solveDfs(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testInnerOSurrounded3x3() {
        char[][] board = {
                {'X', 'X', 'X'},
                {'X', 'O', 'X'},
                {'X', 'X', 'X'}
        };
        char[][] expected = {
                {'X', 'X', 'X'},
                {'X', 'X', 'X'},
                {'X', 'X', 'X'}
        };
        sol.solveDfs(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testSingleRow() {
        char[][] board = {{'X', 'O', 'X', 'O', 'X'}};
        char[][] expected = {{'X', 'O', 'X', 'O', 'X'}};
        sol.solveDfs(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testConnectedToBorderNotFlipped() {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'X', 'O'}
        };
        // bottom-right O is on border, but inner O's are not connected to it -> flipped
        char[][] expected = {
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O'}
        };
        sol.solveDfs(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testUFExample1() {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        char[][] expected = {
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        sol.solveUF(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testUFInnerSurrounded() {
        char[][] board = {
                {'X', 'X', 'X'},
                {'X', 'O', 'X'},
                {'X', 'X', 'X'}
        };
        char[][] expected = {
                {'X', 'X', 'X'},
                {'X', 'X', 'X'},
                {'X', 'X', 'X'}
        };
        sol.solveUF(board);
        assertArrayEquals(expected, board);
    }

    @Test
    void testUFAllBorder() {
        char[][] board = {{'O', 'O'}, {'O', 'O'}};
        char[][] expected = {{'O', 'O'}, {'O', 'O'}};
        sol.solveUF(board);
        assertArrayEquals(expected, board);
    }
}
