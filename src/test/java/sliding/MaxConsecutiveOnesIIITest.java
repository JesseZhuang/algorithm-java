package sliding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxConsecutiveOnesIIITest {

    @Test
    void testSlidingWindowExample1() {
        assertEquals(6, MaxConsecutiveOnesIII.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    @Test
    void testSlidingWindowExample2() {
        assertEquals(10, MaxConsecutiveOnesIII.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    @Test
    void testSlidingWindowAllOnes() {
        assertEquals(5, MaxConsecutiveOnesIII.longestOnes(new int[]{1, 1, 1, 1, 1}, 0));
    }

    @Test
    void testSlidingWindowAllZerosFullK() {
        assertEquals(4, MaxConsecutiveOnesIII.longestOnes(new int[]{0, 0, 0, 0}, 4));
    }

    @Test
    void testSlidingWindowAllZerosPartialK() {
        assertEquals(2, MaxConsecutiveOnesIII.longestOnes(new int[]{0, 0, 0, 0}, 2));
    }

    @Test
    void testSlidingWindowKZero() {
        assertEquals(3, MaxConsecutiveOnesIII.longestOnes(new int[]{1, 1, 1, 0, 1, 1}, 0));
    }

    @Test
    void testSlidingWindowSingleOne() {
        assertEquals(1, MaxConsecutiveOnesIII.longestOnes(new int[]{1}, 0));
    }

    @Test
    void testSlidingWindowSingleZeroWithK() {
        assertEquals(1, MaxConsecutiveOnesIII.longestOnes(new int[]{0}, 1));
    }

    @Test
    void testSlidingWindowSingleZeroNoK() {
        assertEquals(0, MaxConsecutiveOnesIII.longestOnes(new int[]{0}, 0));
    }

    @Test
    void testSlidingWindowAlternating() {
        assertEquals(5, MaxConsecutiveOnesIII.longestOnes(new int[]{1, 0, 1, 0, 1}, 3));
    }

    @Test
    void testSlidingWindowAlternatingStartZero() {
        assertEquals(5, MaxConsecutiveOnesIII.longestOnes(new int[]{0, 1, 0, 1, 0, 1}, 2));
    }

    @Test
    void testBinarySearchExample1() {
        assertEquals(6, MaxConsecutiveOnesIII.longestOnes2(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    @Test
    void testBinarySearchExample2() {
        assertEquals(10, MaxConsecutiveOnesIII.longestOnes2(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    @Test
    void testBinarySearchAllOnes() {
        assertEquals(5, MaxConsecutiveOnesIII.longestOnes2(new int[]{1, 1, 1, 1, 1}, 0));
    }

    @Test
    void testBinarySearchAllZerosFullK() {
        assertEquals(4, MaxConsecutiveOnesIII.longestOnes2(new int[]{0, 0, 0, 0}, 4));
    }

    @Test
    void testBinarySearchAllZerosPartialK() {
        assertEquals(2, MaxConsecutiveOnesIII.longestOnes2(new int[]{0, 0, 0, 0}, 2));
    }

    @Test
    void testBinarySearchKZero() {
        assertEquals(3, MaxConsecutiveOnesIII.longestOnes2(new int[]{1, 1, 1, 0, 1, 1}, 0));
    }

    @Test
    void testBinarySearchSingleOne() {
        assertEquals(1, MaxConsecutiveOnesIII.longestOnes2(new int[]{1}, 0));
    }

    @Test
    void testBinarySearchSingleZeroWithK() {
        assertEquals(1, MaxConsecutiveOnesIII.longestOnes2(new int[]{0}, 1));
    }

    @Test
    void testBinarySearchSingleZeroNoK() {
        assertEquals(0, MaxConsecutiveOnesIII.longestOnes2(new int[]{0}, 0));
    }

    @Test
    void testBinarySearchAlternating() {
        assertEquals(5, MaxConsecutiveOnesIII.longestOnes2(new int[]{1, 0, 1, 0, 1}, 3));
    }

    @Test
    void testBinarySearchAlternatingStartZero() {
        assertEquals(5, MaxConsecutiveOnesIII.longestOnes2(new int[]{0, 1, 0, 1, 0, 1}, 2));
    }
}
