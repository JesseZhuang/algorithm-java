package dp;

/**
 * LeetCode 740 - Delete and Earn
 *
 * Given an integer array nums, you can delete nums[i] to earn nums[i] points.
 * Whenever you delete nums[i], all elements equal to nums[i]-1 and nums[i]+1
 * are also deleted. Return the maximum points you can earn.
 */
public final class DeleteAndEarn {

    private DeleteAndEarn() {
    }

    /**
     * DP (House Robber on frequency array).
     * Build earn[v] = v * count(v), then apply house robber over [0..maxVal].
     * Time: O(n + maxVal), Space: O(maxVal)
     */
    public static int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int n : nums) {
            maxVal = Math.max(maxVal, n);
        }

        // earn[v] = total points from taking all copies of value v
        int[] earn = new int[maxVal + 1];
        for (int n : nums) {
            earn[n] += n;
        }

        // House robber DP: O(maxVal) time, O(1) extra space
        int prev2 = 0, prev1 = 0;
        for (int v = 1; v <= maxVal; v++) {
            int curr = Math.max(prev1, prev2 + earn[v]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /**
     * Sort + Group DP: sort unique values, apply house robber only when
     * consecutive unique values differ by 1.
     * Time: O(n + k log k) where k = distinct values, Space: O(k)
     */
    public static int deleteAndEarnSortGroup(int[] nums) {
        java.util.Map<Integer, Integer> countMap = new java.util.TreeMap<>();
        for (int n : nums) {
            countMap.merge(n, 1, Integer::sum);
        }

        int[] keys = new int[countMap.size()];
        int[] earn = new int[countMap.size()];
        int idx = 0;
        for (var entry : countMap.entrySet()) {
            keys[idx] = entry.getKey();
            earn[idx] = entry.getKey() * entry.getValue();
            idx++;
        }

        // House robber on grouped values: O(k) time, O(1) extra space
        int prev2 = 0, prev1 = earn[0];
        for (int i = 1; i < keys.length; i++) {
            int curr;
            if (keys[i] == keys[i - 1] + 1) {
                // Adjacent values conflict — standard house robber choice
                curr = Math.max(prev1, prev2 + earn[i]);
            } else {
                // Non-adjacent — always take current
                curr = prev1 + earn[i];
            }
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
