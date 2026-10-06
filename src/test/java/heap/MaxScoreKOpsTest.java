package heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxScoreKOpsTest {

    private final MaxScoreKOps.Solution solution = new MaxScoreKOps.Solution();

    @Test
    void scoresFirstExample() {
        assertScoreAndUnchanged(50L, new int[]{10, 10, 10, 10, 10}, 5);
    }

    @Test
    void scoresSecondExample() {
        assertScoreAndUnchanged(17L, new int[]{1, 10, 3, 3, 3}, 3);
    }

    @Test
    void dividesExactlyWhenValueIsDivisibleByThree() {
        assertScoreAndUnchanged(5L, new int[]{3}, 3);
    }

    @Test
    void roundsUpWhenRemainderIsOne() {
        assertScoreAndUnchanged(7L, new int[]{4}, 3);
    }

    @Test
    void roundsUpWhenRemainderIsTwo() {
        assertScoreAndUnchanged(8L, new int[]{5}, 3);
    }

    @Test
    void performsEveryOperationAfterReachingOne() {
        assertScoreAndUnchanged(100_000L, new int[]{1}, 100_000);
    }

    @Test
    void reevaluatesLargestAfterEachOperation() {
        assertScoreAndUnchanged(23L, new int[]{9, 8}, 4);
    }

    @Test
    void accumulatesBeyondIntegerRange() {
        assertScoreAndUnchanged(3_000_000_000L,
                new int[]{1_000_000_000, 1_000_000_000, 1_000_000_000}, 3);
    }

    @Test
    void handlesMaximumInputAndOperationCounts() {
        int[] nums = new int[100_000];
        Arrays.fill(nums, 1_000_000_000);

        assertScoreAndUnchanged(100_000_000_000_000L, nums, 100_000);
    }

    @Test
    void selectsLargestForOneOperationWithoutReorderingInput() {
        assertScoreAndUnchanged(10L, new int[]{3, 10, 1, 5}, 1);
    }

    @Test
    void matchesExhaustiveOracleForSmallInputs() {
        for (int length = 1; length <= 3; length++) {
            int combinations = 1;
            for (int index = 0; index < length; index++) {
                combinations *= 6;
            }
            for (int encoding = 0; encoding < combinations; encoding++) {
                int[] nums = new int[length];
                int remaining = encoding;
                for (int index = 0; index < length; index++) {
                    nums[index] = remaining % 6 + 1;
                    remaining /= 6;
                }
                for (int operations = 1; operations <= 4; operations++) {
                    long expected = exhaustiveMaxScore(nums.clone(), operations);
                    assertScoreAndUnchanged(expected, nums, operations);
                }
            }
        }
    }

    private void assertScoreAndUnchanged(long expected, int[] nums, int operations) {
        int[] original = nums.clone();

        assertEquals(expected, solution.maxKelements(nums, operations),
                () -> "nums=" + Arrays.toString(original) + ", k=" + operations);
        assertArrayEquals(original, nums, "Input array must remain unchanged");
    }

    private long exhaustiveMaxScore(int[] nums, int operations) {
        if (operations == 0) {
            return 0L;
        }
        long maximum = 0L;
        for (int index = 0; index < nums.length; index++) {
            int original = nums[index];
            nums[index] = original / 3 + (original % 3 == 0 ? 0 : 1);
            long score = original + exhaustiveMaxScore(nums, operations - 1);
            nums[index] = original;
            maximum = Math.max(maximum, score);
        }
        return maximum;
    }
}
