package bit;

/**
 * LeetCode 2419, medium. For nonnegative values, a subarray's bitwise AND cannot exceed any of
 * its elements, so the largest possible AND is the array maximum. Only a run of that maximum
 * can attain it. O(n) time and O(1) space.
 */
public final class LongestSubarrayWithMaximumBitwiseAnd {

    private LongestSubarrayWithMaximumBitwiseAnd() {
    }

    public static int longestSubarray(int[] nums) {
        int maximum = Integer.MIN_VALUE;
        int run = 0;
        int best = 0;

        for (int num : nums) {
            if (num > maximum) {
                maximum = num;
                run = 1;
                best = 1;
            } else if (num == maximum) {
                run++;
                best = Math.max(best, run);
            } else {
                run = 0;
            }
        }

        return best;
    }
}
