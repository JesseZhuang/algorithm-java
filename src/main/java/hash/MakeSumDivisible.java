package hash;

import java.util.HashMap;

/**
 * LeetCode 1590, medium, tags: array, hash table, prefix sum.
 * <p>
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the
 * remaining elements is divisible by p. It is not allowed to remove the whole array.
 * <p>
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 * <p>
 * A subarray is defined as a contiguous block of elements in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4],
 * and the sum of the remaining elements is 6, which is divisible by 6.
 * Example 2:
 * <p>
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the
 * subarray [5,2], leaving us with [6,3] with sum 9.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus, we do not need to remove anything.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= p <= 10^9
 * <p>
 * Hint 1
 * Use prefix sums to calculate the subarray sums.
 * Hint 2
 * Suppose you know the remainder for the sum of the entire array. How does removing a subarray affect that
 * remainder? What remainder does the subarray need to have in order to make the rest of the array sum up to be divisible by k?
 * Hint 3
 * Use a map to keep track of the rightmost index for every prefix sum % p.
 */
@SuppressWarnings("unused")
public class MakeSumDivisible {
    // prefix sum+hashmap, n, n, 33ms, 56.53mb.
    static class Solution {
        public int minSubarray(int[] nums, int p) {
            int n = nums.length, sum = 0;
            // Step 1: Calculate total sum and target remainder
            for (int num : nums) sum = (sum + num) % p;
            int target = sum % p;
            if (target == 0) return 0; // The array is already divisible by p
            HashMap<Integer, Integer> ri = new HashMap<>(); // remainder:index
            ri.put(0, -1); // To handle the case where the whole prefix is the answer
            int cur = 0, res = n;
            for (int i = 0; i < n; ++i) {
                cur = (cur + nums[i]) % p;
                int look = (cur - target + p) % p; // look for diff to make up the target
                // If we have seen the look remainder, we can consider this subarray
                if (ri.containsKey(look)) res = Math.min(res, i - ri.get(look));
                ri.put(cur, i);
            }
            return res == n ? -1 : res;
        }
    }
}
