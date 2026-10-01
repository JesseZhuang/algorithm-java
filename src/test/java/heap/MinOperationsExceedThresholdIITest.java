package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinOperationsExceedThresholdIITest {

    @Test
    void performsOperationsForOfficialExampleOne() {
        assertEquals(2, MinOperationsExceedThresholdII.minOperations(
                new int[]{2, 11, 10, 1, 3}, 10));
    }

    @Test
    void performsOperationsForOfficialExampleTwo() {
        assertEquals(4, MinOperationsExceedThresholdII.minOperations(
                new int[]{1, 1, 2, 4, 9}, 20));
    }

    @Test
    void returnsZeroWhenEveryValueAlreadyMeetsThreshold() {
        assertEquals(0, MinOperationsExceedThresholdII.minOperations(
                new int[]{10, 15, 20}, 10));
    }

    @Test
    void stopsWhenOneOperationExactlyReachesThreshold() {
        assertEquals(1, MinOperationsExceedThresholdII.minOperations(
                new int[]{1, 2, 10}, 4));
    }

    @Test
    void handlesIntermediateValuesBeyondIntegerRange() {
        assertEquals(3, MinOperationsExceedThresholdII.minOperations(
                new int[]{1, 1, 900_000_000, 900_000_000}, 1_000_000_000));
    }
}
