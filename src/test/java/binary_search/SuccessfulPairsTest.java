package binary_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SuccessfulPairsTest {

    @Test
    void testExample1() {
        assertArrayEquals(new int[]{4, 0, 3},
                SuccessfulPairs.successfulPairs(new int[]{5, 1, 3}, new int[]{1, 2, 3, 4, 5}, 7));
    }

    @Test
    void testExample2() {
        assertArrayEquals(new int[]{2, 0, 2},
                SuccessfulPairs.successfulPairs(new int[]{3, 1, 2}, new int[]{8, 5, 8}, 16));
    }

    @Test
    void testSingleSuccess() {
        // spell=5, potion=3, success=15 -> 5*3=15 >= 15, success
        assertArrayEquals(new int[]{1},
                SuccessfulPairs.successfulPairs(new int[]{5}, new int[]{3}, 15));
    }

    @Test
    void testSingleFail() {
        // spell=5, potion=2, success=11 -> 5*2=10 < 11, fail
        assertArrayEquals(new int[]{0},
                SuccessfulPairs.successfulPairs(new int[]{5}, new int[]{2}, 11));
    }

    @Test
    void testLargeValuesNoOverflow() {
        // 10^5 * 10^5 = 10^10, success = 10^10 -> should succeed
        assertArrayEquals(new int[]{1},
                SuccessfulPairs.successfulPairs(new int[]{100000}, new int[]{100000}, 10000000000L));
    }

    @Test
    void testSpellOneNeedsPotionThreshold() {
        // spell=1, potions=[5,6,7,8], success=7 -> need potion>=7, answer=2 (7,8)
        assertArrayEquals(new int[]{2},
                SuccessfulPairs.successfulPairs(new int[]{1}, new int[]{5, 6, 7, 8}, 7));
    }
}
