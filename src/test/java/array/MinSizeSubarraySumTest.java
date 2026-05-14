package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinSizeSubarraySumTest {

    @Test
    void testMinSubArrayLen() {
        // Test case 1: Basic case from example
        assertEquals(2, MinSizeSubarraySum.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));

        // Test case 2: Single element sufficient
        assertEquals(1, MinSizeSubarraySum.minSubArrayLen(4, new int[]{1, 4, 4}));

        // Test case 3: No subarray sum >= target
        assertEquals(0, MinSizeSubarraySum.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));

        // Test case 4: Need 3 elements
        assertEquals(3, MinSizeSubarraySum.minSubArrayLen(11, new int[]{1, 2, 3, 4, 5}));

        // Test case 5: Need all elements
        assertEquals(5, MinSizeSubarraySum.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));

        // Test case 6: Target too large for small array
        assertEquals(0, MinSizeSubarraySum.minSubArrayLen(3, new int[]{1, 1}));

        // Test case 7: Single element array that matches
        assertEquals(1, MinSizeSubarraySum.minSubArrayLen(5, new int[]{5}));

        // Test case 8: Large target with longer array
        assertEquals(10, MinSizeSubarraySum.minSubArrayLen(100, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}));

        // Test case 9: First element already >= target
        assertEquals(1, MinSizeSubarraySum.minSubArrayLen(6, new int[]{10, 2, 3}));
    }

    @Test
    void testMinSubArrayLenBinarySearch() {
        // Test case 1: Basic case from example
        assertEquals(2, MinSizeSubarraySum.minSubArrayLenBinarySearch(7, new int[]{2, 3, 1, 2, 4, 3}));

        // Test case 2: Single element sufficient
        assertEquals(1, MinSizeSubarraySum.minSubArrayLenBinarySearch(4, new int[]{1, 4, 4}));

        // Test case 3: No subarray sum >= target
        assertEquals(0, MinSizeSubarraySum.minSubArrayLenBinarySearch(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));

        // Test case 4: Need 3 elements
        assertEquals(3, MinSizeSubarraySum.minSubArrayLenBinarySearch(11, new int[]{1, 2, 3, 4, 5}));

        // Test case 5: Need all elements
        assertEquals(5, MinSizeSubarraySum.minSubArrayLenBinarySearch(15, new int[]{1, 2, 3, 4, 5}));

        // Test case 6: Target too large for small array
        assertEquals(0, MinSizeSubarraySum.minSubArrayLenBinarySearch(3, new int[]{1, 1}));

        // Test case 7: Single element array that matches
        assertEquals(1, MinSizeSubarraySum.minSubArrayLenBinarySearch(5, new int[]{5}));

        // Test case 8: Large target with longer array
        assertEquals(10, MinSizeSubarraySum.minSubArrayLenBinarySearch(100, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}));

        // Test case 9: First element already >= target
        assertEquals(1, MinSizeSubarraySum.minSubArrayLenBinarySearch(6, new int[]{10, 2, 3}));
    }
}
