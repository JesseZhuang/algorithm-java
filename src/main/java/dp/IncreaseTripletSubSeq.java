package dp;

/**
 * <p>
 * LeetCode 334. Medium.
 * <p>
 * Given an unsorted array return whether an increasing sub-sequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should: Return true if there exists i, j, k such that
 * arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false. Your
 * algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Examples: Given [1, 2, 3, 4, 5], return true.
 * <p>
 * Given [5, 4, 3, 2, 1], return false.
 * <p>
 * Tags: Array.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>DP, use variables to mark lo, med, hi 3 numbers. O(n) time, O(1) space.</b>
 * </ul>
 *
 * @author Zexi Jesse Zhuang
 */
public class IncreaseTripletSubSeq {

    public boolean increasingTriplet(int[] nums) {
        int low, med, i = 0;
        low = med = Integer.MAX_VALUE;
        while (i <= nums.length - 1) {
            if (nums[i] <= low) low = nums[i];
            else if (nums[i] <= med) med = nums[i];
            else return true;
            i++;
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        int low = 0x7fffffff, medium = 0x7fffffff;
        for (int i = 0, N = nums.length; i < N; i++) {
            if (nums[i] > medium) {
                System.out.printf("triplet: %d,%d,%d%n", low, medium, nums[i]);
                return true;
            } else if (nums[i] > low) medium = Math.min(nums[i], medium);
            else low = nums[i];
        }
        return false;
    }

    // Misunderstood, thought i, j, k need to be consecutive numbers.
    @Deprecated
    public static boolean increasingTripletConsecutive(int[] nums) {
        int n = nums.length, i = 0;
        while (i <= n - 3) {
            if (nums[i] < nums[i + 1]) {
                if (nums[i + 1] < nums[i + 2]) {
                    return true;
                } else i += 2;
            } else i++;
        }
        return false;
    }

}
