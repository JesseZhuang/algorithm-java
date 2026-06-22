package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortColorsTest {

    @Test
    void leetcodeExample1() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    void leetcodeExample2() {
        int[] nums = {2, 0, 1};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{0, 1, 2}, nums);
    }

    @Test
    void singleElement() {
        int[] nums = {1};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void allSame() {
        int[] nums = {2, 2, 2, 2};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{2, 2, 2, 2}, nums);
    }

    @Test
    void alreadySorted() {
        int[] nums = {0, 0, 1, 1, 2, 2};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    void reverseSorted() {
        int[] nums = {2, 2, 1, 1, 0, 0};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    void twoElements() {
        int[] nums = {1, 0};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{0, 1}, nums);
    }

    @Test
    void missingOneColor() {
        int[] nums = {2, 0, 0, 2};
        SortColors.sortColors(nums);
        assertArrayEquals(new int[]{0, 0, 2, 2}, nums);
    }

    // --- sortColors2 (counting sort) tests ---

    @Test
    void countingSort_leetcodeExample1() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors.sortColors2(nums);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    void countingSort_leetcodeExample2() {
        int[] nums = {2, 0, 1};
        SortColors.sortColors2(nums);
        assertArrayEquals(new int[]{0, 1, 2}, nums);
    }

    @Test
    void countingSort_singleElement() {
        int[] nums = {0};
        SortColors.sortColors2(nums);
        assertArrayEquals(new int[]{0}, nums);
    }

    @Test
    void countingSort_reverseSorted() {
        int[] nums = {2, 2, 1, 1, 0, 0};
        SortColors.sortColors2(nums);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    void countingSort_missingOneColor() {
        int[] nums = {1, 1, 0, 0};
        SortColors.sortColors2(nums);
        assertArrayEquals(new int[]{0, 0, 1, 1}, nums);
    }
}
