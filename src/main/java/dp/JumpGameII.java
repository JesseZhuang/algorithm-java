package dp;

/**
 * LeetCode 45, medium, tags: array, dp, greedy.
 * <p>
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 * <p>
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are
 * at nums[i], you can jump to any nums[i + j] where:
 * <p>
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can
 * reach nums[n - 1].
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1,
 * then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 */
public class JumpGameII {
    // solution 1, 2ms, 45.19Mb. O(n) time, O(1) space.
    public int minJumps(int[] nums) {
        int res = 0;
        for (int i = 0, reach = 0, p = 0; p < nums.length - 1; i++) {
            reach = Math.max(reach, nums[i] + i);
            if (i == p) {
                res++;
                p = reach; // position jump to the furthest can reach
            }
        }
        return res;
    }
}
