package array;

/**
 * LeetCode 330, hard, tags: array, greedy.
 * <p>
 * Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the
 * range [1, n] inclusive can be formed by the sum of some elements in the array.
 * <p>
 * Return the minimum number of patches required.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * Example 2:
 * <p>
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 * Example 3:
 * <p>
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000, m
 * 1 <= nums[i] <= 10^4
 * nums is sorted in ascending order.
 * 1 <= n <= 2^31 - 1
 */
@SuppressWarnings("unused")
public class PatchingArray {
    // m+lgn, 1
    // if [0,miss) is covered, add miss, then [0,2*miss) is covered
    static class Solution {
        public int minPatches(int[] nums, int n) {
            long miss = 1;
            int res = 0, i = 0;
            while (miss <= n) { // need to cover [0,n] so miss >= n+1 [0,miss)
                if (i < nums.length && nums[i] <= miss) miss += nums[i++]; // extend miss to miss+nums[i]
                else {
                    miss *= 2;
                    res++;
                }
            }
            return res;
        }
    }
}
