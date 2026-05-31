package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SlidingWindowMaxTest {
    private final SlidingWindowMax.Solution sol = new SlidingWindowMax.Solution();

    @Test
    void example1() {
        assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7},
                sol.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    @Test
    void singleElement() {
        assertArrayEquals(new int[]{1}, sol.maxSlidingWindow(new int[]{1}, 1));
    }

    @Test
    void kEqualsLength() {
        assertArrayEquals(new int[]{7}, sol.maxSlidingWindow(new int[]{4, 2, 7, 1}, 4));
    }

    @Test
    void decreasing() {
        assertArrayEquals(new int[]{9, 8, 7}, sol.maxSlidingWindow(new int[]{9, 8, 7, 6, 5}, 3));
    }

    @Test
    void underflowCase() {
        assertArrayEquals(new int[]{10, 10, 9, 2},
                sol.maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5));
    }
}
