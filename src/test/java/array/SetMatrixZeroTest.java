package array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SetMatrixZeroTest {

    private final SetMatrixZero solution = new SetMatrixZero();

    @Test
    void testExample1() {
        int[][] matrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[][] expected = {
            {1, 0, 1},
            {0, 0, 0},
            {1, 0, 1}
        };
        solution.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testExample2() {
        int[][] matrix = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };
        int[][] expected = {
            {0, 0, 0, 0},
            {0, 4, 5, 0},
            {0, 3, 1, 0}
        };
        solution.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testNoZeroes() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        int[][] expected = {
            {1, 2},
            {3, 4}
        };
        solution.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testSingleElementZero() {
        int[][] matrix = {{0}};
        int[][] expected = {{0}};
        solution.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testFirstRowHasZero() {
        int[][] matrix = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 4, 5},
            {0, 7, 8}
        };
        solution.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testFirstColHasZero() {
        int[][] matrix = {
            {1, 2, 3},
            {0, 4, 5},
            {6, 7, 8}
        };
        int[][] expected = {
            {0, 2, 3},
            {0, 0, 0},
            {0, 7, 8}
        };
        solution.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }

    @Test
    void testNegativeValuesWithZeros() {
        int[][] matrix = {
            {-1, 0, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        int[][] expected = {
            {0, 0, 0},
            {4, 0, 0},
            {0, 0, 0}
        };
        solution.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }
}
