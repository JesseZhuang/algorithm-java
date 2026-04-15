package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubarraySumEqualsKTest {
    private final SubarraySumEqualsK solver = new SubarraySumEqualsK();

    @Test void basic() {
        assertEquals(2, solver.subarraySum(new int[]{1, 1, 1}, 2));
        assertEquals(2, solver.subarraySumBruteForce(new int[]{1, 1, 1}, 2));
    }

    @Test void twoSubarrays() {
        assertEquals(2, solver.subarraySum(new int[]{1, 2, 3}, 3));
        assertEquals(2, solver.subarraySumBruteForce(new int[]{1, 2, 3}, 3));
    }

    @Test void singleMatch() {
        assertEquals(1, solver.subarraySum(new int[]{1}, 1));
        assertEquals(1, solver.subarraySumBruteForce(new int[]{1}, 1));
    }

    @Test void singleNoMatch() {
        assertEquals(0, solver.subarraySum(new int[]{1}, 0));
        assertEquals(0, solver.subarraySumBruteForce(new int[]{1}, 0));
    }

    @Test void negativeNumbers() {
        assertEquals(1, solver.subarraySum(new int[]{-1, -1, 1}, 0));
        assertEquals(1, solver.subarraySumBruteForce(new int[]{-1, -1, 1}, 0));
    }

    @Test void allZeros() {
        assertEquals(6, solver.subarraySum(new int[]{0, 0, 0}, 0));
        assertEquals(6, solver.subarraySumBruteForce(new int[]{0, 0, 0}, 0));
    }

    @Test void mixedSigns() {
        assertEquals(2, solver.subarraySum(new int[]{1, -1, 0}, -1));
        assertEquals(2, solver.subarraySumBruteForce(new int[]{1, -1, 0}, -1));
    }

    @Test void alternating() {
        assertEquals(4, solver.subarraySum(new int[]{1, -1, 1, -1}, 0));
        assertEquals(4, solver.subarraySumBruteForce(new int[]{1, -1, 1, -1}, 0));
    }

    @Test void entireArray() {
        assertEquals(1, solver.subarraySum(new int[]{1, 2, 3, 4, 5}, 15));
        assertEquals(1, solver.subarraySumBruteForce(new int[]{1, 2, 3, 4, 5}, 15));
    }

    @Test void noMatch() {
        assertEquals(0, solver.subarraySum(new int[]{1, 2, 3}, 7));
        assertEquals(0, solver.subarraySumBruteForce(new int[]{1, 2, 3}, 7));
    }
}
