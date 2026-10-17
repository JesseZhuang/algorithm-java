package bit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubarrayWithMaximumBitwiseAndTest {

    @Test
    void singleton() {
        assertEquals(1, LongestSubarrayWithMaximumBitwiseAnd.longestSubarray(new int[]{7}));
    }

    @Test
    void allEqual() {
        assertEquals(4, LongestSubarrayWithMaximumBitwiseAnd.longestSubarray(new int[]{5, 5, 5, 5}));
    }

    @Test
    void multipleMaximumRuns() {
        assertEquals(3, LongestSubarrayWithMaximumBitwiseAnd.longestSubarray(
                new int[]{1, 7, 7, 2, 7, 7, 7, 3, 7}));
    }

    @Test
    void maximumDiscoveredLateResetsPreviousBest() {
        assertEquals(2, LongestSubarrayWithMaximumBitwiseAnd.longestSubarray(
                new int[]{2, 2, 2, 3, 3, 4, 4}));
    }

    @Test
    void shorterLaterRunDoesNotReplaceBest() {
        assertEquals(3, LongestSubarrayWithMaximumBitwiseAnd.longestSubarray(
                new int[]{5, 5, 5, 1, 5, 5}));
    }

    @Test
    void hundredThousandValues() {
        int[] nums = new int[100_000];
        Arrays.fill(nums, 1);
        Arrays.fill(nums, 25_000, 75_000, 1_000_000_000);

        assertEquals(50_000, LongestSubarrayWithMaximumBitwiseAnd.longestSubarray(nums));
    }

    @Test
    void extremeValues() {
        assertEquals(2, LongestSubarrayWithMaximumBitwiseAnd.longestSubarray(
                new int[]{1, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE}));
    }
}
