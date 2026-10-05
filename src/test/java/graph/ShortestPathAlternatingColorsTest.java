package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ShortestPathAlternatingColorsTest {

    @Test
    void example1() {
        int[][] redEdges = {{0, 1}, {1, 2}};
        int[][] blueEdges = {};

        assertArrayEquals(
                new int[]{0, 1, -1},
                ShortestPathAlternatingColors.shortestAlternatingPaths(3, redEdges, blueEdges));
    }

    @Test
    void example2() {
        int[][] redEdges = {{0, 1}};
        int[][] blueEdges = {{2, 1}};

        assertArrayEquals(
                new int[]{0, 1, -1},
                ShortestPathAlternatingColors.shortestAlternatingPaths(3, redEdges, blueEdges));
    }

    @Test
    void singleNode() {
        assertArrayEquals(
                new int[]{0},
                ShortestPathAlternatingColors.shortestAlternatingPaths(1, new int[][]{}, new int[][]{}));
    }

    @Test
    void continuesOnlyWhenColorsAlternate() {
        int[][] redEdges = {{0, 1}, {2, 3}};
        int[][] blueEdges = {{1, 2}};

        assertArrayEquals(
                new int[]{0, 1, 2, 3},
                ShortestPathAlternatingColors.shortestAlternatingPaths(4, redEdges, blueEdges));
        assertArrayEquals(
                new int[]{0, 1, 2},
                ShortestPathAlternatingColors.shortestAlternatingPaths(
                        3, new int[][]{{1, 2}}, new int[][]{{0, 1}}));
    }

    @Test
    void revisitsSameNodeWithDifferentPreviousColor() {
        int[][] redEdges = {{0, 1}, {1, 2}};
        int[][] blueEdges = {{0, 1}, {1, 3}};

        assertArrayEquals(
                new int[]{0, 1, 2, 2},
                ShortestPathAlternatingColors.shortestAlternatingPaths(4, redEdges, blueEdges));
    }

    @Test
    void handlesCyclesDuplicateEdgesAndUnreachableNodes() {
        int[][] redEdges = {{0, 1}, {0, 1}, {2, 0}};
        int[][] blueEdges = {{1, 2}, {2, 1}};

        assertArrayEquals(
                new int[]{0, 1, 2, -1, -1},
                ShortestPathAlternatingColors.shortestAlternatingPaths(5, redEdges, blueEdges));
    }
}
