package array;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class RunningSumOf1dArrayTest {

    @Test
    void singleton() {
        assertArrayEquals(new int[]{7}, RunningSumOf1dArray.runningSum(new int[]{7}));
    }

    @Test
    void firstExample() {
        assertArrayEquals(new int[]{1, 3, 6, 10}, RunningSumOf1dArray.runningSum(new int[]{1, 2, 3, 4}));
    }

    @Test
    void secondExample() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, RunningSumOf1dArray.runningSum(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    void allZeros() {
        assertArrayEquals(new int[]{0, 0, 0}, RunningSumOf1dArray.runningSum(new int[]{0, 0, 0}));
    }

    @Test
    void allNegative() {
        assertArrayEquals(new int[]{-2, -5, -9}, RunningSumOf1dArray.runningSum(new int[]{-2, -3, -4}));
    }

    @Test
    void mixedValues() {
        assertArrayEquals(new int[]{3, 1, 5, 0, 6}, RunningSumOf1dArray.runningSum(new int[]{3, -2, 4, -5, 6}));
    }

    @Test
    void maximumLengthAndMagnitude() {
        int[] nums = new int[1000];
        Arrays.fill(nums, 1_000_000);

        int[] result = RunningSumOf1dArray.runningSum(nums);

        assertEquals(1_000_000, result[0]);
        assertEquals(500_000_000, result[499]);
        assertEquals(1_000_000_000, result[999]);
    }

    @Test
    void returnsTheMutatedInputArray() {
        int[] nums = {4, 5, 6};

        int[] result = RunningSumOf1dArray.runningSum(nums);

        assertSame(nums, result);
        assertArrayEquals(new int[]{4, 9, 15}, nums);
    }
}
