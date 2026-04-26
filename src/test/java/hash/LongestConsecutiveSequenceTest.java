package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestConsecutiveSequenceTest {

    private void verify(int[] nums, int expected) {
        assertEquals(expected, LongestConsecutiveSequence.longestConsecutiveSet(nums));
        assertEquals(expected, LongestConsecutiveSequence.longestConsecutiveUF(nums));
    }

    @Test void example1() {
        verify(new int[]{100, 4, 200, 1, 3, 2}, 4); // [1, 2, 3, 4]
    }

    @Test void example2() {
        verify(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9); // [0, 1, 2, 3, 4, 5, 6, 7, 8]
    }

    @Test void empty() {
        verify(new int[]{}, 0);
    }

    @Test void single() {
        verify(new int[]{1}, 1);
    }

    @Test void duplicates() {
        verify(new int[]{1, 2, 0, 1}, 3); // [0, 1, 2]
    }

    @Test void negative() {
        verify(new int[]{-1, -2, -3, 0, 1, 2}, 6); // [-3, -2, -1, 0, 1, 2]
    }

    @Test void noConsecutive() {
        verify(new int[]{1, 3, 5, 7, 9}, 1);
    }

    @Test void allSame() {
        verify(new int[]{5, 5, 5, 5}, 1);
    }

    @Test void twoSequences() {
        verify(new int[]{1, 2, 3, 100, 101, 102, 103}, 4); // [100, 101, 102, 103]
    }
}
