package array;

import java.util.Arrays;

/**
 * LeetCode 2563, medium, tags: array, two pointers, binary search, sorting.
 */
@SuppressWarnings("unused")
public class CountFairPairs {
    // O(nlgn) time and space.
    static class Solution {
        public long countFairPairs(int[] nums, int lower, int upper) {
            Arrays.sort(nums);
            return cntLess(nums, upper + 1) - cntLess(nums, lower); // [0,upper+1)-[0,lower): [lower,upper]
        }

        // Calculate the number of pairs with a sum less than `value`.
        private long cntLess(int[] nums, int value) {
            int l = 0, r = nums.length - 1;
            long res = 0;
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum < value) {
                    res += (r - l);
                    l++;
                } else r--;
            }
            return res;
        }
    }
}
