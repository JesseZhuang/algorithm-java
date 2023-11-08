package string;

import java.util.Arrays;

/**
 * LeetCode 179, medium, tags: array, string, greedy, sorting.
 * <p>
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * <p>
 * Since the result may be very large, so you need to return a string instead of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,2]
 * Output: "210"
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
public class LargestNumber {
    // solution 1, 5ms, 42.74 Mb. O(nLgn) time O(n) space.
    public String largestNumber(int[] nums) {
        String[] sNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) sNums[i] = String.valueOf(nums[i]);
        Arrays.sort(sNums, (a, b) -> (b + a).compareTo(a + b));
        if (sNums[0].equals("0")) return "0"; // note not ==
        StringBuilder res = new StringBuilder();
        for (String sN : sNums) res.append(sN);
        return res.toString();
    }
}
