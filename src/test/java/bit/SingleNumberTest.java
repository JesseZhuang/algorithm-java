package bit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleNumberTest {

    @ParameterizedTest
    @MethodSource("cases")
    void returnsTheNumberWithoutAPair(int[] nums, int expected) {
        assertAll(
                () -> assertEquals(expected, SingleNumber.singleNumberXor(nums), "XOR"),
                () -> assertEquals(expected, SingleNumber.singleNumberHashSet(nums), "HashSet")
        );
    }

    private static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{2, 2, 1}, 1),
                Arguments.of(new int[]{4, 1, 2, 1, 2}, 4),
                Arguments.of(new int[]{1}, 1),
                Arguments.of(new int[]{-3, 0, -1, -3, 0}, -1),
                Arguments.of(new int[]{-2, 3, 0, 3, -2}, 0)
        );
    }
}
