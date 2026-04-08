package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XorAfterRangeMultiplicationQueriesTest {

    private static final int[][][] QUERIES = {
            {{0, 2, 1, 4}},                          // example 1
            {{1, 4, 2, 3}, {0, 2, 1, 2}},            // example 2
            {{0, 0, 1, 2}},                           // single element
            {{1, 2, 3, 2}},                           // step larger than range
            {{0, 0, 1, 5}, {0, 0, 1, 3}},            // multiple queries same index
            {{0, 0, 1, 100000}},                      // large values mod
    };

    private static final int[][] NUMS = {
            {1, 1, 1},
            {2, 3, 1, 5, 4},
            {3},
            {1, 5, 3},
            {2, 3},
            {1000000000},
    };

    private static final int[] EXPECTED = {
            4,
            31,
            6,
            1 ^ 10 ^ 3,
            30 ^ 3,
            (int) (1_000_000_000L * 100_000L % 1_000_000_007L),
    };

    @Test
    void xorAfterRangeMultiplicationQueriesAllCases() {
        for (int i = 0; i < EXPECTED.length; i++) {
            assertEquals(
                    EXPECTED[i],
                    XorAfterRangeMultiplicationQueries.xorAfterRangeMultiplicationQueries(
                            NUMS[i].clone(), QUERIES[i]),
                    "case index " + i);
        }
    }
}
