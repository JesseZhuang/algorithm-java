package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** LeetCode 2352. Medium. Tags: array, hash table, matrix. */
public final class EqualRowAndColumnPairs {

    private EqualRowAndColumnPairs() {
    }

    /** Expected O(n^2) time and O(n^2) space for the row-frequency map. */
    public static int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<List<Integer>, Integer> rowFrequencies = new HashMap<>();
        for (int[] row : grid) { // n rows, each converted and hashed in O(n).
            List<Integer> rowKey = Arrays.stream(row).boxed().toList();
            rowFrequencies.merge(rowKey, 1, Integer::sum);
        }

        int pairs = 0;
        for (int columnIndex = 0; columnIndex < n; columnIndex++) { // n columns, each built in O(n).
            List<Integer> columnKey = new ArrayList<>(n);
            for (int rowIndex = 0; rowIndex < n; rowIndex++) {
                columnKey.add(grid[rowIndex][columnIndex]);
            }
            pairs += rowFrequencies.getOrDefault(columnKey, 0);
        }
        return pairs;
    }

    /** O(n^3) time and O(1) extra space. */
    public static int equalPairsBruteForce(int[][] grid) {
        int n = grid.length;
        int pairs = 0;
        for (int rowIndex = 0; rowIndex < n; rowIndex++) { // n rows * n columns * n values.
            for (int columnIndex = 0; columnIndex < n; columnIndex++) {
                boolean matches = true;
                for (int valueIndex = 0; valueIndex < n; valueIndex++) {
                    if (grid[rowIndex][valueIndex] != grid[valueIndex][columnIndex]) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    pairs++;
                }
            }
        }
        return pairs;
    }
}
