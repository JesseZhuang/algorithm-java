package array;

/**
 * <a href="https://leetcode.com/problems/next-permutation/">31. Next Permutation</a>
 */
public final class NextPermutation {
    private NextPermutation() {
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        // Step 1: find pivot — rightmost element smaller than successor, O(n)
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            // Step 2: find rightmost element larger than pivot, O(n)
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        // Step 3: reverse suffix, O(n)
        // Total: O(n) time, O(1) space
        reverse(nums, i + 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
