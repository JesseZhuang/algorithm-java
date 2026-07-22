package graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumHeightTreesTest {

    @Test
    void singleNode() {
        assertEquals(List.of(0), MinimumHeightTrees.findMinHeightTrees(1, new int[][]{}));
    }

    @Test
    void twoNodes() {
        assertEquals(List.of(0, 1), MinimumHeightTrees.findMinHeightTrees(2, new int[][]{{0, 1}}));
    }

    @Test
    void star() {
        // n=4, edges=[[1,0],[1,2],[1,3]] → [1]
        assertEquals(List.of(1), MinimumHeightTrees.findMinHeightTrees(4,
                new int[][]{{1, 0}, {1, 2}, {1, 3}}));
    }

    @Test
    void twoCenters() {
        // n=6, edges=[[3,0],[3,1],[3,2],[3,4],[5,4]] → [3,4]
        assertEquals(List.of(3, 4), MinimumHeightTrees.findMinHeightTrees(6,
                new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}));
    }

    @Test
    void linearOdd() {
        // 0-1-2-3-4 → center is [2]
        assertEquals(List.of(2), MinimumHeightTrees.findMinHeightTrees(5,
                new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
    }

    @Test
    void linearEven() {
        // 0-1-2-3 → centers are [1, 2]
        assertEquals(List.of(1, 2), MinimumHeightTrees.findMinHeightTrees(4,
                new int[][]{{0, 1}, {1, 2}, {2, 3}}));
    }
}
