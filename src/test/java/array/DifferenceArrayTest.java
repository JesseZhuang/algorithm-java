package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DifferenceArrayTest {

    DifferenceArray tbt;

    @BeforeEach
    void setUp() {
        tbt = new DifferenceArray(new int[]{10, 5, 20, 40, 3, 6});
    }

    @Test
    void testUpdateAndGet() {
        tbt.update(0, 1, 10);
        assertArrayEquals(new int[]{20, 15, 20, 40, 3, 6}, tbt.getOriginal());
        tbt.update(1, 3, 20);
        tbt.update(2, 2, 30);
        assertArrayEquals(new int[]{20, 35, 70, 60, 3, 6}, tbt.getOriginal());
        tbt.update(3, 5, -10);
        assertArrayEquals(new int[]{20, 35, 70, 50, -7, -4}, tbt.getOriginal());
    }

    /**
     * https://codeforces.com/contest/295/problem/A
     *
     * @param original,        size n, e.g., [1,2,3]
     * @param operations,      size m, index are 1 based, [left, right, delta], inclusive
     * @param whichOpsToApply, size k, ops range: O(m), index are 1 based, [left, right], inclusive
     * @param expected,        size n
     */
    @ParameterizedTest
    @MethodSource({"paramProvider"})
    void testGregArray(int[] original, int[][] operations, int[][] whichOpsToApply, int[] expected) {
        DifferenceArray diff = new DifferenceArray(original); // O(n)
        // O(k*m+2n)
        for (int[] whichOps : whichOpsToApply) { // O(k)
            for (int i = whichOps[0]; i <= whichOps[1]; i++) // O(m)
                diff.update(operations[i - 1][0] - 1, operations[i - 1][1] - 1, operations[i - 1][2]);
        }
        assertArrayEquals(expected, diff.getOriginal()); // O(n)
        // O(k+2m+2n)
        diff = new DifferenceArray(original); // O(n)
        int m = operations.length;
        DifferenceArray diff2 = new DifferenceArray(new int[m]); // O(m)
        for (int[] whichOps : whichOpsToApply) // O(k)
            diff2.update(whichOps[0] - 1, whichOps[1] - 1, 1);
        int[] opsCount = diff2.getOriginal(); // O(m)
        for (int i = 0; i < m; i++) { // O(m)
            if (opsCount[i] > 0)
                diff.update(operations[i][0] - 1,
                        operations[i][1] - 1, operations[i][2] * opsCount[i]);
        }
        assertArrayEquals(expected, diff.getOriginal()); // O(n)
    }

    static Stream<Arguments> paramProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 4}},
                        new int[][]{{1, 2}, {1, 3}, {2, 3}}, new int[]{9, 18, 17}),
                Arguments.of(new int[]{1}, new int[][]{{1, 1, 1}},
                        new int[][]{{1, 1}}, new int[]{2}),
                Arguments.of(new int[]{1, 2, 3, 4}, new int[][]{{1, 2, 1}, {2, 3, 2}, {3, 4, 4}},
                        new int[][]{{1, 2}, {1, 3}, {2, 3}, {1, 2}, {1, 3}, {2, 3}}, new int[]{5, 18, 31, 20})
        );
    }

}