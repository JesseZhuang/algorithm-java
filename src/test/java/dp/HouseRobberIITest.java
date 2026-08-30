package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseRobberIITest {

    private final HouseRobberII solution = new HouseRobberII();

    @Test
    void testExample1() {
        assertEquals(3, solution.rob(new int[]{2, 3, 2}));
    }

    @Test
    void testExample2() {
        assertEquals(4, solution.rob(new int[]{1, 2, 3, 1}));
    }

    @Test
    void testExample3() {
        assertEquals(3, solution.rob(new int[]{1, 2, 3}));
    }

    @Test
    void testSingleElement() {
        assertEquals(5, solution.rob(new int[]{5}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, solution.rob(new int[]{1, 2}));
    }

    @Test
    void testTwoEqualElements() {
        assertEquals(3, solution.rob(new int[]{3, 3}));
    }

    @Test
    void testFourEqualElements() {
        assertEquals(6, solution.rob(new int[]{3, 3, 3, 3}));
    }

    @Test
    void testAlternatingLargeSmall() {
        assertEquals(2000, solution.rob(new int[]{1000, 1, 1000, 1}));
    }

    @Test
    void testMixedValues() {
        assertEquals(340, solution.rob(new int[]{200, 3, 140, 20, 10}));
    }

    @Test
    void testAllZeros() {
        assertEquals(0, solution.rob(new int[]{0, 0, 0, 0}));
    }

    @Test
    void testFiveElements() {
        assertEquals(8, solution.rob(new int[]{1, 2, 3, 4, 5}));
    }
}
