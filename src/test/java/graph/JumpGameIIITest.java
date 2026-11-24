package graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameIIITest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("jumpCases")
    void canReachWithBFS(String scenario, int[] arr, int start, boolean expected) {
        assertEquals(expected, JumpGameIII.canReach(arr, start));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("jumpCases")
    void canReachWithIterativeDFS(String scenario, int[] arr, int start, boolean expected) {
        assertEquals(expected, JumpGameIII.canReachDFS(arr, start));
    }

    private static Stream<Arguments> jumpCases() {
        return Stream.of(
                Arguments.of("example 1", new int[]{4, 2, 3, 0, 3, 1, 2}, 5, true),
                Arguments.of("example 2", new int[]{4, 2, 3, 0, 3, 1, 2}, 0, true),
                Arguments.of("example 3", new int[]{3, 0, 2, 1, 2}, 2, false),
                Arguments.of("starts on the only zero", new int[]{0}, 0, true),
                Arguments.of("zero reached by jumping right", new int[]{2, 1, 0}, 0, true),
                Arguments.of("path to zero starts with a left jump", new int[]{1, 0, 2}, 2, true),
                Arguments.of("both jumps are out of bounds", new int[]{2, 2, 2}, 1, false),
                Arguments.of("cycle has no zero", new int[]{1, 1}, 0, false));
    }
}
