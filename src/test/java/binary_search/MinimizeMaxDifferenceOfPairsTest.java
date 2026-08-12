package binary_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimizeMaxDifferenceOfPairsTest {

    @Test
    void testExample1() {
        assertEquals(1, MinimizeMaxDifferenceOfPairs.minimizeMax(new int[]{10, 1, 2, 7, 1, 3}, 2));
    }

    @Test
    void testExample2() {
        assertEquals(0, MinimizeMaxDifferenceOfPairs.minimizeMax(new int[]{4, 2, 1, 2}, 1));
    }

    @Test
    void testZeroPairs() {
        assertEquals(0, MinimizeMaxDifferenceOfPairs.minimizeMax(new int[]{5, 3, 1}, 0));
    }

    @Test
    void testSinglePair() {
        assertEquals(4, MinimizeMaxDifferenceOfPairs.minimizeMax(new int[]{1, 5}, 1));
    }

    @Test
    void testAllEqual() {
        assertEquals(0, MinimizeMaxDifferenceOfPairs.minimizeMax(new int[]{3, 3, 3, 3}, 2));
    }

    @Test
    void testConsecutive() {
        assertEquals(1, MinimizeMaxDifferenceOfPairs.minimizeMax(new int[]{1, 2, 3, 4, 5, 6}, 3));
    }

    @Test
    void testZeros() {
        assertEquals(0, MinimizeMaxDifferenceOfPairs.minimizeMax(new int[]{0, 0}, 1));
    }
}
