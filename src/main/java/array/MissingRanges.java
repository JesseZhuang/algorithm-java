package array;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 163 LintCode 641, easy, tags: array, simulation.
 * <p>
 * https://leetcode.com/tag/array/
 * <p>
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are
 * in the inclusive range.
 * <p>
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 * <p>
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums
 * is in any of the ranges, and each missing number is in one of the ranges.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * Examples:
 * <p>
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: ["2","4->49","51->74","76->99"]
 * Explanation: The ranges are:
 * [2,2] --> "2"
 * [4,49] --> "4->49"
 * [51,74] --> "51->74"
 * [76,99] --> "76->99"
 * <p>
 * Input: nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
 * Output: ["4->6"]
 * Explanation:
 * in range[0,7],the missing range include range[4,6]
 * <p>
 * Input: nums = [-1], lower = -1, upper = -1
 * Output: []
 * Explanation: There are no missing ranges since there are no missing numbers.
 * <p>
 * Constraints:
 * <p>
 * -10^9 <= lower <= upper <= 10^9, lint code test case has max:2147483647
 * 0 <= nums.length <= 100
 * lower <= nums[i] <= upper
 * All the values of nums are unique.
 */
public class MissingRanges {
    // O(n) time and O(1) space. lint code 1330 ms, 63.05Mb.
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int next = lower;
        for (int i = 0; i < nums.length; i++) {
            if (lower == Integer.MAX_VALUE) return res;
            if (nums[i] == next) {
                next++;
            } else if (nums[i] > next) {
                res.add(getRange(next, nums[i] - 1));
                if (nums[i] == Integer.MAX_VALUE) return res;
                next = nums[i] + 1;
            }
        }
        if (next <= upper) res.add(getRange(next, upper));
        return res;
    }

    private String getRange(int n1, int n2) {
        return n1 == n2 ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
    }
}
