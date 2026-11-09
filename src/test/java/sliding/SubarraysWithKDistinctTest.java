package sliding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubarraysWithKDistinctTest {

    @Test
    void testExample1() {
        assertEquals(7, SubarraysWithKDistinct.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }

    @Test
    void testExample2() {
        assertEquals(3, SubarraysWithKDistinct.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
    }

    @Test
    void testOneDistinctValue() {
        assertEquals(3, SubarraysWithKDistinct.subarraysWithKDistinct(new int[]{1, 2, 1}, 1));
    }

    @Test
    void testKEqualsNumberOfDistinctValues() {
        assertEquals(2, SubarraysWithKDistinct.subarraysWithKDistinct(new int[]{1, 2, 1, 3}, 3));
    }

    @Test
    void testKGreaterThanNumberOfDistinctValues() {
        assertEquals(0, SubarraysWithKDistinct.subarraysWithKDistinct(new int[]{1, 2, 1, 2}, 3));
    }

    @Test
    void testShrinksWindowAfterLastOccurrenceIsRemoved() {
        assertEquals(8, SubarraysWithKDistinct.subarraysWithKDistinct(new int[]{1, 1, 2, 2, 3, 3}, 2));
    }

    @Test
    void testMaximumLengthWithOneDistinctValue() {
        int[] nums = new int[20_000];
        java.util.Arrays.fill(nums, 1);

        assertEquals(200_010_000, SubarraysWithKDistinct.subarraysWithKDistinct(nums, 1));
    }
}
