package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueNumberOfOccurrencesTest {
    @Test
    void officialExamples() {
        assertBothMethods(new int[]{1, 2, 2, 1, 1, 3}, true);
        assertBothMethods(new int[]{1, 2}, false);
        assertBothMethods(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}, true);
    }

    @Test
    void singletonAndAllEqualValues() {
        assertBothMethods(new int[]{42}, true);
        assertBothMethods(new int[]{7, 7, 7}, true);
    }

    @Test
    void repeatedFrequenciesAreNotUnique() {
        assertBothMethods(new int[]{1, 1, 2, 2}, false);
    }

    @Test
    void inclusiveValueBoundsWithUniqueFrequencies() {
        assertBothMethods(new int[]{-1000, 1000, 1000}, true);
    }

    @Test
    void inclusiveValueBoundsWithCollidingFrequencies() {
        assertBothMethods(new int[]{-1000, -1000, 1000, 1000}, false);
    }

    private void assertBothMethods(int[] numbers, boolean expected) {
        assertEquals(expected, UniqueNumberOfOccurrences.uniqueOccurrences(numbers));
        assertEquals(expected, UniqueNumberOfOccurrences.uniqueOccurrencesByFrequencyArray(numbers));
    }
}
