package queue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubarrayAbsDiffLimitTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{8, 2, 4, 7}, 4, 2),
                Arguments.of(new int[]{10, 1, 2, 4, 7, 2}, 5, 4),
                Arguments.of(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0, 3),
                Arguments.of(new int[]{5}, 0, 1),
                Arguments.of(new int[]{3, 3, 3, 3, 3}, 0, 5),
                Arguments.of(new int[]{1, 2, 1, 2, 1}, 0, 1),
                Arguments.of(new int[]{1, 5, 9, 2, 7}, 100, 5),
                Arguments.of(new int[]{9, 8, 7, 6, 5}, 2, 3),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 2, 3),
                Arguments.of(new int[]{1, 3}, 2, 2),
                Arguments.of(new int[]{1, 4}, 2, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testDeque(int[] nums, int limit, int expected) {
        assertEquals(expected, LongestSubarrayAbsDiffLimit.longestSubarrayDeque(nums, limit));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testTreeMap(int[] nums, int limit, int expected) {
        assertEquals(expected, LongestSubarrayAbsDiffLimit.longestSubarrayTreeMap(nums, limit));
    }
}
