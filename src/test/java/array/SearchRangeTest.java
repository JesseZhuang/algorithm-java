package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SearchRangeTest {

    @Test
    void testSolution() {
        // standard case with duplicates
        assertArrayEquals(new int[]{3, 4}, SearchRange.Solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        // target not found
        assertArrayEquals(new int[]{-1, -1}, SearchRange.Solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        // empty array
        assertArrayEquals(new int[]{-1, -1}, SearchRange.Solution.searchRange(new int[]{}, 0));
        // single element match
        assertArrayEquals(new int[]{0, 0}, SearchRange.Solution.searchRange(new int[]{1}, 1));
        // single element no match
        assertArrayEquals(new int[]{-1, -1}, SearchRange.Solution.searchRange(new int[]{1}, 0));
        // all elements are target
        assertArrayEquals(new int[]{0, 4}, SearchRange.Solution.searchRange(new int[]{5, 5, 5, 5, 5}, 5));
        // target at beginning
        assertArrayEquals(new int[]{0, 1}, SearchRange.Solution.searchRange(new int[]{1, 1, 2, 3, 4}, 1));
        // target at end
        assertArrayEquals(new int[]{3, 4}, SearchRange.Solution.searchRange(new int[]{1, 2, 3, 4, 4}, 4));
        // single occurrence
        assertArrayEquals(new int[]{2, 2}, SearchRange.Solution.searchRange(new int[]{1, 2, 3, 4, 5}, 3));
        // two elements both match
        assertArrayEquals(new int[]{0, 1}, SearchRange.Solution.searchRange(new int[]{2, 2}, 2));
    }

    @Test
    void testSolution2() {
        // standard case with duplicates
        assertArrayEquals(new int[]{3, 4}, SearchRange.Solution2.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        // target not found
        assertArrayEquals(new int[]{-1, -1}, SearchRange.Solution2.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        // empty array
        assertArrayEquals(new int[]{-1, -1}, SearchRange.Solution2.searchRange(new int[]{}, 0));
        // single element match
        assertArrayEquals(new int[]{0, 0}, SearchRange.Solution2.searchRange(new int[]{1}, 1));
        // single element no match
        assertArrayEquals(new int[]{-1, -1}, SearchRange.Solution2.searchRange(new int[]{1}, 0));
        // all elements are target
        assertArrayEquals(new int[]{0, 4}, SearchRange.Solution2.searchRange(new int[]{5, 5, 5, 5, 5}, 5));
        // target at beginning
        assertArrayEquals(new int[]{0, 1}, SearchRange.Solution2.searchRange(new int[]{1, 1, 2, 3, 4}, 1));
        // target at end
        assertArrayEquals(new int[]{3, 4}, SearchRange.Solution2.searchRange(new int[]{1, 2, 3, 4, 4}, 4));
        // single occurrence
        assertArrayEquals(new int[]{2, 2}, SearchRange.Solution2.searchRange(new int[]{1, 2, 3, 4, 5}, 3));
        // two elements both match
        assertArrayEquals(new int[]{0, 1}, SearchRange.Solution2.searchRange(new int[]{2, 2}, 2));
    }
}
