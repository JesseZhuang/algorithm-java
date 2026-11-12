package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MaximumSumQueriesTest {

    @Test
    void officialExampleOnePreservesQueryOrder() {
        assertArrayEquals(new int[]{6, 10, 7}, MaximumSumQueries.maximumSumQueries(
                new int[]{4, 3, 1, 2}, new int[]{2, 4, 9, 5},
                new int[][]{{4, 1}, {1, 3}, {2, 5}}));
    }

    @Test
    void officialExampleTwo() {
        assertArrayEquals(new int[]{9, 9, 9}, MaximumSumQueries.maximumSumQueries(
                new int[]{3, 2, 5}, new int[]{2, 3, 4},
                new int[][]{{4, 4}, {3, 2}, {1, 1}}));
    }

    @Test
    void officialExampleThreeHasNoQualifyingPoint() {
        assertArrayEquals(new int[]{-1}, MaximumSumQueries.maximumSumQueries(
                new int[]{2, 1}, new int[]{2, 3}, new int[][]{{3, 3}}));
    }

    @Test
    void returnsMinusOneWhenEitherThresholdCannotBeMet() {
        assertArrayEquals(new int[]{-1, -1, -1, 10}, MaximumSumQueries.maximumSumQueries(
                new int[]{8, 2}, new int[]{2, 8},
                new int[][]{{9, 1}, {1, 9}, {5, 5}, {1, 1}}));
    }

    @Test
    void handlesDuplicateCoordinatesAndInclusiveThresholds() {
        assertArrayEquals(new int[]{12, 12, 12, -1, 12}, MaximumSumQueries.maximumSumQueries(
                new int[]{7, 3, 4, 4}, new int[]{5, 5, 4, 3},
                new int[][]{{7, 5}, {3, 5}, {4, 2}, {7, 6}, {4, 4}}));
    }

    @Test
    void supportsMaximumSumOfTwoBillion() {
        assertArrayEquals(new int[]{2_000_000_000, 2_000_000_000},
                MaximumSumQueries.maximumSumQueries(
                        new int[]{1, 1_000_000_000}, new int[]{1_000_000_000, 1_000_000_000},
                        new int[][]{{1_000_000_000, 1_000_000_000}, {1, 1}}));
    }
}
