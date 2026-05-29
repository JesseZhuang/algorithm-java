package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacificWaterFlowTest {

    private static Set<List<Integer>> toSet(List<List<Integer>> list) {
        return new HashSet<>(list);
    }

    private static Set<List<Integer>> makeExpected(int[][] coords) {
        Set<List<Integer>> set = new HashSet<>();
        for (int[] c : coords) set.add(Arrays.asList(c[0], c[1]));
        return set;
    }

    @Test
    void testExample1DFS() {
        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        Set<List<Integer>> expected = makeExpected(new int[][]{
                {0, 4}, {1, 3}, {1, 4}, {2, 2}, {3, 0}, {3, 1}, {4, 0}
        });
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticDFS(heights)));
    }

    @Test
    void testExample1BFS() {
        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        Set<List<Integer>> expected = makeExpected(new int[][]{
                {0, 4}, {1, 3}, {1, 4}, {2, 2}, {3, 0}, {3, 1}, {4, 0}
        });
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticBFS(heights)));
    }

    @Test
    void testSingleCellDFS() {
        int[][] heights = {{1}};
        Set<List<Integer>> expected = makeExpected(new int[][]{{0, 0}});
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticDFS(heights)));
    }

    @Test
    void testSingleCellBFS() {
        int[][] heights = {{1}};
        Set<List<Integer>> expected = makeExpected(new int[][]{{0, 0}});
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticBFS(heights)));
    }

    @Test
    void testFlatGridDFS() {
        int[][] heights = {{1, 1}, {1, 1}};
        Set<List<Integer>> expected = makeExpected(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticDFS(heights)));
    }

    @Test
    void testFlatGridBFS() {
        int[][] heights = {{1, 1}, {1, 1}};
        Set<List<Integer>> expected = makeExpected(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticBFS(heights)));
    }

    @Test
    void testSingleRowDFS() {
        int[][] heights = {{1, 2, 3, 4, 5}};
        Set<List<Integer>> expected = makeExpected(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}});
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticDFS(heights)));
    }

    @Test
    void testSingleRowBFS() {
        int[][] heights = {{1, 2, 3, 4, 5}};
        Set<List<Integer>> expected = makeExpected(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}});
        PacificWaterFlow sol = new PacificWaterFlow();
        assertEquals(expected, toSet(sol.pacificAtlanticBFS(heights)));
    }
}
