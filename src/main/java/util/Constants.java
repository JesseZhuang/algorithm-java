package util;

public class Constants {
    public static final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static final int I32_MAX = Integer.MAX_VALUE;

    /**
     * whether cell i,j is inside of matrix[r][c].
     *
     * @param i, row index
     * @param j, column index
     * @param r, number of rows
     * @param c, number of columns
     * @return whether cell is inside of matrix
     */
    public static boolean inside(int i, int j, int r, int c) {
        return i >= 0 && i <= r - 1 && j >= 0 && j <= c - 1;
    }
}
