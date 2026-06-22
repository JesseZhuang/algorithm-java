package array;

/**
 * LeetCode 75 - Sort Colors
 * Given an array with n objects colored red (0), white (1), or blue (2),
 * sort them in-place so that objects of the same color are adjacent.
 */
public final class SortColors {

    private SortColors() {
    }

    /**
     * Dutch National Flag algorithm.
     * Time: O(n) single pass. Space: O(1).
     */
    public static void sortColors(int[] nums) {
        int lo = 0, mid = 0, hi = nums.length - 1;
        // Single pass: partition into [0..lo-1]=0, [lo..mid-1]=1, [hi+1..n-1]=2
        while (mid <= hi) {
            if (nums[mid] == 0) {
                swap(nums, lo, mid);
                lo++;
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, hi);
                hi--;
            } else {
                mid++;
            }
        }
    }

    /**
     * Counting sort.
     * Time: O(n) two passes. Space: O(1).
     */
    public static void sortColors2(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        // First pass: count occurrences
        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }
        // Second pass: overwrite array
        int i = 0;
        while (count0-- > 0) nums[i++] = 0;
        while (count1-- > 0) nums[i++] = 1;
        while (count2-- > 0) nums[i++] = 2;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
