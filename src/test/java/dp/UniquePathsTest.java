package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniquePathsTest {
    UniquePaths tbt;

    @BeforeEach
    void setUp() {
        tbt = new UniquePaths();
    }

    @Test
    void testDP() {
        assertEquals(28, tbt.uniquePathsDP(3, 7));
        assertEquals(3, tbt.uniquePathsDP(3, 2));
        assertEquals(1, tbt.uniquePathsDP(1, 1));
        assertEquals(1, tbt.uniquePathsDP(1, 100));
        assertEquals(48620, tbt.uniquePathsDP(10, 10));
    }

    @Test
    void testCombination() {
        assertEquals(28, tbt.uniquePathsCombination(3, 7));
        assertEquals(3, tbt.uniquePathsCombination(3, 2));
        assertEquals(1, tbt.uniquePathsCombination(1, 1));
        assertEquals(1, tbt.uniquePathsCombination(1, 100));
        assertEquals(48620, tbt.uniquePathsCombination(10, 10));
    }
}
