package array;

/**
 * LeetCode 3653. Easy. Tags: array, simulation.
 * <p>
 * Given an integer array nums and queries where queries[i] = [l, r, k, v],
 * for each query multiply nums[idx] by v mod (10^9+7) for idx = l, l+k, l+2k, ...
 * while idx <= r. Return XOR of all elements after processing all queries.
 * <p>
 * O(q * n / k) time, O(1) extra space (modify in place).
 */
public final class XorAfterRangeMultiplicationQueries {

    private XorAfterRangeMultiplicationQueries() {
    }

    static final long MOD = 1_000_000_007L;

    public static int xorAfterRangeMultiplicationQueries(int[] nums, int[][] queries) {
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int) ((long) nums[idx] * v % MOD);
            }
        }
        int res = 0;
        for (int x : nums) {
            res ^= x;
        }
        return res;
    }
}
