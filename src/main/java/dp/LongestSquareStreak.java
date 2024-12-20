package dp;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * LeetCode 2501, medium, tags: array, hash table, binary search, dp, sorting.
 * <p>
 * You are given an integer array nums. A subsequence of nums is called a square streak if:
 * <p>
 * The length of the subsequence is at least 2, and
 * after sorting the subsequence, each element (except the first element) is the square of the previous number.
 * Return the length of the longest square streak in nums, or return -1 if there is no square streak.
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
 * the order of the remaining elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,6,16,8,2]
 * Output: 3
 * Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
 * - 4 = 2 * 2.
 * - 16 = 4 * 4.
 * Therefore, [4,16,2] is a square streak.
 * It can be shown that every subsequence of length 4 is not a square streak.
 * Example 2:
 * <p>
 * Input: nums = [2,3,5,6,7]
 * Output: -1
 * Explanation: There is no square streak in nums so return -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 10^5
 * 2 <= nums[i] <= 10^5
 * <p>
 * Hint 1
 * With the constraints, the length of the longest square streak possible is 5.
 * Hint 2
 * Store the elements of nums in a set to quickly check if it exists.
 */
@SuppressWarnings("unused")
public class LongestSquareStreak {
    // set, n, n.
    // binary search or sort based, nlgn, n.
    static class Solution {
        public int longestSquareStreak(int[] nums) {
            int res = 0;
            Set<Integer> uniq = Arrays.stream(nums).boxed().collect(toSet());
            for (int start : nums) {
                int streak = 0; // cur streak
                long cur = start;
                // Continue the streak as long as we can find the next square in the set
                while (uniq.contains((int) cur)) {
                    streak++;
                    // Break if the next square would be larger than 10^5 (problem constraint)
                    if (cur * cur > 1e5) break;
                    cur *= cur;
                }
                res = Math.max(res, streak);
            }
            return res < 2 ? -1 : res;
        }
    }
}
