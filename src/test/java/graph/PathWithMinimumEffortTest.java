package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathWithMinimumEffortTest {

    @Test
    void testExample1() {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        assertEquals(2, PathWithMinimumEffort.minimumEffortPathDijkstra(heights));
        assertEquals(2, PathWithMinimumEffort.minimumEffortPathBinarySearch(heights));
    }

    @Test
    void testExample2() {
        int[][] heights = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        assertEquals(1, PathWithMinimumEffort.minimumEffortPathDijkstra(heights));
        assertEquals(1, PathWithMinimumEffort.minimumEffortPathBinarySearch(heights));
    }

    @Test
    void testExample3() {
        int[][] heights = {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};
        assertEquals(0, PathWithMinimumEffort.minimumEffortPathDijkstra(heights));
        assertEquals(0, PathWithMinimumEffort.minimumEffortPathBinarySearch(heights));
    }

    @Test
    void testSingleCell() {
        int[][] heights = {{5}};
        assertEquals(0, PathWithMinimumEffort.minimumEffortPathDijkstra(heights));
        assertEquals(0, PathWithMinimumEffort.minimumEffortPathBinarySearch(heights));
    }

    @Test
    void testSingleRow() {
        int[][] heights = {{1, 10, 6, 7, 9, 10, 4, 9}};
        assertEquals(9, PathWithMinimumEffort.minimumEffortPathDijkstra(heights));
        assertEquals(9, PathWithMinimumEffort.minimumEffortPathBinarySearch(heights));
    }

    @Test
    void testSingleColumn() {
        int[][] heights = {{1}, {10}, {6}, {7}};
        assertEquals(9, PathWithMinimumEffort.minimumEffortPathDijkstra(heights));
        assertEquals(9, PathWithMinimumEffort.minimumEffortPathBinarySearch(heights));
    }

    @Test
    void testTwoByTwo() {
        int[][] heights = {{1, 100}, {100, 1}};
        assertEquals(99, PathWithMinimumEffort.minimumEffortPathDijkstra(heights));
        assertEquals(99, PathWithMinimumEffort.minimumEffortPathBinarySearch(heights));
    }
}
