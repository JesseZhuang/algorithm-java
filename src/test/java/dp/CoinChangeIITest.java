package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinChangeIITest {

    @Test
    void testChange() {
        assertEquals(4, CoinChangeII.change(5, new int[]{1, 2, 5}));
        assertEquals(0, CoinChangeII.change(3, new int[]{2}));
        assertEquals(1, CoinChangeII.change(10, new int[]{10}));
        assertEquals(1, CoinChangeII.change(0, new int[]{1, 2, 5}));
        assertEquals(1, CoinChangeII.change(7, new int[]{7}));
        assertEquals(0, CoinChangeII.change(7, new int[]{3}));
        assertEquals(242, CoinChangeII.change(100, new int[]{1, 5, 10, 25}));
    }

    @Test
    void testChange2D() {
        assertEquals(4, CoinChangeII.change2D(5, new int[]{1, 2, 5}));
        assertEquals(0, CoinChangeII.change2D(3, new int[]{2}));
        assertEquals(1, CoinChangeII.change2D(10, new int[]{10}));
        assertEquals(1, CoinChangeII.change2D(0, new int[]{1, 2, 5}));
        assertEquals(1, CoinChangeII.change2D(7, new int[]{7}));
        assertEquals(0, CoinChangeII.change2D(7, new int[]{3}));
        assertEquals(242, CoinChangeII.change2D(100, new int[]{1, 5, 10, 25}));
    }
}
