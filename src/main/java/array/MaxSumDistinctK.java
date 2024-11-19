package array;

import java.util.HashMap;

/**
 * LeetCode 2461, medium.
 */
@SuppressWarnings("unused")
public class MaxSumDistinctK {
    // n, n.
    static class Solution {
        public long maximumSubarraySum(int[] nums, int k) {
            long res = 0, cur = 0, dup = -1;
            HashMap<Integer, Integer> last = new HashMap<>(); // val->last seen index
            for (int i = 0; i < nums.length; ++i) {
                cur += nums[i];
                if (i >= k) cur -= nums[i - k];
                dup = Math.max(dup, last.getOrDefault(nums[i], -1));
                if (i - dup >= k) res = Math.max(res, cur); // past the duplicate segment
                last.put(nums[i], i);
            }
            return res;
        }
    }
}
