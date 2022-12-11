package bit;

import java.util.Arrays;

/**
 * LeetCode 268, easy. Tags: array, bit, hash table, math, binary search, sorting.
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
 * 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2].
 * 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 * <p>
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9].
 * 8 is the missing number in the range since it does not appear in nums.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 * <p>
 * <p>
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */
public class MissingNumber {
    // 1ms, 51.1 Mb. O(N) time, O(1) space.
    public int missingNumberXor(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) res = res ^ i ^ nums[i]; // a^b^b = a
        return res;
    }

    // 1ms, 51.2 Mb. O(N) time, O(1) space.
    public int missingNumberSum(int[] nums) {
        int len = nums.length;
        int sum = (0 + len) * (len + 1) / 2;
        for (int i = 0; i < len; i++) sum -= nums[i];
        return sum;
    }

    // 11ms, 51 Mb. O(NLgN)time, O(1) space.
    public int missingNumberBinarySearch(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length; // [0,1,3], [0,2,3]
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
