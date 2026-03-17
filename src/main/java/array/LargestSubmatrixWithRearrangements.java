package array;

import java.util.Arrays;

/**
 * LeetCode 1727, medium, tags: array, sorting, matrix.
 * <p>
 * You are given a binary matrix. You can rearrange the columns in each row independently.
 * Return the area of the largest submatrix with all 1's after rearrangements.
 */
public class LargestSubmatrixWithRearrangements {
    // Solution A: sort heights per row. O(r * c log c) time, O(c) extra space.
    // 13ms, 114.70mb
    public int largestSubmatrixSort(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[] heights = new int[c];
        int[] sorted = new int[c];
        int best = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 1) heights[j] += 1;
                else heights[j] = 0;
            }
            System.arraycopy(heights, 0, sorted, 0, c);
            Arrays.sort(sorted); // ascending
            for (int j = 0; j < c; j++) {
                int h = sorted[j];
                if (h == 0) continue;
                int width = c - j;
                best = Math.max(best, h * width);
            }
        }
        return best;
    }

    // Solution B: maintain sorted heights incrementally. O(r * c) time, O(c) extra space.
    // 7ms, 112.01mb
    public int largestSubmatrixCounting(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[] prevHeights = new int[c];
        int[] prevCols = new int[c];
        int[] curHeights = new int[c];
        int[] curCols = new int[c];
        boolean[] seen = new boolean[c];
        int prevLen = 0;
        int best = 0;
        for (int i = 0; i < r; i++) {
            Arrays.fill(seen, false);
            int curLen = 0;
            for (int k = 0; k < prevLen; k++) {
                int col = prevCols[k];
                if (matrix[i][col] == 1) {
                    curHeights[curLen] = prevHeights[k] + 1;
                    curCols[curLen] = col;
                    seen[col] = true;
                    curLen++;
                }
            }
            for (int col = 0; col < c; col++) {
                if (!seen[col] && matrix[i][col] == 1) {
                    curHeights[curLen] = 1;
                    curCols[curLen] = col;
                    curLen++;
                }
            }
            for (int k = 0; k < curLen; k++) {
                best = Math.max(best, curHeights[k] * (k + 1));
            }
            int[] tmpHeights = prevHeights;
            prevHeights = curHeights;
            curHeights = tmpHeights;
            int[] tmpCols = prevCols;
            prevCols = curCols;
            curCols = tmpCols;
            prevLen = curLen;
        }
        return best;
    }
}
