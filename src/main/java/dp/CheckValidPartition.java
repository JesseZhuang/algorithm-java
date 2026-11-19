package dp;

/**
 * <a href="https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/">LeetCode 2369</a>,
 * medium, tags: array, dynamic programming.
 */
public final class CheckValidPartition {
    private CheckValidPartition() {}

    /** Rolling prefix DP. Time O(n), auxiliary space O(1). */
    public static boolean validPartition(int[] nums) {
        boolean threeBack = true; // dp[0]: the empty prefix is valid.
        boolean twoBack = false; // dp[1]: a singleton cannot form a group.
        boolean oneBack = nums[0] == nums[1]; // dp[2].

        for (int length = 3; length <= nums.length; length++) { // O(n).
            int last = nums[length - 1];
            int middle = nums[length - 2];
            int first = nums[length - 3];
            boolean equalPair = middle == last;
            boolean validTriple = (first == middle && middle == last)
                    || (first + 1 == middle && middle + 1 == last);
            boolean current = (twoBack && equalPair) || (threeBack && validTriple);

            // Before the next prefix, retain dp[length-2], dp[length-1], dp[length].
            threeBack = twoBack;
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }
}
