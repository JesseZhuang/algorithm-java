package stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfSubarrayMinimumsTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[]{3, 1, 2, 4}, 17),
                Arguments.of(new int[]{11, 81, 94, 43, 3}, 444),
                Arguments.of(new int[]{5}, 5),
                Arguments.of(new int[]{1, 2}, 4),
                Arguments.of(new int[]{3, 2, 1}, 10),
                Arguments.of(new int[]{1, 2, 3}, 10),
                Arguments.of(new int[]{2, 2, 2}, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSumSubarrayMins(int[] arr, int expected) {
        assertEquals(expected, SumOfSubarrayMinimums.sumSubarrayMins(arr));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSumSubarrayMins2(int[] arr, int expected) {
        assertEquals(expected, SumOfSubarrayMinimums.sumSubarrayMins2(arr));
    }
}
