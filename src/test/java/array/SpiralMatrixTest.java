package array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpiralMatrixTest {

    private final SpiralMatrix tbt = new SpiralMatrix();

    @Test
    void test3x3() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5), tbt.spiralOrder(matrix));
    }

    @Test
    void test3x4() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7), tbt.spiralOrder(matrix));
    }

    @Test
    void testSingleRow() {
        int[][] matrix = {{1, 2, 3, 4}};
        assertEquals(List.of(1, 2, 3, 4), tbt.spiralOrder(matrix));
    }

    @Test
    void testSingleColumn() {
        int[][] matrix = {{1}, {2}, {3}};
        assertEquals(List.of(1, 2, 3), tbt.spiralOrder(matrix));
    }

    @Test
    void testSingleElement() {
        int[][] matrix = {{7}};
        assertEquals(List.of(7), tbt.spiralOrder(matrix));
    }

    @Test
    void test2x2() {
        int[][] matrix = {{1, 2}, {3, 4}};
        assertEquals(List.of(1, 2, 4, 3), tbt.spiralOrder(matrix));
    }

    @Test
    void test4x3() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        assertEquals(List.of(1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8), tbt.spiralOrder(matrix));
    }
}
