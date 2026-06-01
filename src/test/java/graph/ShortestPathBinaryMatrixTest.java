package graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortestPathBinaryMatrixTest {

    static Stream<Arguments> cases() {
        return Stream.of(
            Arguments.of(new int[][]{{0, 1}, {1, 0}}, 2),
            Arguments.of(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}, 4),
            Arguments.of(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}}, -1),
            Arguments.of(new int[][]{{0}}, 1),
            Arguments.of(new int[][]{{0, 0}, {0, 1}}, -1),
            Arguments.of(new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}}, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testBfs(int[][] grid, int expected) {
        assertEquals(expected, ShortestPathBinaryMatrix.bfs(grid));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testAStar(int[][] grid, int expected) {
        assertEquals(expected, ShortestPathBinaryMatrix.aStar(grid));
    }
}
