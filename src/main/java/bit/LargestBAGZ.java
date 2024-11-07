package bit;

/**
 * LeetCode 2275, medium, tags: array, hash table, bit manipulation, counting.
 * <p>
 * The bitwise AND of an array nums is the bitwise AND of all integers in nums.
 * <p>
 * For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
 * Also, for nums = [7], the bitwise AND is 7.
 * You are given an array of positive integers candidates. Evaluate the bitwise AND of every combination of numbers
 * of candidates. Each number in candidates may only be used once in each combination.
 * <p>
 * Return the size of the largest combination of candidates with a bitwise AND greater than 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [16,17,71,62,12,24,14]
 * Output: 4
 * Explanation: The combination [16,17,62,24] has a bitwise AND of 16 & 17 & 62 & 24 = 16 > 0.
 * The size of the combination is 4.
 * It can be shown that no combination with a size greater than 4 has a bitwise AND greater than 0.
 * Note that more than one combination may have the largest size.
 * For example, the combination [62,12,24,14] has a bitwise AND of 62 & 12 & 24 & 14 = 8 > 0.
 * Example 2:
 * <p>
 * Input: candidates = [8,8]
 * Output: 2
 * Explanation: The largest combination [8,8] has a bitwise AND of 8 & 8 = 8 > 0.
 * The size of the combination is 2, so we return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 10^5, n
 * 1 <= candidates[i] <= 10^7, m
 * <p>
 * Hint 1
 * For the bitwise AND to be greater than zero, at least one bit should be 1 for every number in the combination.
 * Hint 2
 * The candidates are 24 bits long, so for every bit position, we can calculate the size of the largest combination
 * such that the bitwise AND will have a 1 at that bit position.
 */
@SuppressWarnings("unused")
public class LargestBAGZ {
    // nlgm, 1. use array for each bit, need lgm space.
    static class Solution {
        public int largestCombination(int[] A) {
            int res = 0, cur;
            for (int i = 1; i <= 10_000_000; i <<= 1) {
                cur = 0;
                for (int a : A)
                    if ((a & i) > 0) cur++;
                res = Math.max(res, cur);
            }
            return res;
        }
    }
}
