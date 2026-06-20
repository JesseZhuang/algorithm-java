package graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestIncreasingPathTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}, 4),
                Arguments.of(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}, 4),
                Arguments.of(new int[][]{{1}}, 1),
                Arguments.of(new int[][]{{1, 2}}, 2),
                Arguments.of(new int[][]{{7, 8, 9}, {9, 7, 6}, {7, 2, 3}}, 6),
                Arguments.of(new int[][]{{1, 1}, {1, 1}}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testDFS(int[][] matrix, int expected) {
        assertEquals(expected, LongestIncreasingPath.longestIncreasingPathDFS(matrix));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testBFS(int[][] matrix, int expected) {
        assertEquals(expected, LongestIncreasingPath.longestIncreasingPathBFS(matrix));
    }
}
