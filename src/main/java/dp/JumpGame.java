package dp;

/**
 * LeetCode 55, medium, tags: array, dynamic programming, greedy.
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element
 * in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^5
 */
public class JumpGame {
    // solution 1, O(n) time, O(1) space, 2ms, 42.8Mb
    public boolean canJumpDP1(int[] nums) {
        for (int i = 0, reach = 0; i < nums.length && i <= reach; i++) { // <= reach, not <
            reach = Math.max(reach, i + nums[i]);
            if (reach >= nums.length - 1) return true;
        }
        return false; // i == nums.length also ok
    }

    // solution 2, O(n) time, O(1) space, 1ms, 43Mb
    public boolean canJumpDP2(int[] nums) {
        int smallest = nums.length - 1; // smallest index can reach to the end
        for (int i = nums.length - 2; i >= 0; i--) if (i + nums[i] >= smallest) smallest = i;
        return smallest <= 0;
    }
}
