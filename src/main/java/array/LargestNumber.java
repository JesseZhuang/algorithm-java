package array;

import java.util.Arrays;

/**
 * LeetCode 179, medium, tags: array, string, greedy, sorting.
 * <p>
 * Given a list of non-negative integers nums, arrange them such that they form the largest number
 * and return it as a string.
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,2]
 * Output: "210"
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 */
@SuppressWarnings("unused")
public final class LargestNumber {

    private LargestNumber() {
    }

    // Custom comparator, O(n log n) time, O(n) space. 3ms, 41.5mb.
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        if (strs[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : strs) sb.append(s);
        return sb.toString();
    }
}
