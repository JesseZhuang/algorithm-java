package array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Search2DMatrixITest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3, true),
                Arguments.of(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13, false),
                Arguments.of(new int[][]{{1}}, 1, true),
                Arguments.of(new int[][]{{1}}, 0, false),
                Arguments.of(new int[][]{{1, 3}}, 3, true),
                Arguments.of(new int[][]{{1}, {3}}, 3, true),
                Arguments.of(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 60, true),
                Arguments.of(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 1, true),
                Arguments.of(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 11, true)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSearchMatrix1(int[][] matrix, int target, boolean expected) {
        assertEquals(expected, Search2DMatrixI.searchMatrix1(matrix, target));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSearchMatrix2(int[][] matrix, int target, boolean expected) {
        assertEquals(expected, Search2DMatrixI.searchMatrix2(matrix, target));
    }
}
