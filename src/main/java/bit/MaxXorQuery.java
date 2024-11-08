package bit;

/**
 * LeetCode 1829, medium, tags:
 * <p>
 * You are given a sorted array nums of n non-negative integers and an integer maximumBit. You want to perform
 * the following query n times:
 * <p>
 * Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k
 * is maximized. k is the answer to the ith query.
 * Remove the last element from the current array nums.
 * Return an array answer, where answer[i] is the answer to the ith query.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,1,3], maximumBit = 2
 * Output: [0,3,2,3]
 * Explanation: The queries are answered as follows:
 * 1st query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
 * 2nd query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
 * 3rd query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
 * 4th query: nums = [0], k = 3 since 0 XOR 3 = 3.
 * Example 2:
 * <p>
 * Input: nums = [2,3,4,7], maximumBit = 3
 * Output: [5,2,6,5]
 * Explanation: The queries are answered as follows:
 * 1st query: nums = [2,3,4,7], k = 5 since 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7.
 * 2nd query: nums = [2,3,4], k = 2 since 2 XOR 3 XOR 4 XOR 2 = 7.
 * 3rd query: nums = [2,3], k = 6 since 2 XOR 3 XOR 6 = 7.
 * 4th query: nums = [2], k = 5 since 2 XOR 5 = 7.
 * Example 3:
 * <p>
 * Input: nums = [0,1,2,2,5,7], maximumBit = 3
 * Output: [4,3,6,4,6,7]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums.length == n
 * 1 <= n <= 10^5
 * 1 <= maximumBit <= 20
 * 0 <= nums[i] < 2^maximumBit
 * nums is sorted in ascending order.
 * <p>
 * Hint 1
 * Note that the maximum possible XOR result is always 2^(maximumBit) - 1
 * Hint 2
 * So the answer for a prefix is the XOR of that prefix XORed with 2^(maximumBit)-1
 */
@SuppressWarnings("unused")
public class MaxXorQuery {
    // n, 1. if using prefix xor array, n space.
    static class Solution {
        public int[] getMaximumXor(int[] nums, int maximumBit) { // 0 1 1 3, 2
            int xProd = 0; // xor product
            for (int num : nums) xProd = xProd ^ num; // 3
            int[] res = new int[nums.length];
            int mask = (1 << maximumBit) - 1; // 3
            for (int i = 0; i < nums.length; i++) {
                res[i] = xProd ^ mask;
                xProd = xProd ^ nums[nums.length - 1 - i]; // remove last element
            }
            return res;
        }
    }
}
