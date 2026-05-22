package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordSearchTest {

    private WordSearch sol;

    @BeforeEach
    void setUp() {
        sol = new WordSearch();
    }

    @Test
    void testExample1() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(sol.exist(board, "ABCCED"));
    }

    @Test
    void testExample2() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(sol.exist(board, "SEE"));
    }

    @Test
    void testExample3() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertFalse(sol.exist(board, "ABCB"));
    }

    @Test
    void testSingleCellTrue() {
        char[][] board = {{'A'}};
        assertTrue(sol.exist(board, "A"));
    }

    @Test
    void testSingleCellFalse() {
        char[][] board = {{'A'}};
        assertFalse(sol.exist(board, "B"));
    }

    @Test
    void testFullBoard() {
        char[][] board = {{'A', 'B'}, {'C', 'D'}};
        assertTrue(sol.exist(board, "ABDC"));
    }

    @Test
    void testNoMatchLongerWord() {
        char[][] board = {{'A', 'B'}, {'C', 'D'}};
        assertFalse(sol.exist(board, "ABCDE"));
    }
}
