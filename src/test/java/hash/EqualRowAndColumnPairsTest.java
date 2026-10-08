package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualRowAndColumnPairsTest {

    private void assertPairCount(int expected, int[][] grid) {
        assertEquals(expected, EqualRowAndColumnPairs.equalPairs(grid));
        assertEquals(expected, EqualRowAndColumnPairs.equalPairsBruteForce(grid));
    }

    @Test
    void officialExampleOne() {
        assertPairCount(1, new int[][]{
                {3, 2, 1},
                {1, 7, 6},
                {2, 7, 7}
        });
    }

    @Test
    void officialExampleTwo() {
        assertPairCount(3, new int[][]{
                {3, 1, 2, 2},
                {1, 4, 4, 5},
                {2, 4, 2, 2},
                {2, 4, 2, 2}
        });
    }

    @Test
    void singleCellMatchesItself() {
        assertPairCount(1, new int[][]{{42}});
    }

    @Test
    void allEqualRowsAndColumnsCountDuplicates() {
        assertPairCount(9, new int[][]{
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5}
        });
    }

    @Test
    void noRowsMatchAnyColumns() {
        assertPairCount(0, new int[][]{
                {1, 2},
                {3, 4}
        });
    }
}
