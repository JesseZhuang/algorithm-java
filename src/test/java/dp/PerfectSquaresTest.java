package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerfectSquaresTest {

    @Test
    void testNumSquares1() {
        assertEquals(3, PerfectSquares.numSquares1(12));
        assertEquals(2, PerfectSquares.numSquares1(13));
        assertEquals(1, PerfectSquares.numSquares1(1));
        assertEquals(1, PerfectSquares.numSquares1(4));
        assertEquals(4, PerfectSquares.numSquares1(7));
        assertEquals(4, PerfectSquares.numSquares1(15));
        assertEquals(1, PerfectSquares.numSquares1(100));
        assertEquals(2, PerfectSquares.numSquares1(2));
        assertEquals(3, PerfectSquares.numSquares1(3));
        assertEquals(1, PerfectSquares.numSquares1(10000));
        assertEquals(4, PerfectSquares.numSquares1(9999));
    }

    @Test
    void testNumSquares2() {
        assertEquals(3, PerfectSquares.numSquares2(12));
        assertEquals(2, PerfectSquares.numSquares2(13));
        assertEquals(1, PerfectSquares.numSquares2(1));
        assertEquals(1, PerfectSquares.numSquares2(4));
        assertEquals(4, PerfectSquares.numSquares2(7));
        assertEquals(4, PerfectSquares.numSquares2(15));
        assertEquals(1, PerfectSquares.numSquares2(100));
        assertEquals(2, PerfectSquares.numSquares2(2));
        assertEquals(3, PerfectSquares.numSquares2(3));
        assertEquals(1, PerfectSquares.numSquares2(10000));
        assertEquals(4, PerfectSquares.numSquares2(9999));
    }
}
