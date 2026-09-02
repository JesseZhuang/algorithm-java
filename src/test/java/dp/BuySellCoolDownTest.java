package dp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuySellCoolDownTest {
    private static BuySellCoolDown tbt;

    @BeforeAll
    static void setup() {
        tbt = new BuySellCoolDown();
    }

    private void assertBoth(int expected, int[] prices) {
        assertEquals(expected, tbt.maxProfitDP2(prices), "DP2 (O(1) space)");
        assertEquals(expected, tbt.maxProfitDP1(prices), "DP1 (O(n) space)");
    }

    @Test
    void example1() {
        assertBoth(3, new int[]{1, 2, 3, 0, 2});
    }

    @Test
    void singleElement() {
        assertBoth(0, new int[]{1});
    }

    @Test
    void twoElementsProfit() {
        assertBoth(1, new int[]{1, 2});
    }

    @Test
    void twoElementsNoProfit() {
        assertBoth(0, new int[]{2, 1});
    }

    @Test
    void decreasingPrices() {
        assertBoth(0, new int[]{5, 4, 3, 2, 1});
    }

    @Test
    void allSame() {
        assertBoth(0, new int[]{3, 3, 3, 3});
    }

    @Test
    void increasingWithCooldown() {
        assertBoth(3, new int[]{1, 2, 3, 4});
    }

    @Test
    void longerSequence() {
        assertBoth(6, new int[]{1, 2, 3, 0, 2, 4, 0, 3});
    }

    @Test
    void skipMiddleDip() {
        assertBoth(6, new int[]{1, 4, 2, 7});
    }

    @Test
    void alternating() {
        assertBoth(2, new int[]{1, 3, 1, 3, 1});
    }

    @Test
    void largeGap() {
        assertBoth(1000, new int[]{0, 1000});
    }

    @Test
    void sellEarly() {
        assertBoth(4, new int[]{1, 5, 0});
    }
}
