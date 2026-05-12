package array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RotateImageTest {
    @Test
    void testTransposeReflect() {
        RotateImage ri = new RotateImage();
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ri.rotate2(m1);
        assertArrayEquals(new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}, m1);

        int[][] m2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        ri.rotate2(m2);
        assertArrayEquals(new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}}, m2);

        int[][] m3 = {{1}};
        ri.rotate2(m3);
        assertArrayEquals(new int[][]{{1}}, m3);

        int[][] m4 = {{1, 2}, {3, 4}};
        ri.rotate2(m4);
        assertArrayEquals(new int[][]{{3, 1}, {4, 2}}, m4);
    }

    @Test
    void testFourWaySwap() {
        RotateImage ri = new RotateImage();
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ri.rotate1(m1);
        assertArrayEquals(new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}, m1);

        int[][] m2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        ri.rotate1(m2);
        assertArrayEquals(new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}}, m2);

        int[][] m3 = {{1}};
        ri.rotate1(m3);
        assertArrayEquals(new int[][]{{1}}, m3);

        int[][] m4 = {{1, 2}, {3, 4}};
        ri.rotate1(m4);
        assertArrayEquals(new int[][]{{3, 1}, {4, 2}}, m4);
    }

    @Test
    void test5x5() {
        RotateImage ri = new RotateImage();
        int[][] m = {
            {1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}
        };
        ri.rotate2(m);
        assertArrayEquals(new int[][]{
            {21, 16, 11, 6, 1}, {22, 17, 12, 7, 2}, {23, 18, 13, 8, 3},
            {24, 19, 14, 9, 4}, {25, 20, 15, 10, 5}
        }, m);
    }
}
