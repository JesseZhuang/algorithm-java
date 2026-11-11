package array;

/**
 * LeetCode 2012. Sum of Beauty in the Array.
 * Uses a running prefix maximum and suffix minima: O(n) time, O(n) extra space.
 */
public final class SumOfBeautyInArray {

    private SumOfBeautyInArray() {
    }

    public static int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        int prefixMax = nums[0];
        int beauty = 0;
        for (int i = 1; i < n - 1; i++) {
            if (prefixMax < nums[i] && nums[i] < suffixMin[i + 1]) {
                beauty += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                beauty++;
            }
            prefixMax = Math.max(prefixMax, nums[i]);
        }
        return beauty;
    }
}
