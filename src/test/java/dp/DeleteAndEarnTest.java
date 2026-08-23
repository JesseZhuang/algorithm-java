package dp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteAndEarnTest {

    @Test
    void testBasic() {
        assertEquals(6, DeleteAndEarn.deleteAndEarn(new int[]{3, 4, 2}));
        assertEquals(9, DeleteAndEarn.deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, DeleteAndEarn.deleteAndEarn(new int[]{1}));
        assertEquals(10000, DeleteAndEarn.deleteAndEarn(new int[]{10000}));
    }

    @Test
    void testAllSame() {
        assertEquals(9, DeleteAndEarn.deleteAndEarn(new int[]{3, 3, 3}));
    }

    @Test
    void testNonAdjacent() {
        assertEquals(18, DeleteAndEarn.deleteAndEarn(new int[]{1, 1, 1, 5, 5, 5}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, DeleteAndEarn.deleteAndEarn(new int[]{1, 2}));
    }

    @Test
    void testConsecutiveSequence() {
        assertEquals(6, DeleteAndEarn.deleteAndEarn(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testMixed() {
        assertEquals(9, DeleteAndEarn.deleteAndEarn(new int[]{3, 3, 3, 4}));
        assertEquals(16, DeleteAndEarn.deleteAndEarn(new int[]{1, 1, 1, 1, 2, 3, 3, 3, 3}));
    }

    // --- Sort + Group DP method ---

    @Test
    void testSortGroupBasic() {
        assertEquals(6, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{3, 4, 2}));
        assertEquals(9, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{2, 2, 3, 3, 3, 4}));
    }

    @Test
    void testSortGroupSingleElement() {
        assertEquals(1, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{1}));
        assertEquals(10000, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{10000}));
    }

    @Test
    void testSortGroupAllSame() {
        assertEquals(9, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{3, 3, 3}));
    }

    @Test
    void testSortGroupNonAdjacent() {
        assertEquals(18, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{1, 1, 1, 5, 5, 5}));
    }

    @Test
    void testSortGroupTwoElements() {
        assertEquals(2, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{1, 2}));
    }

    @Test
    void testSortGroupConsecutiveSequence() {
        assertEquals(6, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testSortGroupMixed() {
        assertEquals(9, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{3, 3, 3, 4}));
        assertEquals(16, DeleteAndEarn.deleteAndEarnSortGroup(new int[]{1, 1, 1, 1, 2, 3, 3, 3, 3}));
    }
}
