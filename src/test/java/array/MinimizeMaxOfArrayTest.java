package array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinimizeMaxOfArrayTest {
    @Test
    void testPrefixSum() {
        assertEquals(5, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{3, 7, 1, 6}));
        assertEquals(10, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{10, 1}));
        assertEquals(5, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{5}));
        assertEquals(4, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{4, 4, 4, 4}));
        assertEquals(10, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{10, 5, 1}));
        assertEquals(6, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{1, 5, 10}));
        assertEquals(0, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{0, 0, 0}));
        assertEquals(20, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{0, 0, 0, 0, 100}));
        assertEquals(5, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{1, 9}));
        assertEquals(6, MinimizeMaxOfArray.PrefixSum.minimizeArrayValue(new int[]{1, 10}));
    }

    @Test
    void testBinarySearch() {
        assertEquals(5, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{3, 7, 1, 6}));
        assertEquals(10, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{10, 1}));
        assertEquals(5, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{5}));
        assertEquals(4, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{4, 4, 4, 4}));
        assertEquals(10, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{10, 5, 1}));
        assertEquals(6, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{1, 5, 10}));
        assertEquals(0, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{0, 0, 0}));
        assertEquals(20, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{0, 0, 0, 0, 100}));
        assertEquals(5, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{1, 9}));
        assertEquals(6, MinimizeMaxOfArray.BinarySearch.minimizeArrayValue(new int[]{1, 10}));
    }
}
