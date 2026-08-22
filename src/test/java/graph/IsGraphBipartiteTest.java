package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsGraphBipartiteTest {

    @Test
    void testBipartiteBFS() {
        assertTrue(IsGraphBipartite.isBipartiteBFS(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        assertFalse(IsGraphBipartite.isBipartiteBFS(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        assertTrue(IsGraphBipartite.isBipartiteBFS(new int[][]{{}}));
        assertTrue(IsGraphBipartite.isBipartiteBFS(new int[][]{{1}, {0}, {3}, {2}}));
        assertFalse(IsGraphBipartite.isBipartiteBFS(new int[][]{{1}, {0}, {3, 4}, {2, 4}, {2, 3}}));
        assertTrue(IsGraphBipartite.isBipartiteBFS(new int[][]{{}, {}, {}}));
        assertFalse(IsGraphBipartite.isBipartiteBFS(new int[][]{{1, 2}, {0, 2}, {0, 1}}));
        assertTrue(IsGraphBipartite.isBipartiteBFS(new int[][]{{3, 4, 5}, {3, 4, 5}, {3, 4, 5}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}}));
    }

    @Test
    void testBipartiteUF() {
        assertTrue(IsGraphBipartite.isBipartiteUF(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
        assertFalse(IsGraphBipartite.isBipartiteUF(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
        assertTrue(IsGraphBipartite.isBipartiteUF(new int[][]{{}}));
        assertTrue(IsGraphBipartite.isBipartiteUF(new int[][]{{1}, {0}, {3}, {2}}));
        assertFalse(IsGraphBipartite.isBipartiteUF(new int[][]{{1}, {0}, {3, 4}, {2, 4}, {2, 3}}));
        assertTrue(IsGraphBipartite.isBipartiteUF(new int[][]{{}, {}, {}}));
        assertFalse(IsGraphBipartite.isBipartiteUF(new int[][]{{1, 2}, {0, 2}, {0, 1}}));
        assertTrue(IsGraphBipartite.isBipartiteUF(new int[][]{{3, 4, 5}, {3, 4, 5}, {3, 4, 5}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}}));
    }
}
