package sliding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSubarraysMaxKTest {

    @Test
    void testSlidingWindowExample1() {
        assertEquals(6, CountSubarraysMaxK.countSubarrays(new int[]{1, 3, 2, 3, 3}, 2));
    }

    @Test
    void testSlidingWindowExample2() {
        assertEquals(0, CountSubarraysMaxK.countSubarrays(new int[]{1, 4, 2, 1}, 3));
    }

    @Test
    void testSlidingWindowAllMax() {
        assertEquals(6, CountSubarraysMaxK.countSubarrays(new int[]{5, 5, 5, 5}, 2));
    }

    @Test
    void testSlidingWindowSingleElement() {
        assertEquals(1, CountSubarraysMaxK.countSubarrays(new int[]{7}, 1));
    }

    @Test
    void testSlidingWindowMaxAtEnds() {
        assertEquals(1, CountSubarraysMaxK.countSubarrays(new int[]{3, 1, 1, 3}, 2));
    }

    @Test
    void testSlidingWindowAlternating() {
        assertEquals(1, CountSubarraysMaxK.countSubarrays(new int[]{2, 1, 2, 1, 2}, 3));
    }

    @Test
    void testSlidingWindowMaxOnce() {
        assertEquals(3, CountSubarraysMaxK.countSubarrays(new int[]{1, 2, 3}, 1));
    }

    @Test
    void testSlidingWindowNotEnoughMax() {
        assertEquals(0, CountSubarraysMaxK.countSubarrays(new int[]{5, 1, 5, 1}, 3));
    }

    @Test
    void testSlidingWindowMaxAtFront() {
        assertEquals(7, CountSubarraysMaxK.countSubarrays(new int[]{4, 4, 4, 1, 1}, 2));
    }

    @Test
    void testBinarySearchExample1() {
        assertEquals(6, CountSubarraysMaxK.countSubarrays2(new int[]{1, 3, 2, 3, 3}, 2));
    }

    @Test
    void testBinarySearchExample2() {
        assertEquals(0, CountSubarraysMaxK.countSubarrays2(new int[]{1, 4, 2, 1}, 3));
    }

    @Test
    void testBinarySearchAllMax() {
        assertEquals(6, CountSubarraysMaxK.countSubarrays2(new int[]{5, 5, 5, 5}, 2));
    }

    @Test
    void testBinarySearchSingleElement() {
        assertEquals(1, CountSubarraysMaxK.countSubarrays2(new int[]{7}, 1));
    }

    @Test
    void testBinarySearchMaxAtEnds() {
        assertEquals(1, CountSubarraysMaxK.countSubarrays2(new int[]{3, 1, 1, 3}, 2));
    }

    @Test
    void testBinarySearchAlternating() {
        assertEquals(1, CountSubarraysMaxK.countSubarrays2(new int[]{2, 1, 2, 1, 2}, 3));
    }

    @Test
    void testBinarySearchMaxOnce() {
        assertEquals(3, CountSubarraysMaxK.countSubarrays2(new int[]{1, 2, 3}, 1));
    }

    @Test
    void testBinarySearchNotEnoughMax() {
        assertEquals(0, CountSubarraysMaxK.countSubarrays2(new int[]{5, 1, 5, 1}, 3));
    }

    @Test
    void testBinarySearchMaxAtFront() {
        assertEquals(7, CountSubarraysMaxK.countSubarrays2(new int[]{4, 4, 4, 1, 1}, 2));
    }
}
