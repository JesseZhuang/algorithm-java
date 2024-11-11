package math;

/**
 * LeetCode 2601, medium, tags: array, math, binary search, greedy, number theory.
 * <p>
 * You are given a 0-indexed integer array nums of length n.
 * <p>
 * You can perform the following operation as many times as you want:
 * <p>
 * Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then
 * subtract p from nums[i].
 * Return true if you can make nums a strictly increasing array using the above operation and false otherwise.
 * <p>
 * A strictly increasing array is an array whose each element is strictly greater than its preceding element.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,9,6,10]
 * Output: true
 * Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums
 * becomes [1,9,6,10].
 * In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
 * After the second operation, nums is sorted in strictly increasing order, so the answer is true.
 * Example 2:
 * <p>
 * Input: nums = [6,8,11,12]
 * Output: true
 * Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.
 * Example 3:
 * <p>
 * Input: nums = [5,8,3]
 * Output: false
 * Explanation: It can be proven that there is no way to perform operations to make nums sorted in strictly
 * increasing order, so the answer is false.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * nums.length == n
 * <p>
 * Hint 1
 * Think about if we have many primes to subtract from nums[i]. Which prime is more optimal?
 * Hint 2
 * The most optimal prime to subtract from nums[i] is the one that makes nums[i] the smallest as possible and
 * greater than nums[i-1].
 */
@SuppressWarnings("unused")
public class PrimeSubtraction {
    // todo editorial, n+mloglog(m), m.
    static class Solution {

        public boolean primeSubOperation(int[] nums) {
            int maxElement = getMaxElement(nums);

            // Store the sieve array.
            boolean[] sieve = new boolean[maxElement + 1];
            fill(sieve, true);
            sieve[1] = false;
            for (int i = 2; i <= Math.sqrt(maxElement + 1); i++) {
                if (sieve[i]) {
                    for (int j = i * i; j <= maxElement; j += i) {
                        sieve[j] = false;
                    }
                }
            }

            // Start by storing the currValue as 1, and the initial index as 0.
            int currValue = 1;
            int i = 0;
            while (i < nums.length) {
                // Store the difference needed to make nums[i] equal to currValue.
                int difference = nums[i] - currValue;

                // If difference is less than 0, then nums[i] is already less than
                // currValue. Return false in this case.
                if (difference < 0) {
                    return false;
                }

                // If the difference is prime or zero, then nums[i] can be made
                // equal to currValue.
                if (sieve[difference] == true || difference == 0) {
                    i++;
                    currValue++;
                } else {
                    // Otherwise, try for the next currValue.
                    currValue++;
                }
            }
            return true;
        }

        private int getMaxElement(int[] nums) {
            int max = -1;
            for (int num : nums) {
                if (num > max) {
                    max = num;
                }
            }
            return max;
        }

        private void fill(boolean[] arr, boolean value) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = value;
            }
        }
    }
}
