package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IntervalListIntersectionsTest {

    @Test
    void testLeetCodeExample1() {
        int[][] first = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] second = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] expected = {{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}};
        assertArrayEquals(expected, IntervalListIntersections.intervalIntersection(first, second));
    }

    @Test
    void testLeetCodeExample2EmptyList() {
        int[][] first = {{1, 3}, {5, 9}};
        int[][] second = {};
        assertArrayEquals(new int[0][], IntervalListIntersections.intervalIntersection(first, second));
    }

    @Test
    void testNoIntersection() {
        int[][] first = {{1, 2}, {5, 6}};
        int[][] second = {{3, 4}, {7, 8}};
        assertArrayEquals(new int[0][], IntervalListIntersections.intervalIntersection(first, second));
    }

    @Test
    void testFullOverlap() {
        int[][] first = {{1, 5}};
        int[][] second = {{1, 5}};
        assertArrayEquals(new int[][]{{1, 5}}, IntervalListIntersections.intervalIntersection(first, second));
    }

    @Test
    void testOneContainsOther() {
        int[][] first = {{1, 10}};
        int[][] second = {{3, 5}, {7, 8}};
        assertArrayEquals(new int[][]{{3, 5}, {7, 8}}, IntervalListIntersections.intervalIntersection(first, second));
    }

    @Test
    void testTouchingEndpoints() {
        int[][] first = {{1, 3}};
        int[][] second = {{3, 5}};
        assertArrayEquals(new int[][]{{3, 3}}, IntervalListIntersections.intervalIntersection(first, second));
    }

    @Test
    void testSinglePointIntervals() {
        int[][] first = {{2, 2}, {5, 5}};
        int[][] second = {{1, 3}, {4, 6}};
        assertArrayEquals(new int[][]{{2, 2}, {5, 5}}, IntervalListIntersections.intervalIntersection(first, second));
    }
}
