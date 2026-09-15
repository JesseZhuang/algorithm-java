package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubarraySumsDivisibleByKTest {
    private final SubarraySumsDivisibleByK solver = new SubarraySumsDivisibleByK();

    @Test void basic() {
        assertEquals(7, solver.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
        assertEquals(7, solver.subarraysDivByKBrute(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

    @Test void singleNoMatch() {
        assertEquals(0, solver.subarraysDivByK(new int[]{5}, 9));
        assertEquals(0, solver.subarraysDivByKBrute(new int[]{5}, 9));
    }

    @Test void allDivisible() {
        assertEquals(6, solver.subarraysDivByK(new int[]{5, 10, 15}, 5));
        assertEquals(6, solver.subarraysDivByKBrute(new int[]{5, 10, 15}, 5));
    }

    @Test void singleZero() {
        assertEquals(1, solver.subarraysDivByK(new int[]{0}, 1));
        assertEquals(1, solver.subarraysDivByKBrute(new int[]{0}, 1));
    }

    @Test void negativeNumbers() {
        assertEquals(2, solver.subarraysDivByK(new int[]{-1, 2, 9}, 2));
        assertEquals(2, solver.subarraysDivByKBrute(new int[]{-1, 2, 9}, 2));
    }

    @Test void allZeros() {
        assertEquals(6, solver.subarraysDivByK(new int[]{0, 0, 0}, 3));
        assertEquals(6, solver.subarraysDivByKBrute(new int[]{0, 0, 0}, 3));
    }

    @Test void noMatch() {
        assertEquals(0, solver.subarraysDivByK(new int[]{1, 2, 3}, 100));
        assertEquals(0, solver.subarraysDivByKBrute(new int[]{1, 2, 3}, 100));
    }

    @Test void kEqualsOne() {
        assertEquals(6, solver.subarraysDivByK(new int[]{1, 2, 3}, 1));
        assertEquals(6, solver.subarraysDivByKBrute(new int[]{1, 2, 3}, 1));
    }

    @Test void mixedSigns() {
        assertEquals(3, solver.subarraysDivByK(new int[]{-5, 1, 2, -3, 4}, 5));
        assertEquals(3, solver.subarraysDivByKBrute(new int[]{-5, 1, 2, -3, 4}, 5));
    }

    @Test void singleDivisible() {
        assertEquals(1, solver.subarraysDivByK(new int[]{6}, 3));
        assertEquals(1, solver.subarraysDivByKBrute(new int[]{6}, 3));
    }
}
