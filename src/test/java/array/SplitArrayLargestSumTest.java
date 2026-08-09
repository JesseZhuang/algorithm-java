package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitArrayLargestSumTest {

    private final SplitArrayLargestSum solution = new SplitArrayLargestSum();

    @Test
    void testExample1() {
        assertEquals(18, solution.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

    @Test
    void testExample2() {
        assertEquals(9, solution.splitArray(new int[]{1, 2, 3, 4, 5}, 2));
    }

    @Test
    void testSingleElement() {
        assertEquals(10, solution.splitArray(new int[]{10}, 1));
    }

    @Test
    void testKEqualsN() {
        assertEquals(5, solution.splitArray(new int[]{1, 2, 3, 4, 5}, 5));
    }

    @Test
    void testKEqualsOne() {
        assertEquals(15, solution.splitArray(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    void testEqualElements() {
        assertEquals(6, solution.splitArray(new int[]{3, 3, 3, 3}, 2));
    }

    @Test
    void testLargeElement() {
        assertEquals(1000000, solution.splitArray(new int[]{1000000, 1, 1}, 2));
    }
}
