package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximalSquareTest {

    @Test
    void testMaximalSquare() {
        assertEquals(4, MaximalSquare.maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));
        assertEquals(1, MaximalSquare.maximalSquare(new char[][]{
                {'0', '1'},
                {'1', '0'}
        }));
        assertEquals(0, MaximalSquare.maximalSquare(new char[][]{
                {'0'}
        }));
        assertEquals(1, MaximalSquare.maximalSquare(new char[][]{
                {'1'}
        }));
        assertEquals(4, MaximalSquare.maximalSquare(new char[][]{
                {'1', '1'},
                {'1', '1'}
        }));
    }

    @Test
    void testEmptyMatrix() {
        assertEquals(0, MaximalSquare.maximalSquare(new char[][]{}));
        assertEquals(0, MaximalSquare.maximalSquare(null));
    }
}
