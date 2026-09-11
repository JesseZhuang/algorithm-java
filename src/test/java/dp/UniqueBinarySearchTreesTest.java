package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueBinarySearchTreesTest {

    @Test
    void testNumTreesDP() {
        assertEquals(1, UniqueBinarySearchTrees.numTreesDP(0));
        assertEquals(1, UniqueBinarySearchTrees.numTreesDP(1));
        assertEquals(2, UniqueBinarySearchTrees.numTreesDP(2));
        assertEquals(5, UniqueBinarySearchTrees.numTreesDP(3));
        assertEquals(14, UniqueBinarySearchTrees.numTreesDP(4));
        assertEquals(42, UniqueBinarySearchTrees.numTreesDP(5));
        assertEquals(1767263190, UniqueBinarySearchTrees.numTreesDP(19));
    }

    @Test
    void testNumTreesCatalan() {
        assertEquals(1, UniqueBinarySearchTrees.numTreesCatalan(0));
        assertEquals(1, UniqueBinarySearchTrees.numTreesCatalan(1));
        assertEquals(2, UniqueBinarySearchTrees.numTreesCatalan(2));
        assertEquals(5, UniqueBinarySearchTrees.numTreesCatalan(3));
        assertEquals(14, UniqueBinarySearchTrees.numTreesCatalan(4));
        assertEquals(42, UniqueBinarySearchTrees.numTreesCatalan(5));
        assertEquals(1767263190, UniqueBinarySearchTrees.numTreesCatalan(19));
    }
}
