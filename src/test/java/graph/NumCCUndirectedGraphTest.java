package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumCCUndirectedGraphTest {

    NumCCUndirectedGraph tbt;

    @BeforeEach
    void setUp() {
        tbt = new NumCCUndirectedGraph();
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void ccDFS(int n, int[][] edges, int expected) {
        assertEquals(expected, tbt.ccDFS(n, edges));
        assertEquals(expected, tbt.ccBFS(n, edges));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}, 2),
                Arguments.of(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}, 1)
        );
    }
}