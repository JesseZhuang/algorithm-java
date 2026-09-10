package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxNumberOfKSumPairsTest {

    private void assertBoth(int expected, int[] nums, int k) {
        assertEquals(expected, MaxNumberOfKSumPairs.maxOperations(nums, k),
                "HashMap approach");
        assertEquals(expected, MaxNumberOfKSumPairs.maxOperationsTwoPointers(nums.clone(), k),
                "Two-pointer approach");
    }

    @Test
    void example1() {
        assertBoth(2, new int[]{1, 2, 3, 4}, 5);
    }

    @Test
    void example2() {
        assertBoth(1, new int[]{3, 1, 3, 4, 3}, 6);
    }

    @Test
    void noPairs() {
        assertBoth(0, new int[]{1, 2, 3}, 10);
    }

    @Test
    void allPairs() {
        assertBoth(2, new int[]{1, 3, 1, 3}, 4);
    }

    @Test
    void duplicates() {
        assertBoth(2, new int[]{2, 2, 2, 2}, 4);
    }

    @Test
    void singleElement() {
        assertBoth(0, new int[]{5}, 5);
    }

    @Test
    void oddCount() {
        assertBoth(1, new int[]{2, 2, 2}, 4);
    }
}
