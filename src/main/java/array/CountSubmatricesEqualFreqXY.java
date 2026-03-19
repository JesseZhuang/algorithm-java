package array;

/**
 * LeetCode 3212. Medium.
 * <p>
 * Given a 2D char grid of 'X', 'Y', and '.', count submatrices with top-left at (0,0)
 * where the number of X equals the number of Y and that count is positive.
 * <p>
 * Column sums for X and Y; for each row, accumulate running sums over columns.
 * O(m * n) time, O(n) space.
 */
public class CountSubmatricesEqualFreqXY {

    // 18ms, 206.18mb
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        int[] colX = new int[n];
        int[] colY = new int[n];
        for (int i = 0; i < m; i++) {
            int rowX = 0;
            int rowY = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'X') {
                    colX[j]++;
                }
                if (grid[i][j] == 'Y') {
                    colY[j]++;
                }
                rowX += colX[j];
                rowY += colY[j];
                if (rowX == rowY && rowX > 0) {
                    res++;
                }
            }
        }
        return res;
    }
}
