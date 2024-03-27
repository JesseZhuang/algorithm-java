package dp;

import java.util.Arrays;

/**
 * LeetCode 377, medium, tags: array, dynamic programming.
 * Given an array of distinct integers nums and a target integer target,
 * return the number of possible combinations that add up to target.
 * <p>
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * Example 2:
 * <p>
 * Input: nums = [9], target = 3
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200, N
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 * <p>
 * Follow up: What if negative numbers are allowed in the given array? How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 * <p>
 * If negative numbers are included in the array then repetition will lead to infinity, thus giving infinite answers.
 * For example: given array: [-2,2] and target=0 then possible solutions will go as {-2,2} {-2,-2,2,2}
 * {-2,-2,-2,2,2,2}...
 * So in that case the question could add some limitations such as that each element can only be taken once.
 */
public class CombinationSumIV {

    // solution 1, O(N*target) time, O(target) space. 1ms, 40.96 Mb.
    public int combinationSum4I(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // init dp[0] not dp[1]
        for (int i = 1; i <= target; i++) // O(target)
            for (int j = 0; j < nums.length; j++) // O(N)
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
        return dp[target];
    }

    // solution 2, O(N*target) time, O(N+target) space. 1ms, 41.5Mb.
    public int combinationSum4Cache(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target, dp);
    }

    private int helper(int[] nums, int target, int[] dp) {
        if (dp[target] != -1) return dp[target];
        int res = 0;
        for (int i = 0; i < nums.length; i++)
            if (target >= nums[i]) res += helper(nums, target - nums[i], dp);
        dp[target] = res;
        return dp[target];
    }

    // O(N*target) time, O(target) space. Time limit exceeded. Repeating computations similar to fibonacci.
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) return 1;
        int res = 0;
        for (int i = 0; i < nums.length; i++) // O(N)
            if (target >= nums[i]) res += combinationSum4(nums, target - nums[i]); // O(target)
        return res;
    }
}
