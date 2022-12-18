package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
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

    // https://codeforces.com/contest/295/problem/A
    @ParameterizedTest
    @MethodSource({"paramProvider"})
    void testGregArray(int[] original, int[][] operations, int[][] whichOpsToApply, int[] expected) {
        DifferenceArray diff = new DifferenceArray(original);
        System.out.println(Arrays.toString(original));
        for (int[] whichOps : whichOpsToApply) {
            for (int i = whichOps[0]; i <= whichOps[1]; i++)
                diff.update(operations[i - 1][0] - 1, operations[i - 1][1] - 1, operations[i - 1][2]);
        }
        assertArrayEquals(expected, diff.getOriginal());
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