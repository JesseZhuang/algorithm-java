package array;

import java.util.Arrays;

/**
 * LeetCode 2563, medium, tags: array, two pointers, binary search, sorting.
 */
@SuppressWarnings("unused")
public class CountFairPairs {
    // O(nlgn) time and space. todo editorial
    static class Solution {
        public long countFairPairs(int[] nums, int lower, int upper) {
            Arrays.sort(nums);
            return cntLess(nums, upper + 1) - cntLess(nums, lower);
        }

        // Calculate the number of pairs with sum less than `value`.
        private long cntLess(int[] nums, int value) {
            int left = 0, right = nums.length - 1;
            long res = 0;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < value) {
                    res += (right - left);
                    left++;
                } else right--;
            }
            return res;
        }
    }
}
