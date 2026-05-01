package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/k-diff-pairs-in-an-array/">LeetCode 532</a>, medium,
 * tags: array, hash table, counting, two pointers, binary search, sorting.
 */
public final class KdiffPairs {
    private KdiffPairs() {}

    /**
     * Count values and check for k-diff pairs.
     * Time O(n), Space O(n).
     */
    public static int findPairs(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int n : nums) cnt.merge(n, 1, Integer::sum);
        int res = 0;
        for (var e : cnt.entrySet()) {
            if (k > 0 && cnt.containsKey(e.getKey() + k)) res++;
            else if (k == 0 && e.getValue() > 1) res++;
        }
        return res;
    }
}
