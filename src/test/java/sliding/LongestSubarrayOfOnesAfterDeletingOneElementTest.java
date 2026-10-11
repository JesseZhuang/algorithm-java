package sliding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubarrayOfOnesAfterDeletingOneElementTest {

    @Test
    void testExamples() {
        assertBoth(3, new int[]{1, 1, 0, 1});
        assertBoth(5, new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1});
    }

    @Test
    void testAllOnesStillDeletesOneElement() {
        assertBoth(2, new int[]{1, 1, 1});
    }

    @Test
    void testAllZeros() {
        assertBoth(0, new int[]{0, 0, 0, 0});
    }

    @Test
    void testSingleton() {
        assertBoth(0, new int[]{1});
        assertBoth(0, new int[]{0});
    }

    @Test
    void testWindowContainingMultipleZeros() {
        assertBoth(3, new int[]{1, 0, 0, 1, 1, 1});
        assertBoth(3, new int[]{0, 0, 1, 1, 1, 0});
    }

    private void assertBoth(int expected, int[] nums) {
        assertEquals(expected, LongestSubarrayOfOnesAfterDeletingOneElement.longestSubarray(nums));
        assertEquals(expected, LongestSubarrayOfOnesAfterDeletingOneElement.longestSubarrayWithPrefixSuffix(nums));
    }
}
