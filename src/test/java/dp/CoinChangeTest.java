package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinChangeTest {
    CoinChange tbt;

    @BeforeEach
    void setUp() {
        tbt = new CoinChange();
    }

    @Test
    void testDP() {
        assertEquals(3, tbt.coinChangeDP1(new int[]{1, 2, 5}, 11));
        assertEquals(-1, tbt.coinChangeDP1(new int[]{2}, 3));
        assertEquals(0, tbt.coinChangeDP1(new int[]{1}, 0));
        assertEquals(2, tbt.coinChangeDP1(new int[]{3}, 6));
        assertEquals(-1, tbt.coinChangeDP1(new int[]{3}, 7));
        assertEquals(2, tbt.coinChangeDP1(new int[]{1, 5, 10, 25}, 30));
        assertEquals(3, tbt.coinChangeDP1(new int[]{1, 5, 6}, 15));
        assertEquals(-1, tbt.coinChangeDP1(new int[]{5, 10}, 3));
    }

    @Test
    void testBFS() {
        assertEquals(3, tbt.coinChangeBFS(new int[]{1, 2, 5}, 11));
        assertEquals(-1, tbt.coinChangeBFS(new int[]{2}, 3));
        assertEquals(0, tbt.coinChangeBFS(new int[]{1}, 0));
        assertEquals(2, tbt.coinChangeBFS(new int[]{3}, 6));
        assertEquals(-1, tbt.coinChangeBFS(new int[]{3}, 7));
        assertEquals(2, tbt.coinChangeBFS(new int[]{1, 5, 10, 25}, 30));
        assertEquals(3, tbt.coinChangeBFS(new int[]{1, 5, 6}, 15));
        assertEquals(-1, tbt.coinChangeBFS(new int[]{5, 10}, 3));
    }
}
