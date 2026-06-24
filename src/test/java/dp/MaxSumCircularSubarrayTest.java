package dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxSumCircularSubarrayTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[]{1, -2, 3, -2}, 3),
                Arguments.of(new int[]{5, -3, 5}, 10),
                Arguments.of(new int[]{-3, -2, -3}, -2),
                Arguments.of(new int[]{7}, 7),
                Arguments.of(new int[]{-1}, -1),
                Arguments.of(new int[]{-5, -3, -4, -1, -2}, -1),
                Arguments.of(new int[]{3, 1, 2, 4}, 10),
                Arguments.of(new int[]{5, -3, -100, 2, 4}, 11),
                Arguments.of(new int[]{3, -1}, 3),
                Arguments.of(new int[]{8, -1, -1, 8, -7, 4}, 18)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testKadane(int[] nums, int expected) {
        assertEquals(expected, MaxSumCircularSubarray.maxSubarraySumCircularKadane(nums));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testDeque(int[] nums, int expected) {
        assertEquals(expected, MaxSumCircularSubarray.maxSubarraySumCircularDeque(nums));
    }
}
