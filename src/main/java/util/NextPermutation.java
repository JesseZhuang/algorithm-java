package util;

import java.util.Arrays;

/**
 * Implementation of c++ next_permutation.
 */
public class NextPermutation {

    public static boolean nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // Find the pivot: Traverse the array from the end and find the first element
        // that is smaller than the element next to it.
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i < 0) return false;
        // Find the next larger element from the back in the suffix.
        int j = nums.length - 1;
        while (nums[j] <= nums[i]) j--;
        IntArrayUtil.swap(nums, i, j); // Swap: Swap the pivot and the larger element.
        reverse(nums, i + 1); // Reverse the subarray following the original pivot position
        // 1,2,3 -> 1,3,2 -> 2,1,3 ...
        return true;
    }

    private static void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            IntArrayUtil.swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2}; // can handle duplicate values
        do {
            System.out.println(Arrays.toString(nums));
        } while (nextPermutation(nums));
    }
}
