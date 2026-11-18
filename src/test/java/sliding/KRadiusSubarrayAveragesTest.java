package sliding;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class KRadiusSubarrayAveragesTest {

    @Test
    void testSample() {
        assertArrayEquals(new int[]{-1, -1, -1, 5, 4, 4, -1, -1, -1},
                KRadiusSubarrayAverages.getAverages(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3));
    }

    @Test
    void testZeroRadiusSingleElement() {
        assertArrayEquals(new int[]{100000},
                KRadiusSubarrayAverages.getAverages(new int[]{100000}, 0));
    }

    @Test
    void testZeroRadiusEveryElement() {
        assertArrayEquals(new int[]{4, 0, 9},
                KRadiusSubarrayAverages.getAverages(new int[]{4, 0, 9}, 0));
    }

    @Test
    void testWindowTooLarge() {
        assertArrayEquals(new int[]{-1},
                KRadiusSubarrayAverages.getAverages(new int[]{8}, 100000));
    }

    @Test
    void testWindowJustTooLarge() {
        assertArrayEquals(new int[]{-1, -1, -1, -1},
                KRadiusSubarrayAverages.getAverages(new int[]{1, 2, 3, 4}, 2));
    }

    @Test
    void testFirstAndLastValidCenters() {
        assertArrayEquals(new int[]{-1, 4, 5, 6, -1},
                KRadiusSubarrayAverages.getAverages(new int[]{2, 4, 6, 5, 8}, 1));
    }

    @Test
    void testWindowEqualsArrayLength() {
        assertArrayEquals(new int[]{-1, -1, 3, -1, -1},
                KRadiusSubarrayAverages.getAverages(new int[]{1, 2, 3, 4, 7}, 2));
    }

    @Test
    void testSignedValuesTruncateTowardZero() {
        assertArrayEquals(new int[]{-1, -2, 0, 2, -1},
                KRadiusSubarrayAverages.getAverages(new int[]{-5, -2, -1, 4, 3}, 1));
    }

    @Test
    void testSumExceedsIntRange() {
        int[] nums = new int[100000];
        Arrays.fill(nums, 100000);
        int[] expected = new int[100000];
        Arrays.fill(expected, -1);
        expected[49999] = 100000;
        expected[50000] = 100000;

        assertArrayEquals(expected, KRadiusSubarrayAverages.getAverages(nums, 49999));
    }

    @Test
    void testPrefixSumSample() {
        assertArrayEquals(new int[]{-1, -1, -1, 5, 4, 4, -1, -1, -1},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3));
    }

    @Test
    void testPrefixSumZeroRadiusSingleElement() {
        assertArrayEquals(new int[]{100000},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{100000}, 0));
    }

    @Test
    void testPrefixSumZeroRadiusEveryElement() {
        assertArrayEquals(new int[]{4, 0, 9},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{4, 0, 9}, 0));
    }

    @Test
    void testPrefixSumWindowTooLarge() {
        assertArrayEquals(new int[]{-1},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{8}, 100000));
    }

    @Test
    void testPrefixSumWindowJustTooLarge() {
        assertArrayEquals(new int[]{-1, -1, -1, -1},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{1, 2, 3, 4}, 2));
    }

    @Test
    void testPrefixSumFirstAndLastValidCenters() {
        assertArrayEquals(new int[]{-1, 4, 5, 6, -1},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{2, 4, 6, 5, 8}, 1));
    }

    @Test
    void testPrefixSumWindowEqualsArrayLength() {
        assertArrayEquals(new int[]{-1, -1, 3, -1, -1},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{1, 2, 3, 4, 7}, 2));
    }

    @Test
    void testPrefixSumSignedValuesTruncateTowardZero() {
        assertArrayEquals(new int[]{-1, -2, 0, 2, -1},
                KRadiusSubarrayAverages.getAveragesByPrefixSum(new int[]{-5, -2, -1, 4, 3}, 1));
    }

    @Test
    void testPrefixSumSumExceedsIntRange() {
        int[] nums = new int[100000];
        Arrays.fill(nums, 100000);
        int[] expected = new int[100000];
        Arrays.fill(expected, -1);
        expected[49999] = 100000;
        expected[50000] = 100000;

        assertArrayEquals(expected, KRadiusSubarrayAverages.getAveragesByPrefixSum(nums, 49999));
    }
}
