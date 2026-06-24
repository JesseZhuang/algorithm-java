package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TargetSumTest {

    @Test
    void testSubsetSumDP() {
        assertEquals(5, TargetSum.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        assertEquals(1, TargetSum.findTargetSumWays(new int[]{1}, 1));
        assertEquals(2, TargetSum.findTargetSumWays(new int[]{1, 0}, 1));
        assertEquals(32, TargetSum.findTargetSumWays(new int[]{0, 0, 0, 0, 0}, 0));
        assertEquals(0, TargetSum.findTargetSumWays(new int[]{1, 2, 3}, 7));
        assertEquals(5, TargetSum.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, -3));
        assertEquals(2, TargetSum.findTargetSumWays(new int[]{0}, 0));
        assertEquals(2, TargetSum.findTargetSumWays(new int[]{1, 2, 1}, 0));
    }

    @Test
    void testDFSMemo() {
        assertEquals(5, TargetSum.findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, 3));
        assertEquals(1, TargetSum.findTargetSumWays2(new int[]{1}, 1));
        assertEquals(2, TargetSum.findTargetSumWays2(new int[]{1, 0}, 1));
        assertEquals(32, TargetSum.findTargetSumWays2(new int[]{0, 0, 0, 0, 0}, 0));
        assertEquals(0, TargetSum.findTargetSumWays2(new int[]{1, 2, 3}, 7));
        assertEquals(5, TargetSum.findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, -3));
        assertEquals(2, TargetSum.findTargetSumWays2(new int[]{0}, 0));
        assertEquals(2, TargetSum.findTargetSumWays2(new int[]{1, 2, 1}, 0));
    }
}
