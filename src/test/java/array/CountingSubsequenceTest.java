package array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountingSubsequenceTest {

    @ParameterizedTest
    @MethodSource("tests")
    void testCountingSubsequence(int[] nums, int target, int expected) {
        assertEquals(expected, new CountingSubsequence(nums).subsequenceCount(target));
    }

    @Test
    void testCountTwice() {
        CountingSubsequence tbt = new CountingSubsequence(new int[]{2, 3, -1, 1, 5, 5, -5, 5});
        assertEquals(9, tbt.subsequenceCount(5));
        assertEquals(6, tbt.subsequenceCount(10));
    }

    static Stream<Arguments> tests() {
        return Stream.of(
                Arguments.of(new int[]{24, 17, 23, 24, 5, 47}, 47, 2),
                Arguments.of(new int[]{2, 7, 1, 8, 2, 8, 1, 8, 2, 8, 4, 5, 9}, 47, 3),
                Arguments.of(new int[]{2, 47, 10047, 47, 1047, 47, 47}, 47, 4)
        );
    }

}