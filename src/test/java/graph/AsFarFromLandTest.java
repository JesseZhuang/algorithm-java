package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsFarFromLandTest {

    @Test
    void example1BFS() {
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        assertEquals(2, new AsFarFromLand.SolutionBFS().maxDistance(grid));
    }

    @Test
    void example2BFS() {
        int[][] grid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertEquals(4, new AsFarFromLand.SolutionBFS().maxDistance(grid));
    }

    @Test
    void allLandBFS() {
        int[][] grid = {{1, 1}, {1, 1}};
        assertEquals(-1, new AsFarFromLand.SolutionBFS().maxDistance(grid));
    }

    @Test
    void allWaterBFS() {
        int[][] grid = {{0, 0}, {0, 0}};
        assertEquals(-1, new AsFarFromLand.SolutionBFS().maxDistance(grid));
    }

    @Test
    void landCenterBFS() {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertEquals(2, new AsFarFromLand.SolutionBFS().maxDistance(grid));
    }

    @Test
    void minGridBFS() {
        int[][] grid = {{0, 1}, {1, 0}};
        assertEquals(1, new AsFarFromLand.SolutionBFS().maxDistance(grid));
    }

    @Test
    void example1DP() {
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        assertEquals(2, new AsFarFromLand.Solution().maxDistance(grid));
    }

    @Test
    void example2DP() {
        int[][] grid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertEquals(4, new AsFarFromLand.Solution().maxDistance(grid));
    }

    @Test
    void allLandDP() {
        int[][] grid = {{1, 1}, {1, 1}};
        assertEquals(-1, new AsFarFromLand.Solution().maxDistance(grid));
    }

    @Test
    void allWaterDP() {
        int[][] grid = {{0, 0}, {0, 0}};
        assertEquals(-1, new AsFarFromLand.Solution().maxDistance(grid));
    }

    @Test
    void landCenterDP() {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertEquals(2, new AsFarFromLand.Solution().maxDistance(grid));
    }

    @Test
    void minGridDP() {
        int[][] grid = {{0, 1}, {1, 0}};
        assertEquals(1, new AsFarFromLand.Solution().maxDistance(grid));
    }
}
