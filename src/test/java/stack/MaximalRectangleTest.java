package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximalRectangleTest {

    // LeetCode example: answer = 6
    private static final char[][] EXAMPLE = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
    };

    // --- Histogram + monotonic stack approach ---

    @Test
    void testStackLeetCodeExample() {
        assertEquals(6, MaximalRectangle.maximalRectangle(EXAMPLE));
    }

    @Test
    void testStackEmptyMatrix() {
        assertEquals(0, MaximalRectangle.maximalRectangle(new char[][]{}));
    }

    @Test
    void testStackSingleZero() {
        assertEquals(0, MaximalRectangle.maximalRectangle(new char[][]{{'0'}}));
    }

    @Test
    void testStackSingleOne() {
        assertEquals(1, MaximalRectangle.maximalRectangle(new char[][]{{'1'}}));
    }

    @Test
    void testStackAllOnes() {
        char[][] matrix = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        assertEquals(9, MaximalRectangle.maximalRectangle(matrix));
    }

    @Test
    void testStackAllZeros() {
        char[][] matrix = {
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        assertEquals(0, MaximalRectangle.maximalRectangle(matrix));
    }

    @Test
    void testStackSingleRow() {
        assertEquals(3, MaximalRectangle.maximalRectangle(new char[][]{{'1', '1', '1', '0', '1'}}));
    }

    @Test
    void testStackSingleColumn() {
        char[][] matrix = {{'1'}, {'1'}, {'0'}, {'1'}, {'1'}, {'1'}};
        assertEquals(3, MaximalRectangle.maximalRectangle(matrix));
    }

    @Test
    void testStackWideRectangle() {
        char[][] matrix = {
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1'}
        };
        assertEquals(10, MaximalRectangle.maximalRectangle(matrix));
    }

    // --- DP approach ---

    @Test
    void testDPLeetCodeExample() {
        assertEquals(6, MaximalRectangle.maximalRectangleDP(EXAMPLE));
    }

    @Test
    void testDPEmptyMatrix() {
        assertEquals(0, MaximalRectangle.maximalRectangleDP(new char[][]{}));
    }

    @Test
    void testDPSingleZero() {
        assertEquals(0, MaximalRectangle.maximalRectangleDP(new char[][]{{'0'}}));
    }

    @Test
    void testDPSingleOne() {
        assertEquals(1, MaximalRectangle.maximalRectangleDP(new char[][]{{'1'}}));
    }

    @Test
    void testDPAllOnes() {
        char[][] matrix = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };
        assertEquals(9, MaximalRectangle.maximalRectangleDP(matrix));
    }

    @Test
    void testDPAllZeros() {
        char[][] matrix = {
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        assertEquals(0, MaximalRectangle.maximalRectangleDP(matrix));
    }

    @Test
    void testDPSingleRow() {
        assertEquals(3, MaximalRectangle.maximalRectangleDP(new char[][]{{'1', '1', '1', '0', '1'}}));
    }

    @Test
    void testDPSingleColumn() {
        char[][] matrix = {{'1'}, {'1'}, {'0'}, {'1'}, {'1'}, {'1'}};
        assertEquals(3, MaximalRectangle.maximalRectangleDP(matrix));
    }

    @Test
    void testDPWideRectangle() {
        char[][] matrix = {
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1'}
        };
        assertEquals(10, MaximalRectangle.maximalRectangleDP(matrix));
    }
}
