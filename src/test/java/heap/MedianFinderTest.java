package heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianFinderTest {
    private static final double DELTA = 1e-5;
    private MedianFinder mf;

    @BeforeEach
    void setUp() {
        mf = new MedianFinder();
    }

    @Test
    void testExample1() {
        mf.addNum(1);
        mf.addNum(2);
        assertEquals(1.5, mf.findMedian(), DELTA);
        mf.addNum(3);
        assertEquals(2.0, mf.findMedian(), DELTA);
    }

    @Test
    void testSingleElement() {
        mf.addNum(5);
        assertEquals(5.0, mf.findMedian(), DELTA);
    }

    @Test
    void testTwoElements() {
        mf.addNum(1);
        mf.addNum(2);
        assertEquals(1.5, mf.findMedian(), DELTA);
    }

    @Test
    void testNegativeNumbers() {
        for (int num : new int[]{-5, -3, -1, 0, 2}) mf.addNum(num);
        assertEquals(-1.0, mf.findMedian(), DELTA);
    }

    @Test
    void testDuplicates() {
        for (int num : new int[]{5, 5, 5, 5}) mf.addNum(num);
        assertEquals(5.0, mf.findMedian(), DELTA);
    }

    @Test
    void testLargeRange() {
        mf.addNum(-100000);
        mf.addNum(100000);
        assertEquals(0.0, mf.findMedian(), DELTA);
    }

    @Test
    void testIncremental() {
        mf.addNum(6);
        assertEquals(6.0, mf.findMedian(), DELTA);
        mf.addNum(10);
        assertEquals(8.0, mf.findMedian(), DELTA);
        mf.addNum(2);
        assertEquals(6.0, mf.findMedian(), DELTA);
        mf.addNum(6);
        assertEquals(6.0, mf.findMedian(), DELTA);
    }
}
