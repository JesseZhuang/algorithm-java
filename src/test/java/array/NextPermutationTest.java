package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NextPermutationTest {

    @Test
    void ascending() {
        int[] nums = {1, 2, 3};
        NextPermutation.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 3, 2}, nums);
    }

    @Test
    void descending() {
        int[] nums = {3, 2, 1};
        NextPermutation.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void duplicates() {
        int[] nums = {1, 1, 5};
        NextPermutation.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 5, 1}, nums);
    }

    @Test
    void singleElement() {
        int[] nums = {1};
        NextPermutation.nextPermutation(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void twoElementsAscending() {
        int[] nums = {1, 2};
        NextPermutation.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1}, nums);
    }

    @Test
    void duplicatesComplex() {
        int[] nums = {1, 2, 2};
        NextPermutation.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1, 2}, nums);
    }

    @Test
    void middlePivot() {
        int[] nums = {2, 3, 1, 3, 3};
        NextPermutation.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 3, 3, 1, 3}, nums);
    }
}
