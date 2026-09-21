package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinationSumIVTest {
    CombinationSumIV tbt;

    @BeforeEach
    void setUp() {
        tbt = new CombinationSumIV();
    }

    @Test
    void testDP() {
        assertEquals(7, tbt.combinationSum4I(new int[]{1, 2, 3}, 4));
        assertEquals(0, tbt.combinationSum4I(new int[]{9}, 3));
        assertEquals(1, tbt.combinationSum4I(new int[]{1}, 1));
        assertEquals(5, tbt.combinationSum4I(new int[]{1, 2}, 4));
        assertEquals(8, tbt.combinationSum4I(new int[]{3, 1, 2, 4}, 4));
        assertEquals(982, tbt.combinationSum4I(new int[]{5, 1, 8}, 24));
    }

    @Test
    void testCache() {
        assertEquals(7, tbt.combinationSum4Cache(new int[]{1, 2, 3}, 4));
        assertEquals(0, tbt.combinationSum4Cache(new int[]{9}, 3));
        assertEquals(1, tbt.combinationSum4Cache(new int[]{1}, 1));
        assertEquals(5, tbt.combinationSum4Cache(new int[]{1, 2}, 4));
        assertEquals(8, tbt.combinationSum4Cache(new int[]{3, 1, 2, 4}, 4));
        assertEquals(982, tbt.combinationSum4Cache(new int[]{5, 1, 8}, 24));
    }
}
