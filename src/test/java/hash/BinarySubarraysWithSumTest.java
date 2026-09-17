package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySubarraysWithSumTest {

    private void check(int expected, int[] nums, int goal) {
        assertEquals(expected, BinarySubarraysWithSum.numSubarraysWithSum(nums, goal));
        assertEquals(expected, BinarySubarraysWithSum.numSubarraysWithSum2(nums, goal));
    }

    @Test void example1() {
        check(4, new int[]{1, 0, 1, 0, 1}, 2);
    }

    @Test void example2() {
        check(15, new int[]{0, 0, 0, 0, 0}, 0);
    }

    @Test void singleOneGoalOne() {
        check(1, new int[]{1}, 1);
    }

    @Test void singleZeroGoalZero() {
        check(1, new int[]{0}, 0);
    }

    @Test void singleOneGoalZero() {
        check(0, new int[]{1}, 0);
    }

    @Test void allOnes() {
        check(3, new int[]{1, 1, 1, 1}, 2);
    }

    @Test void goalEqualsLength() {
        check(1, new int[]{1, 1, 1}, 3);
    }

    @Test void goalZeroWithMixed() {
        check(3, new int[]{1, 0, 0, 1}, 0);
    }

    @Test void leadingTrailingZeros() {
        check(9, new int[]{0, 0, 1, 0, 0}, 1);
    }

    @Test void noValidSubarray() {
        check(0, new int[]{0, 0, 0}, 1);
    }

    @Test void largeGoal() {
        check(0, new int[]{1, 0, 1}, 5);
    }
}
