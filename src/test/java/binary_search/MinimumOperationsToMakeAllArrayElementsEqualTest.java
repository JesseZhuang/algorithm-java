package binary_search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumOperationsToMakeAllArrayElementsEqualTest {

    @Test
    void returnsOperationsForFirstExample() {
        assertEquals(List.of(14L, 10L), MinimumOperationsToMakeAllArrayElementsEqual.minOperations(
                new int[]{3, 1, 6, 8}, new int[]{1, 5}));
    }

    @Test
    void returnsOperationsForSecondExample() {
        assertEquals(List.of(20L), MinimumOperationsToMakeAllArrayElementsEqual.minOperations(
                new int[]{2, 9, 6, 3}, new int[]{10}));
    }

    @Test
    void handlesQueriesBelowAtAndAboveArrayValues() {
        assertEquals(List.of(9L, 5L, 4L, 12L), MinimumOperationsToMakeAllArrayElementsEqual.minOperations(
                new int[]{2, 4, 6}, new int[]{1, 3, 4, 8}));
    }

    @Test
    void handlesDuplicatesAndPreservesQueryOrder() {
        assertEquals(List.of(6L, 6L, 8L, 10L), MinimumOperationsToMakeAllArrayElementsEqual.minOperations(
                new int[]{5, 1, 5, 3}, new int[]{4, 5, 2, 6}));
    }

    @Test
    void computesLargeTotalsUsingLong() {
        assertEquals(List.of(2_999_999_997L), MinimumOperationsToMakeAllArrayElementsEqual.minOperations(
                new int[]{1_000_000_000, 1_000_000_000, 1_000_000_000}, new int[]{1}));
    }
}
