package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestBridgeTest {

    @Test
    void test1() {
        int[][] grid = {{0, 1}, {1, 0}};
        assertEquals(1, new ShortestBridge.Solution().shortestBridge(grid));
    }

    @Test
    void test2() {
        int[][] grid = {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}};
        assertEquals(2, new ShortestBridge.Solution().shortestBridge(grid));
    }

    @Test
    void test3() {
        int[][] grid = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        assertEquals(1, new ShortestBridge.Solution().shortestBridge(grid));
    }
}
