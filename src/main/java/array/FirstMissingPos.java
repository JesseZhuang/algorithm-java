package array;

/**
 * LeetCode 41, hard, tags: array, hash table.
 * <p>
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 * Example 2:
 * <p>
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 * Example 3:
 * <p>
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * <p>
 * Hint 1
 * Think about how you would solve the problem in non-constant space. Can you apply that logic to the existing space?
 * Hint 2
 * We don't care about duplicates or non-positive integers
 * Hint 3
 * Remember that O(2n) = O(n)
 */
public class FirstMissingPos {
    // solution 1, index sort, n time, 1 space, 1ms, 59.3Mb.
    // hashset to store numbers [1,len], then look for [1,len] in the hashset, return first one missing
    public int firstMissingPositive(int[] nums) {
        int l = nums.length, i = 0;
        while (i < l) {
            int n = nums[i];
            if (n == i + 1 || n <= 0 || n > l || nums[i] == nums[n - 1]) i++;
            else {
                nums[i] = nums[n - 1];
                nums[n - 1] = n;
            }
        }
        i = 0;
        while (i < l && nums[i] == i + 1) i++;
        return i + 1;
    }
}
