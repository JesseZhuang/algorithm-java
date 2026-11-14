package sliding;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumOperationsToReduceXToZeroTest {

    @ParameterizedTest
    @MethodSource("cases")
    void testSlidingWindow(int[] nums, int x, int expected) {
        assertEquals(expected, MinimumOperationsToReduceXToZero.minOperations(nums, x));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testPrefixSum(int[] nums, int x, int expected) {
        assertEquals(expected, MinimumOperationsToReduceXToZero.minOperationsByPrefixSum(nums, x));
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 4, 2, 3}, 5, 2),
                Arguments.of(new int[]{5, 6, 7, 8, 9}, 4, -1),
                Arguments.of(new int[]{3, 2, 20, 1, 1, 3}, 10, 5),
                Arguments.of(new int[]{1, 2, 3}, 6, 3),
                Arguments.of(new int[]{1, 2, 3}, 0, 0),
                Arguments.of(new int[]{1, 2, 3}, 2, -1),
                Arguments.of(new int[]{1, 2, 3}, 7, -1),
                Arguments.of(new int[]{1}, 1, 1)
        );
    }
}
