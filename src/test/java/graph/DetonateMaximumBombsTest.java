package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetonateMaximumBombsTest {

    @Test
    void testExample1() {
        int[][] bombs = {{2, 1, 3}, {6, 1, 4}};
        assertEquals(2, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(2, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }

    @Test
    void testExample2() {
        int[][] bombs = {{1, 1, 5}, {10, 10, 5}};
        assertEquals(1, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(1, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }

    @Test
    void testExample3() {
        int[][] bombs = {{1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}};
        assertEquals(5, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(5, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }

    @Test
    void testSingleBomb() {
        int[][] bombs = {{0, 0, 1}};
        assertEquals(1, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(1, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }

    @Test
    void testNoChain() {
        int[][] bombs = {{0, 0, 1}, {100, 100, 1}, {200, 200, 1}};
        assertEquals(1, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(1, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }

    @Test
    void testOneDirectional() {
        // bomb 0 has radius 10, can reach bomb 1 at distance 5
        // bomb 1 has radius 1, cannot reach bomb 0
        int[][] bombs = {{0, 0, 10}, {5, 0, 1}};
        assertEquals(2, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(2, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }

    @Test
    void testExactBoundary() {
        // distance from (0,0) to (3,4) = 5, radius of bomb 0 is 5, exactly on boundary
        int[][] bombs = {{0, 0, 5}, {3, 4, 1}};
        assertEquals(2, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(2, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }

    @Test
    void testChain() {
        // bomb 0 at (0,0) r=2 can reach bomb 1 at (1,0)
        // bomb 1 at (1,0) r=2 can reach bomb 2 at (3,0) (distance=2, radius=2)
        // chain: 0->1->2
        int[][] bombs = {{0, 0, 2}, {1, 0, 2}, {3, 0, 1}};
        assertEquals(3, DetonateMaximumBombs.maximumDetonationBFS(bombs));
        assertEquals(3, DetonateMaximumBombs.maximumDetonationDFS(bombs));
    }
}
