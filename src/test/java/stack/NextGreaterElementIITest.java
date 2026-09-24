package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NextGreaterElementIITest {

    @Test
    void testExample1() {
        assertArrayEquals(new int[]{2, 3, 4, -1, 4},
                NextGreaterElementII.nextGreaterElements(new int[]{1, 2, 3, 4, 3}));
        assertArrayEquals(new int[]{2, 3, 4, -1, 4},
                NextGreaterElementII.nextGreaterElements2(new int[]{1, 2, 3, 4, 3}));
    }

    @Test
    void testExample2() {
        assertArrayEquals(new int[]{2, -1, 2},
                NextGreaterElementII.nextGreaterElements(new int[]{1, 2, 1}));
        assertArrayEquals(new int[]{2, -1, 2},
                NextGreaterElementII.nextGreaterElements2(new int[]{1, 2, 1}));
    }

    @Test
    void testSingle() {
        assertArrayEquals(new int[]{-1},
                NextGreaterElementII.nextGreaterElements(new int[]{5}));
        assertArrayEquals(new int[]{-1},
                NextGreaterElementII.nextGreaterElements2(new int[]{5}));
    }

    @Test
    void testAllSame() {
        assertArrayEquals(new int[]{-1, -1, -1},
                NextGreaterElementII.nextGreaterElements(new int[]{3, 3, 3}));
        assertArrayEquals(new int[]{-1, -1, -1},
                NextGreaterElementII.nextGreaterElements2(new int[]{3, 3, 3}));
    }

    @Test
    void testCircularWrap() {
        assertArrayEquals(new int[]{5, 5, -1},
                NextGreaterElementII.nextGreaterElements(new int[]{4, 3, 5}));
        assertArrayEquals(new int[]{5, 5, -1},
                NextGreaterElementII.nextGreaterElements2(new int[]{4, 3, 5}));
    }

    @Test
    void testIncreasing() {
        assertArrayEquals(new int[]{2, 3, 4, -1},
                NextGreaterElementII.nextGreaterElements(new int[]{1, 2, 3, 4}));
        assertArrayEquals(new int[]{2, 3, 4, -1},
                NextGreaterElementII.nextGreaterElements2(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testMixed() {
        assertArrayEquals(new int[]{5, 5, -1, 4, 5},
                NextGreaterElementII.nextGreaterElements(new int[]{3, 1, 5, 2, 4}));
        assertArrayEquals(new int[]{5, 5, -1, 4, 5},
                NextGreaterElementII.nextGreaterElements2(new int[]{3, 1, 5, 2, 4}));
    }

    @Test
    void testTwoElements() {
        assertArrayEquals(new int[]{2, -1},
                NextGreaterElementII.nextGreaterElements(new int[]{1, 2}));
        assertArrayEquals(new int[]{2, -1},
                NextGreaterElementII.nextGreaterElements2(new int[]{1, 2}));
    }

    @Test
    void testTwoElementsSame() {
        assertArrayEquals(new int[]{-1, -1},
                NextGreaterElementII.nextGreaterElements(new int[]{2, 2}));
        assertArrayEquals(new int[]{-1, -1},
                NextGreaterElementII.nextGreaterElements2(new int[]{2, 2}));
    }

    @Test
    void testNegative() {
        assertArrayEquals(new int[]{0, 0, -1},
                NextGreaterElementII.nextGreaterElements(new int[]{-1, -3, 0}));
        assertArrayEquals(new int[]{0, 0, -1},
                NextGreaterElementII.nextGreaterElements2(new int[]{-1, -3, 0}));
    }
}
