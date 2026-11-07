package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfUniqueElementsTest {

    @Test
    void sumsSingleton() {
        int[] nums = {7};

        assertEquals(7, SumOfUniqueElements.sumOfUnique(nums));
        assertEquals(7, SumOfUniqueElements.sumOfUniqueBySorting(nums));
    }

    @Test
    void sumsAllDistinctValues() {
        int[] nums = {1, 2, 3, 4};

        assertEquals(10, SumOfUniqueElements.sumOfUnique(nums));
        assertEquals(10, SumOfUniqueElements.sumOfUniqueBySorting(nums));
    }

    @Test
    void returnsZeroWhenEveryValueRepeats() {
        int[] nums = {5, 5, 9, 9, 9};

        assertEquals(0, SumOfUniqueElements.sumOfUnique(nums));
        assertEquals(0, SumOfUniqueElements.sumOfUniqueBySorting(nums));
    }

    @Test
    void sumsOnlyValuesThatOccurOnce() {
        int[] nums = {1, 2, 2, 3, 3, 3, 4, 5, 5, 5, 5};

        assertEquals(5, SumOfUniqueElements.sumOfUnique(nums));
        assertEquals(5, SumOfUniqueElements.sumOfUniqueBySorting(nums));
    }

    @Test
    void sortingApproachLeavesInputUnchanged() {
        int[] nums = {3, 1, 2, 1};
        int[] original = nums.clone();

        SumOfUniqueElements.sumOfUniqueBySorting(nums);

        assertArrayEquals(original, nums);
    }
}
