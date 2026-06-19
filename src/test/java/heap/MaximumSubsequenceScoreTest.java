package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumSubsequenceScoreTest {

    @Test
    void testExample1() {
        assertEquals(12, MaximumSubsequenceScore.maxScore(
                new int[]{1, 3, 3, 2}, new int[]{2, 1, 3, 4}, 3));
    }

    @Test
    void testExample2() {
        assertEquals(30, MaximumSubsequenceScore.maxScore(
                new int[]{4, 2, 3, 1, 1}, new int[]{7, 5, 10, 9, 6}, 1));
    }

    @Test
    void testAllOnes() {
        assertEquals(2, MaximumSubsequenceScore.maxScore(
                new int[]{1, 1, 1}, new int[]{1, 1, 1}, 2));
    }

    @Test
    void testAllSelected() {
        assertEquals(10, MaximumSubsequenceScore.maxScore(
                new int[]{2, 3, 5}, new int[]{4, 2, 1}, 3));
    }

    @Test
    void testSingleElement() {
        assertEquals(30, MaximumSubsequenceScore.maxScore(
                new int[]{5}, new int[]{6}, 1));
    }

    @Test
    void testKEqualsTwo() {
        assertEquals(100, MaximumSubsequenceScore.maxScore(
                new int[]{10, 20, 30}, new int[]{1, 2, 3}, 2));
    }
}
