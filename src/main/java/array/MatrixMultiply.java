package array;

/**
 * Why the dot product is done this way?
 * https://www.mathsisfun.com/algebra/matrix-multiplying.html
 */
public class MatrixMultiply {
    public static int[][] multiply(int[][] a, int[][] b) {
        int r1 = a.length, c1 = a[0].length, r2 = b.length, c2 = b[0].length;
        if (c1 != r2) throw new IllegalArgumentException("matrix 1 col must equal to matrix 2 row");
        int[][] res = new int[r1][c2];
        for (int r = 0; r < r1; r++) // row
            for (int c = 0; c < c2; c++) // col
                for (int k = 0; k < c1; k++) // ele
                    res[r][c] += a[r][k] * b[k][c];
        return res;
    }
}
