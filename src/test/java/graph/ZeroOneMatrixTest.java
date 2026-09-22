package graph;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ZeroOneMatrixTest {
    private static ZeroOneMatrix.Solution s1;
    private static ZeroOneMatrix.Solution2 s2;

    @BeforeAll
    static void setup() {
        s1 = new ZeroOneMatrix.Solution();
        s2 = new ZeroOneMatrix.Solution2();
    }

    @Test
    void testExample1() {
        int[][] expected = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertArrayEquals(expected, s1.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        assertArrayEquals(expected, s2.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    @Test
    void testExample2() {
        int[][] expected = {{0, 0, 0}, {0, 1, 0}, {1, 2, 1}};
        assertArrayEquals(expected, s1.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}));
        assertArrayEquals(expected, s2.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}));
    }
}
