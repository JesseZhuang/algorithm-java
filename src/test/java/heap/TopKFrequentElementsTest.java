package heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TopKFrequentElementsTest {

    @Test
    void testBucketExample1() {
        int[] res = TopKFrequentElements.topKFrequentBucket(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Arrays.sort(res);
        assertArrayEquals(new int[]{1, 2}, res);
    }

    @Test
    void testBucketExample2() {
        assertArrayEquals(new int[]{1}, TopKFrequentElements.topKFrequentBucket(new int[]{1}, 1));
    }

    @Test
    void testBucketNegatives() {
        int[] res = TopKFrequentElements.topKFrequentBucket(new int[]{-1, -1, -2, -2, -2, -3}, 2);
        Arrays.sort(res);
        assertArrayEquals(new int[]{-2, -1}, res);
    }

    @Test
    void testBucketAllSame() {
        assertArrayEquals(new int[]{5}, TopKFrequentElements.topKFrequentBucket(new int[]{5, 5, 5, 5}, 1));
    }

    @Test
    void testBucketMultiple() {
        int[] res = TopKFrequentElements.topKFrequentBucket(new int[]{4, 4, 4, 1, 1, 2, 2, 2, 3}, 3);
        Arrays.sort(res);
        assertArrayEquals(new int[]{1, 2, 4}, res);
    }

    @Test
    void testHeapExample1() {
        int[] res = TopKFrequentElements.topKFrequentHeap(new int[]{1, 1, 1, 2, 2, 3}, 2);
        Arrays.sort(res);
        assertArrayEquals(new int[]{1, 2}, res);
    }

    @Test
    void testHeapExample2() {
        assertArrayEquals(new int[]{1}, TopKFrequentElements.topKFrequentHeap(new int[]{1}, 1));
    }

    @Test
    void testHeapNegatives() {
        int[] res = TopKFrequentElements.topKFrequentHeap(new int[]{-1, -1, -2, -2, -2, -3}, 2);
        Arrays.sort(res);
        assertArrayEquals(new int[]{-2, -1}, res);
    }

    @Test
    void testHeapAllSame() {
        assertArrayEquals(new int[]{5}, TopKFrequentElements.topKFrequentHeap(new int[]{5, 5, 5, 5}, 1));
    }

    @Test
    void testHeapMultiple() {
        int[] res = TopKFrequentElements.topKFrequentHeap(new int[]{4, 4, 4, 1, 1, 2, 2, 2, 3}, 3);
        Arrays.sort(res);
        assertArrayEquals(new int[]{1, 2, 4}, res);
    }
}
