package array;

/**
 * LeetCode 167, medium, tags: array, two pointers, binary search.
 * <p>
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up t
 * o a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1
 * must be less than index2.
 * <p>
 * Note:
 * <p>
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 * <p>
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * <p>
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * <p>
 * Example 2:
 * <p>
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * <p>
 * Example 3:
 * <p>
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>HashMap, O(N) time, O(N) space.</b>
 * <li>Binary search, O(NlgN) time, O(1) space.
 * <li>Two pointer, O(N) time, O(1) space.
 * </ul>
 * <p>
 * Constraints:
 * <p>
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 */
public class TwoSumII {
    // solution 1, 2p, O(N) time O(1) space. 1ms, 46.1 Mb.
    public int[] twoSum2P(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int v = numbers[l] + numbers[r];
            if (v < target) l++;
            else if (v == target) break;
            else r--;
        }
        return new int[]{l + 1, r + 1};
    }
}
