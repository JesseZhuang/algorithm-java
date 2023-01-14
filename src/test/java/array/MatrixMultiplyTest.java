package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MatrixMultiplyTest {

    @Test
    void multiply() {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{7, 8}, {9, 10}, {11, 12}};
        int[][] product = {{58, 64}, {139, 154}};
        assertArrayEquals(product, MatrixMultiply.multiply(a, b));
    }
}
