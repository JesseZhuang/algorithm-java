package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FindPeakElementTest {

    private final FindPeakElement sol = new FindPeakElement();

    private void assertValidPeak(int[] nums, int idx) {
        assertTrue(idx >= 0 && idx < nums.length, "index out of bounds: " + idx);
        if (idx > 0) assertTrue(nums[idx] > nums[idx - 1],
                "nums[" + idx + "]=" + nums[idx] + " not greater than left neighbor " + nums[idx - 1]);
        if (idx < nums.length - 1) assertTrue(nums[idx] > nums[idx + 1],
                "nums[" + idx + "]=" + nums[idx] + " not greater than right neighbor " + nums[idx + 1]);
    }

    @Test
    void testSingleElement() {
        int[] nums = {1};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }

    @Test
    void testTwoElementsAscending() {
        int[] nums = {1, 2};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }

    @Test
    void testTwoElementsDescending() {
        int[] nums = {2, 1};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }

    @Test
    void testExample1() {
        int[] nums = {1, 2, 3, 1};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }

    @Test
    void testExample2() {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }

    @Test
    void testAscending() {
        int[] nums = {1, 2, 3, 4, 5};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }

    @Test
    void testDescending() {
        int[] nums = {5, 4, 3, 2, 1};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }

    @Test
    void testMultiplePeaks() {
        int[] nums = {1, 3, 2, 4, 1};
        assertValidPeak(nums, sol.findPeakElementRecur(nums));
        assertValidPeak(nums, sol.findPeakElementIter(nums));
    }
}
