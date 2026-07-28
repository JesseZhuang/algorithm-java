package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstMissingPosTest {

    private final FirstMissingPos solution = new FirstMissingPos();

    @Test
    void testExample1() {
        assertEquals(3, solution.firstMissingPositive(new int[]{1, 2, 0}));
    }

    @Test
    void testExample2() {
        assertEquals(2, solution.firstMissingPositive(new int[]{3, 4, -1, 1}));
    }

    @Test
    void testExample3() {
        assertEquals(1, solution.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }

    @Test
    void testSingleElementPresent() {
        assertEquals(2, solution.firstMissingPositive(new int[]{1}));
    }

    @Test
    void testSingleElementMissing() {
        assertEquals(1, solution.firstMissingPositive(new int[]{2}));
    }

    @Test
    void testConsecutiveSequence() {
        assertEquals(6, solution.firstMissingPositive(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testDuplicates() {
        assertEquals(2, solution.firstMissingPositive(new int[]{1, 1, 1, 1}));
    }

    @Test
    void testAllNegative() {
        assertEquals(1, solution.firstMissingPositive(new int[]{-1, -2, -3}));
    }

    @Test
    void testExtremeValues() {
        assertEquals(3, solution.firstMissingPositive(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 2}));
    }
}
