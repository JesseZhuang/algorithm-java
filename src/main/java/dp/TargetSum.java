package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 494, medium, tags: array, dynamic programming, backtracking.
 * You are given an integer array nums and an integer target.
 * <p>
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums
 * and then concatenate all the integers.
 * <p>
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the
 * expression "+2-1".
 * <p>
 * Return the number of different expressions that you can build, which evaluates to target.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], target = 1
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 20, n
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000, s
 * -1000 <= target <= 1000
 */
public final class TargetSum {

    private TargetSum() {
    }

    // solution 1, subset sum DP. O(n*P) time, O(P) space. P = (total + target) / 2.
    // Transform: let P = subset assigned '+', N = subset assigned '-'.
    // P + N = total, P - N = target => P = (total + target) / 2.
    // Count subsets of nums that sum to P.
    public static int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int n : nums) total += n;
        if (Math.abs(target) > total || (total + target) % 2 != 0) return 0; // early return
        int p = (total + target) / 2;
        int[] dp = new int[p + 1]; // dp[j]: number of subsets summing to j
        dp[0] = 1;
        for (int num : nums) { // O(n) iterations
            for (int j = p; j >= num; j--) { // O(P) iterations, reverse to avoid reuse
                dp[j] += dp[j - num]; // accumulate ways
            }
        }
        return dp[p];
    }

    // solution 2, DFS + memoization. O(n*s) time and space where s = sum(nums).
    public static int findTargetSumWays2(int[] nums, int target) {
        int total = 0;
        for (int n : nums) total += n;
        if (Math.abs(target) > total) return 0; // early return, target unreachable
        Map<Long, Integer> memo = new HashMap<>();
        return dfs(nums, 0, target, memo);
    }

    private static int dfs(int[] nums, int index, int remaining, Map<Long, Integer> memo) {
        if (index == nums.length) return remaining == 0 ? 1 : 0;
        long key = (long) index * 2001 + remaining + 1000; // unique key for (index, remaining)
        if (memo.containsKey(key)) return memo.get(key);
        // O(2^n) without memo, O(n*s) with memo where s = range of possible sums
        int ways = dfs(nums, index + 1, remaining - nums[index], memo)
                + dfs(nums, index + 1, remaining + nums[index], memo);
        memo.put(key, ways);
        return ways;
    }
}
