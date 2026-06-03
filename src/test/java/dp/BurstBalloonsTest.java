package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BurstBalloonsTest {

    private final BurstBalloons.Solution solution = new BurstBalloons.Solution();

    @Test
    void testExample1() {
        assertEquals(167, solution.maxCoins(new int[]{3, 1, 5, 8}));
    }

    @Test
    void testExample2() {
        assertEquals(10, solution.maxCoins(new int[]{1, 5}));
    }

    @Test
    void testSingleBalloon() {
        assertEquals(3, solution.maxCoins(new int[]{3}));
    }

    @Test
    void testTwoSame() {
        assertEquals(6, solution.maxCoins(new int[]{2, 2}));
    }

    @Test
    void testAscending() {
        assertEquals(40, solution.maxCoins(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testDescending() {
        assertEquals(40, solution.maxCoins(new int[]{4, 3, 2, 1}));
    }

    @Test
    void testLargeValues() {
        assertEquals(1010100, solution.maxCoins(new int[]{100, 100, 100}));
    }

    @Test
    void testMinimalCase() {
        assertEquals(1, solution.maxCoins(new int[]{1}));
    }
}
