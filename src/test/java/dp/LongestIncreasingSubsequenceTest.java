package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestIncreasingSubsequenceTest {

    @Test
    void testBinarySearch() {
        assertEquals(4, LongestIncreasingSubsequence.lengthOfLISBinarySearch(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(4, LongestIncreasingSubsequence.lengthOfLISBinarySearch(new int[]{0, 1, 0, 3, 2, 3}));
        assertEquals(1, LongestIncreasingSubsequence.lengthOfLISBinarySearch(new int[]{7, 7, 7, 7, 7, 7, 7}));
        assertEquals(1, LongestIncreasingSubsequence.lengthOfLISBinarySearch(new int[]{1}));
        assertEquals(6, LongestIncreasingSubsequence.lengthOfLISBinarySearch(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    @Test
    void testDP() {
        assertEquals(4, LongestIncreasingSubsequence.lengthOfLISDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(4, LongestIncreasingSubsequence.lengthOfLISDP(new int[]{0, 1, 0, 3, 2, 3}));
        assertEquals(1, LongestIncreasingSubsequence.lengthOfLISDP(new int[]{7, 7, 7, 7, 7, 7, 7}));
        assertEquals(1, LongestIncreasingSubsequence.lengthOfLISDP(new int[]{1}));
        assertEquals(6, LongestIncreasingSubsequence.lengthOfLISDP(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }
}
