package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindMinRotatedSortedArrayTest {

    private FindMinRotatedSortedArray solution;

    @BeforeEach
    void setUp() {
        solution = new FindMinRotatedSortedArray();
    }

    @Test
    void testRotatedThreeTimes() {
        assertEquals(1, solution.findMin(new int[]{3, 4, 5, 1, 2}));
    }

    @Test
    void testRotatedFourTimes() {
        assertEquals(0, solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    @Test
    void testNoRotation() {
        assertEquals(11, solution.findMin(new int[]{11, 13, 15, 17}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.findMin(new int[]{1}));
    }

    @Test
    void testTwoElementsRotated() {
        assertEquals(1, solution.findMin(new int[]{2, 1}));
    }

    @Test
    void testTwoElementsSorted() {
        assertEquals(1, solution.findMin(new int[]{1, 2}));
    }

    @Test
    void testMinAtSecondPosition() {
        assertEquals(1, solution.findMin(new int[]{7, 1, 2, 3, 4, 5, 6}));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-5, solution.findMin(new int[]{1, 2, 3, -5, -4, -3, -2, -1, 0}));
    }
}
