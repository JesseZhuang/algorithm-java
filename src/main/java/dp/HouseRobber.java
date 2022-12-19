package dp;

import java.util.ArrayDeque;
import java.util.Arrays;

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
    public Integer[] housesRobbed(int[] nums) {
        int robbedPrevious = 0, didNotRobPrevious = 0;

        // use these two queues to track which houses were robbed
        ArrayDeque<Integer> robbedPreRecord = new ArrayDeque<>();
        ArrayDeque<Integer> didNotRobPreRecord = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // If we rob current house, previous house cannot be robbed.
            int currRobbed = didNotRobPrevious + nums[i];
            ArrayDeque<Integer> robbedCur = didNotRobPreRecord.clone();
            robbedCur.add(i);

            // If we don't rob current house, get max of the previous house robbed and not robbed
            int currNotRobbed = Math.max(didNotRobPrevious, robbedPrevious);
            ArrayDeque<Integer> didNotRobCur;
            if (currNotRobbed == didNotRobPrevious) didNotRobCur = didNotRobPreRecord;
            else didNotRobCur = robbedPreRecord;

            // Update values for the next round
            didNotRobPrevious = currNotRobbed;
            robbedPrevious = currRobbed;
            robbedPreRecord = robbedCur;
            didNotRobPreRecord = didNotRobCur;

            System.out.format("if robbed %dth house, stash = %d, robbed %s; if not, stash = %d, robbed %s.\n",
                    i, robbedPrevious, robbedPreRecord, didNotRobPrevious, didNotRobPreRecord);

        }
        if (robbedPrevious > didNotRobPrevious) return robbedPreRecord.toArray(new Integer[0]);
        else return didNotRobPreRecord.toArray(new Integer[0]);
    }

    public int rob(int[] nums) {
        int ifRobbedPrevious = 0, ifDidNotRobPrevious = 0;

        for (int num : nums) {
            int currRobbed = ifDidNotRobPrevious + num;

            // Update values for the next round
            ifDidNotRobPrevious = Math.max(ifDidNotRobPrevious, ifRobbedPrevious);
            ifRobbedPrevious = currRobbed;
        }
        return Math.max(ifRobbedPrevious, ifDidNotRobPrevious);
    }

    // thought too complicated
    @SuppressWarnings("ConstantConditions")
    @Deprecated
    public int rob4(int[] nums) {
        int stash = 0, i = 0, diff = 0, offset;
        // used to record which houses were robbed for logging
        ArrayDeque<Integer> robbed = new ArrayDeque<>();
        // indicating last robbed house index even or odd
        boolean robbedOdd = false;
        while (i < nums.length) {
            offset = nums[i] - nums[i + 1];
            if (offset > diff) {
                if (robbedOdd) {
                    stash -= robbed.poll();
                }
                robbed.push(i);
                stash += nums[i++];
                diff += offset;
                if (i % 2 != 0) robbedOdd = true;
            }
        }
        return stash;
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        System.out.println(Arrays.toString(hr.housesRobbed(new int[]{1, 2, 3, 1})));
    }
}
