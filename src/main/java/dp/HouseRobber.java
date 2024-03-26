package dp;

/**
 * LeetCode 198, medium. Tags: array, dynamic programming.
 * <p>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <pre>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * </pre>
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>DP, O(N) time, O(1) space. 0 ms, 15%.</b>
 * <li>Permutation sum, basically same with dp.
 * </ul>
 */
public class HouseRobber {

    // solution 1 dp, n time, 1 space, 0ms, 41.25MB.
    public static int rob(int[] nums, int i, int j) {
        int robPrev = 0, nRobPrev = 0;
        for (int k = i; k < j; k++) {
            int currRobbed = nRobPrev + nums[k];
            // Update values for the next round
            nRobPrev = Math.max(nRobPrev, robPrev); // nRobPrev = robPrev incorrect
            robPrev = currRobbed;
        }
        return Math.max(robPrev, nRobPrev);
    }

    public static int rob(int[] nums) {
        return rob(nums, 0, nums.length);
    }

}
