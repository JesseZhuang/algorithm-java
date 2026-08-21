package array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Search2DMatrixTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                // standard 5x5 matrix
                Arguments.of(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5, true),
                Arguments.of(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20, false),
                // single element
                Arguments.of(new int[][]{{5}}, 5, true),
                Arguments.of(new int[][]{{5}}, 3, false),
                // single row
                Arguments.of(new int[][]{{1, 4, 7, 11, 15}}, 7, true),
                Arguments.of(new int[][]{{1, 4, 7, 11, 15}}, 6, false),
                // single column
                Arguments.of(new int[][]{{1}, {3}, {5}, {7}}, 5, true),
                Arguments.of(new int[][]{{1}, {3}, {5}, {7}}, 4, false),
                // negative values
                Arguments.of(new int[][]{{-10, -5, 0}, {-3, 2, 6}, {1, 4, 9}}, -5, true),
                Arguments.of(new int[][]{{-10, -5, 0}, {-3, 2, 6}, {1, 4, 9}}, -7, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSearchMatrix1(int[][] matrix, int target, boolean expected) {
        assertEquals(expected, new Search2DMatrix().searchMatrix1(matrix, target));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSearchMatrix(int[][] matrix, int target, boolean expected) {
        assertEquals(expected, new Search2DMatrix().searchMatrix(matrix, target));
    }
}
