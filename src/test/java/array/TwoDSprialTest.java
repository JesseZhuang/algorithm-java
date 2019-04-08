package array;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TwoDSprialTest {

    private static TwoDSprial tbt;

    @BeforeAll
    static void setup() {
        tbt = new TwoDSprial();
    }

    @Test
    void testOneToFourIterative() {
        assertArrayEquals(new int[][]{{1}}, tbt.spiralIterative(1));
        assertArrayEquals(new int[][]{{1, 2}, {4, 3}}, tbt.spiralIterative(2));
        assertArrayEquals(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}, tbt.spiralIterative(3));
        assertArrayEquals(new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}}, tbt.spiralIterative(4));
        assertArrayEquals(new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8},
                {28, 29, 30, 31, 32, 33, 34, 9},
                {27, 48, 49, 50, 51, 52, 35, 10},
                {26, 47, 60, 61, 62, 53, 36, 11},
                {25, 46, 59, 64, 63, 54, 37, 12},
                {24, 45, 58, 57, 56, 55, 38, 13},
                {23, 44, 43, 42, 41, 40, 39, 14},
                {22, 21, 20, 19, 18, 17, 16, 15}
        }, tbt.spiralIterative(8));
    }

    @Test
    void testOneToFourRecursive() {
        assertArrayEquals(new int[][]{{1}}, tbt.spiralRecursive(1));
        assertArrayEquals(new int[][]{{1, 2}, {4, 3}}, tbt.spiralRecursive(2));
        assertArrayEquals(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}, tbt.spiralRecursive(3));
        assertArrayEquals(new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}}, tbt.spiralRecursive(4));
        assertArrayEquals(new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8},
                {28, 29, 30, 31, 32, 33, 34, 9},
                {27, 48, 49, 50, 51, 52, 35, 10},
                {26, 47, 60, 61, 62, 53, 36, 11},
                {25, 46, 59, 64, 63, 54, 37, 12},
                {24, 45, 58, 57, 56, 55, 38, 13},
                {23, 44, 43, 42, 41, 40, 39, 14},
                {22, 21, 20, 19, 18, 17, 16, 15}
        }, tbt.spiralRecursive(8));
    }
}
