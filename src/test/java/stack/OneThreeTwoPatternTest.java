package stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneThreeTwoPatternTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, false),
                Arguments.of(new int[]{3, 1, 4, 2}, true),
                Arguments.of(new int[]{-1, 3, 2, 0}, true),
                Arguments.of(new int[]{1, 2}, false),
                Arguments.of(new int[]{5, 4, 3, 2, 1}, false),
                Arguments.of(new int[]{3, 3, 3, 3}, false),
                Arguments.of(new int[]{-2, 1, -1}, true),
                Arguments.of(new int[]{1, 3, 2}, true),
                Arguments.of(new int[]{3, 5, 0, 3, 4}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testFind132patternStack(int[] nums, boolean expected) {
        assertEquals(expected, OneThreeTwoPattern.find132patternStack(nums));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testFind132patternPrefixMin(int[] nums, boolean expected) {
        assertEquals(expected, OneThreeTwoPattern.find132patternPrefixMin(nums));
    }
}
