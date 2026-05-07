package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LargestRectangleTest {

    LargestRectangle tbt = new LargestRectangle();

    @Test
    void testStackExample1() {
        assertEquals(10, LargestRectangle.largestRectangleAreaStack(new int[]{2, 1, 5, 6, 2, 3}));
    }

    @Test
    void testStackExample2() {
        assertEquals(4, LargestRectangle.largestRectangleAreaStack(new int[]{2, 4}));
    }

    @Test
    void testStackSingle() {
        assertEquals(5, LargestRectangle.largestRectangleAreaStack(new int[]{5}));
    }

    @Test
    void testStackIncreasing() {
        assertEquals(9, LargestRectangle.largestRectangleAreaStack(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testStackDecreasing() {
        assertEquals(9, LargestRectangle.largestRectangleAreaStack(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testStackAllSame() {
        assertEquals(12, LargestRectangle.largestRectangleAreaStack(new int[]{3, 3, 3, 3}));
    }

    @Test
    void testArrayExample1() {
        assertEquals(10, tbt.largestRectangleAreaArray(new int[]{2, 1, 5, 6, 2, 3}));
    }

    @Test
    void testArrayExample2() {
        assertEquals(4, tbt.largestRectangleAreaArray(new int[]{2, 4}));
    }

    @Test
    void testArraySingle() {
        assertEquals(5, tbt.largestRectangleAreaArray(new int[]{5}));
    }

    @Test
    void testArrayIncreasing() {
        assertEquals(9, tbt.largestRectangleAreaArray(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testArrayDecreasing() {
        assertEquals(9, tbt.largestRectangleAreaArray(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testArrayAllSame() {
        assertEquals(12, tbt.largestRectangleAreaArray(new int[]{3, 3, 3, 3}));
    }
}
