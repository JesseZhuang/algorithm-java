package graph;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ZeroOneMatrixTest {
    private static ZeroOneMatrix.Solution tbt;

    @BeforeAll
    static void setup() {
        tbt = new ZeroOneMatrix.Solution();
    }

    @Test
    void testExample1() {
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] expected = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertArrayEquals(expected, tbt.updateMatrix(mat));
    }

    @Test
    void testExample2() {
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] expected = {{0, 0, 0}, {0, 1, 0}, {1, 2, 1}};
        assertArrayEquals(expected, tbt.updateMatrix(mat));
    }
}
