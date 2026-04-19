package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchRotatedSortedArrayTest {

    @Test
    void testSolution() {
        // standard rotated arrays
        assertEquals(4, SearchRotatedSortedArray.Solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        assertEquals(1, SearchRotatedSortedArray.Solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 5));
        // not found
        assertEquals(-1, SearchRotatedSortedArray.Solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        // single element
        assertEquals(0, SearchRotatedSortedArray.Solution.search(new int[]{1}, 1));
        assertEquals(-1, SearchRotatedSortedArray.Solution.search(new int[]{1}, 0));
        // two elements
        assertEquals(0, SearchRotatedSortedArray.Solution.search(new int[]{3, 1}, 3));
        assertEquals(1, SearchRotatedSortedArray.Solution.search(new int[]{3, 1}, 1));
        assertEquals(-1, SearchRotatedSortedArray.Solution.search(new int[]{3, 1}, 2));
        // no rotation
        assertEquals(2, SearchRotatedSortedArray.Solution.search(new int[]{1, 2, 3, 4, 5}, 3));
        // target at boundaries
        assertEquals(0, SearchRotatedSortedArray.Solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4));
        assertEquals(6, SearchRotatedSortedArray.Solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2));
    }

    @Test
    void testSolution2() {
        // standard rotated arrays
        assertEquals(4, SearchRotatedSortedArray.Solution2.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        assertEquals(1, SearchRotatedSortedArray.Solution2.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 5));
        // not found
        assertEquals(-1, SearchRotatedSortedArray.Solution2.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        // single element
        assertEquals(0, SearchRotatedSortedArray.Solution2.search(new int[]{1}, 1));
        assertEquals(-1, SearchRotatedSortedArray.Solution2.search(new int[]{1}, 0));
        // two elements
        assertEquals(0, SearchRotatedSortedArray.Solution2.search(new int[]{3, 1}, 3));
        assertEquals(1, SearchRotatedSortedArray.Solution2.search(new int[]{3, 1}, 1));
        assertEquals(-1, SearchRotatedSortedArray.Solution2.search(new int[]{3, 1}, 2));
        // no rotation
        assertEquals(2, SearchRotatedSortedArray.Solution2.search(new int[]{1, 2, 3, 4, 5}, 3));
        // target at boundaries
        assertEquals(0, SearchRotatedSortedArray.Solution2.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4));
        assertEquals(6, SearchRotatedSortedArray.Solution2.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2));
    }
}
