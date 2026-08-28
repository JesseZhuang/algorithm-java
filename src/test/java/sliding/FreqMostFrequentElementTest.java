package sliding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FreqMostFrequentElementTest {

    @Test
    void testSlidingWindowExample1() {
        assertEquals(3, FreqMostFrequentElement.maxFrequency(new int[]{1, 2, 4}, 5));
    }

    @Test
    void testSlidingWindowExample2() {
        assertEquals(2, FreqMostFrequentElement.maxFrequency(new int[]{1, 4, 8, 13}, 5));
    }

    @Test
    void testSlidingWindowExample3() {
        assertEquals(1, FreqMostFrequentElement.maxFrequency(new int[]{3, 9, 6}, 2));
    }

    @Test
    void testSlidingWindowSingleElement() {
        assertEquals(1, FreqMostFrequentElement.maxFrequency(new int[]{10}, 5));
    }

    @Test
    void testSlidingWindowAllSame() {
        assertEquals(4, FreqMostFrequentElement.maxFrequency(new int[]{5, 5, 5, 5}, 0));
    }

    @Test
    void testBinarySearchExample1() {
        assertEquals(3, FreqMostFrequentElement.maxFrequency2(new int[]{1, 2, 4}, 5));
    }

    @Test
    void testBinarySearchExample2() {
        assertEquals(2, FreqMostFrequentElement.maxFrequency2(new int[]{1, 4, 8, 13}, 5));
    }

    @Test
    void testBinarySearchExample3() {
        assertEquals(1, FreqMostFrequentElement.maxFrequency2(new int[]{3, 9, 6}, 2));
    }

    @Test
    void testBinarySearchSingleElement() {
        assertEquals(1, FreqMostFrequentElement.maxFrequency2(new int[]{10}, 5));
    }

    @Test
    void testBinarySearchAllSame() {
        assertEquals(4, FreqMostFrequentElement.maxFrequency2(new int[]{5, 5, 5, 5}, 0));
    }
}
