package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HIndexTest {

    @Test
    void returnsHIndexForExamples() {
        assertBothMethodsReturn(3, new int[]{3, 0, 6, 1, 5});
        assertBothMethodsReturn(1, new int[]{1, 3, 1});
    }

    @Test
    void returnsZeroWhenNoPaperHasCitations() {
        assertBothMethodsReturn(0, new int[]{0, 0, 0});
    }

    @Test
    void returnsNumberOfPapersWhenEveryPaperMeetsIt() {
        assertBothMethodsReturn(3, new int[]{3, 4, 5});
    }

    @Test
    void handlesOnePaper() {
        assertBothMethodsReturn(1, new int[]{10});
    }

    @Test
    void handlesCitationCountsAboveNumberOfPapers() {
        assertBothMethodsReturn(2, new int[]{100, 2, 3});
    }

    @Test
    void sortingAlternativeDoesNotMutateInput() {
        int[] citations = {3, 0, 6, 1, 5};
        int[] original = citations.clone();

        assertEquals(3, HIndex.hIndexSort(citations));

        assertArrayEquals(original, citations);
    }

    private void assertBothMethodsReturn(int expected, int[] citations) {
        assertEquals(expected, HIndex.hIndex(citations));
        assertEquals(expected, HIndex.hIndexSort(citations));
    }
}
