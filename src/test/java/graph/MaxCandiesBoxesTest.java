package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxCandiesBoxesTest {

    @Test
    void example1() {
        assertEquals(
                16,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1, 0, 1, 0},
                        new int[] {7, 5, 4, 100},
                        new int[][] {{}, {}, {1}, {}},
                        new int[][] {{1, 2}, {3}, {}, {}},
                        new int[] {0}));
    }

    @Test
    void example2() {
        assertEquals(
                6,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1, 0, 0, 0, 0, 0},
                        new int[] {1, 1, 1, 1, 1, 1},
                        new int[][] {{1, 2, 3, 4, 5}, {}, {}, {}, {}, {}},
                        new int[][] {{1, 2, 3, 4, 5}, {}, {}, {}, {}, {}},
                        new int[] {0}));
    }

    @Test
    void noInitialBoxes() {
        assertEquals(
                0,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1, 1},
                        new int[] {10, 20},
                        new int[][] {{}, {}},
                        new int[][] {{}, {}},
                        new int[] {}));
    }

    @Test
    void singleOpenBox() {
        assertEquals(
                42,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1},
                        new int[] {42},
                        new int[][] {{}},
                        new int[][] {{}},
                        new int[] {0}));
    }

    @Test
    void singleClosedNoKey() {
        assertEquals(
                0,
                MaxCandiesBoxes.maxCandies(
                        new int[] {0},
                        new int[] {42},
                        new int[][] {{}},
                        new int[][] {{}},
                        new int[] {0}));
    }

    @Test
    void chainUnlock() {
        assertEquals(
                6,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1, 0, 0},
                        new int[] {1, 2, 3},
                        new int[][] {{1}, {2}, {}},
                        new int[][] {{1}, {2}, {}},
                        new int[] {0}));
    }

    @Test
    void keyBeforeBox() {
        assertEquals(
                111,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1, 0, 1},
                        new int[] {10, 100, 1},
                        new int[][] {{1}, {}, {}},
                        new int[][] {{}, {}, {1}},
                        new int[] {0, 2}));
    }

    @Test
    void unreachable() {
        assertEquals(
                5,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1, 0, 0},
                        new int[] {5, 10, 100},
                        new int[][] {{}, {}, {}},
                        new int[][] {{1}, {}, {}},
                        new int[] {0}));
    }

    @Test
    void duplicateKeys() {
        assertEquals(
                6,
                MaxCandiesBoxes.maxCandies(
                        new int[] {1, 1, 0},
                        new int[] {1, 2, 3},
                        new int[][] {{2}, {2}, {}},
                        new int[][] {{1}, {2}, {}},
                        new int[] {0}));
    }

    @Test
    void circularKeysBothClosed() {
        assertEquals(
                0,
                MaxCandiesBoxes.maxCandies(
                        new int[] {0, 0},
                        new int[] {10, 20},
                        new int[][] {{1}, {0}},
                        new int[][] {{}, {}},
                        new int[] {0, 1}));
    }
}
