package graph;

/**
 * LeetCode 1975, medium.
 * <p>
 * n == matrix.length == matrix[i].length
 */
@SuppressWarnings("unused")
public class MaxMatrixSum {
    // n^2, 1. todo, editoral,
    static class Solution {
        public long maxMatrixSum(int[][] matrix) {
            long res = 0;
            int min = Integer.MAX_VALUE;
            int negCnt = 0;
            for (int[] row : matrix) {
                for (int n : row) {
                    res += Math.abs(n);
                    if (n < 0) negCnt++;
                    min = Math.min(min, Math.abs(n));
                }
            }
            // Adjust if the count of negative numbers is odd
            if ((negCnt & 1) != 0) res -= 2L * min;
            return res;
        }
    }
}
