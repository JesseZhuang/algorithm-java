package array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartitionArrayIntoThreePartsWithEqualSumTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1}, true),
                Arguments.of(new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1}, false),
                Arguments.of(new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4}, true),
                Arguments.of(new int[]{0, 0, 0}, true),
                Arguments.of(new int[]{0, 0, 0, 0}, true),
                Arguments.of(new int[]{-1, -1, -1}, true),
                Arguments.of(new int[]{1, 1, 2}, false),
                Arguments.of(new int[]{1, 1, 1, 0}, true),
                Arguments.of(new int[]{1, -1, 0, 0}, true),
                Arguments.of(new int[]{1, -1, 0}, false),
                Arguments.of(new int[]{1, -1, 1, -1}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void requiresThreeNonemptyEqualSumParts(int[] arr, boolean expected) {
        assertEquals(expected, PartitionArrayIntoThreePartsWithEqualSum.canThreePartsEqualSum(arr));
    }
}
