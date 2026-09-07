package bit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CountingBitsTest {

    CountingBits tbt;

    @BeforeEach
    void setUp() {
        tbt = new CountingBits();
    }

    @Test
    void testZero() {
        int[] expected = {0};
        assertAll(0, expected);
    }

    @Test
    void testOne() {
        int[] expected = {0, 1};
        assertAll(1, expected);
    }

    @Test
    void testTwo() {
        int[] expected = {0, 1, 1};
        assertAll(2, expected);
    }

    @Test
    void testFive() {
        int[] expected = {0, 1, 1, 2, 1, 2};
        assertAll(5, expected);
    }

    @Test
    void testPowerOfTwo() {
        // n=8: 0,1,1,2,1,2,2,3,1
        int[] expected = {0, 1, 1, 2, 1, 2, 2, 3, 1};
        assertAll(8, expected);
    }

    @Test
    void testAllBitsSet() {
        // n=15: 0..15, 15=1111 has 4 bits
        int[] expected = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
        assertAll(15, expected);
    }

    @Test
    void testLarge() {
        int n = 100_000;
        // use bitCount as reference
        int[] expected = new int[n + 1];
        for (int i = 0; i <= n; i++) expected[i] = Integer.bitCount(i);
        assertAll(n, expected);
    }

    private void assertAll(int n, int[] expected) {
        assertArrayEquals(expected, tbt.countBits(n), "countBits (O(n log n))");
        assertArrayEquals(expected, tbt.countBits1(n), "countBits1 (bitCount)");
        assertArrayEquals(expected, tbt.countBits2(n), "countBits2 (DP)");
    }
}
