package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LargestSubmatrixWithRearrangementsTest {
    private final LargestSubmatrixWithRearrangements solver = new LargestSubmatrixWithRearrangements();

    @Test
    void testExample1() {
        int[][] matrix = {
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 1}
        };
        assertBoth(4, matrix);
    }

    @Test
    void testExample2() {
        int[][] matrix = {
                {1, 0, 1, 0, 1}
        };
        assertBoth(3, matrix);
    }

    @Test
    void testAllZeros() {
        int[][] matrix = {
                {0, 0},
                {0, 0}
        };
        assertBoth(0, matrix);
    }

    @Test
    void testAllOnes() {
        int[][] matrix = {
                {1, 1},
                {1, 1},
                {1, 1}
        };
        assertBoth(6, matrix);
    }

    @Test
    void testSingleColumn() {
        int[][] matrix = {
                {1},
                {1},
                {0},
                {1}
        };
        assertBoth(2, matrix);
    }

    @Test
    void testNonSquare() {
        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 1, 0}
        };
        // best after rearrangement: heights after row 2 are [2,1,2,0] -> sort -> [0,1,2,2], max area 2*2=4
        assertBoth(4, matrix);
    }

    private void assertBoth(int expected, int[][] matrix) {
        assertEquals(expected, solver.largestSubmatrixSort(copy(matrix)));
        assertEquals(expected, solver.largestSubmatrixCounting(copy(matrix)));
    }

    private int[][] copy(int[][] matrix) {
        int[][] out = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            out[i] = matrix[i].clone();
        }
        return out;
    }
}
