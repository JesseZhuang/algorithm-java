package bit;

/**
 * LeetCode 2044, medium, tags: bit manipulation, array, backtracking, enumeration.
 * <p>
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of
 * different non-empty subsets with the maximum bitwise OR.
 * <p>
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
 * Two subsets are considered different if the indices of the elements chosen are different.
 * <p>
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1]
 * Output: 2
 * Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
 * - [3]
 * - [3,1]
 * Example 2:
 * <p>
 * Input: nums = [2,2,2]
 * Output: 7
 * Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
 * Example 3:
 * <p>
 * Input: nums = [3,2,1,5]
 * Output: 6
 * Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 16, n
 * 1 <= nums[i] <= 10^5, m == 1 << (ilog2_max(nums)+1), which is the max possible OR value
 * <p>
 * Hint 1
 * Can we enumerate all possible subsets?
 * Hint 2
 * The maximum bitwise-OR is the bitwise-OR of the whole array.
 */
@SuppressWarnings("unused")
public class CntMaxBitOrSubsets {
    // dp, mn, m.
    // [3,2,1,5], after processing index 2 element 1: dp[3]==5: (1,2),(1,2,3),(3),(2,3),(1,3), max:3
    // dp[3|5]==dp[7] += dp[3], 5 combining with the 5 groups above will have OR value of 7
    static class Solution {
        public int countMaxOrSubsets(int[] nums) {
            int max = 0;
            int[] dp = new int[1 << 17]; // dp[i]: cnt of subsets with OR value of i
            dp[0] = 1; // empty subset OR value 0
            for (int n : nums) {
                // backwards, avoid double counting, e.g., dp[0|2]+=dp[0], then dp[2|2]+=dp[2]
                for (int i = max; i >= 0; i--)
                    dp[i | n] += dp[i];
                max |= n;
            }
            return dp[max];
        }
    }

    // n*2^n, 1.
    static class Solution2 {
        public int countMaxOrSubsets(int[] nums) {
            int maxOr = 0;
            for (int num : nums) maxOr |= num;
            int totalSubsets = 1 << nums.length; // 2^n subsets
            int res = 0;
            // Iterate through all possible subsets
            for (int mask = 0; mask < totalSubsets; mask++) {
                int curOr = 0;
                for (int i = 0; i < nums.length; i++)
                    if (((mask >> i) & 1) == 1) curOr |= nums[i];
                if (curOr == maxOr) res++;
            }
            return res;
        }
    }

    // 2^n, n, recursive. with memoization nm, nm.
    static class Solution4 {
        public int countMaxOrSubsets(int[] nums) {
            int maxOr = 0;
            for (int num : nums) maxOr |= num;
            return countSubsets(nums, 0, 0, maxOr);
        }

        private int countSubsets(int[] nums, int index, int curOr, int targetOr) {
            // Base case: reached the end of the array
            if (index == nums.length) return (curOr == targetOr) ? 1 : 0;
            // Don't include the current number
            int countWithout = countSubsets(nums, index + 1, curOr, targetOr);
            // Include the current number
            int countWith = countSubsets(nums, index + 1, curOr | nums[index], targetOr);
            // Return the sum of both cases
            return countWithout + countWith;
        }
    }
}
