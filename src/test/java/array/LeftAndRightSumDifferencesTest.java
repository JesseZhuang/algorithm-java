package array;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LeftAndRightSumDifferencesTest {

    @Test
    void example() {
        assertArrayEquals(new int[]{15, 1, 11, 22},
                LeftAndRightSumDifferences.leftRightDifference(new int[]{10, 4, 8, 3}));
    }

    @Test
    void singletonHasNoValuesOnEitherSide() {
        assertArrayEquals(new int[]{0},
                LeftAndRightSumDifferences.leftRightDifference(new int[]{1}));
    }

    @Test
    void equalSidesAtCenter() {
        assertArrayEquals(new int[]{6, 0, 6},
                LeftAndRightSumDifferences.leftRightDifference(new int[]{5, 1, 5}));
    }

    @Test
    void twoElementsUseEmptySideAtEachEnd() {
        assertArrayEquals(new int[]{100_000, 1},
                LeftAndRightSumDifferences.leftRightDifference(new int[]{1, 100_000}));
    }

    @Test
    void maximumLengthAndValues() {
        int[] nums = new int[1000];
        Arrays.fill(nums, 100_000);

        int[] result = LeftAndRightSumDifferences.leftRightDifference(nums);

        assertEquals(1000, result.length);
        assertEquals(99_900_000, result[0]);
        assertEquals(100_000, result[499]);
        assertEquals(100_000, result[500]);
        assertEquals(99_900_000, result[999]);
        assertEquals(100_000, nums[0]);
    }
}
