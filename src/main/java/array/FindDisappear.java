package array;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 448, LintCode 1236, easy, tags: array, hash table.
 * <p>
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the
 * range [1, n] that do not appear in nums.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * Example 2:
 * <p>
 * Input: nums = [1,1]
 * Output: [2]
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * <p>
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count
 * as extra space.
 * <p>
 * Hint 1:
 * This is a really easy problem if you decide to use additional memory. For those trying to write an initial solution
 * using additional memory, think counters!
 * HashSet or boolean array to mark seen or not, O(n) space.
 * Hint 2:
 * However, the trick really is to not use any additional space than what is already available to use.
 * Sometimes, multiple passes over the input array help find the solution. However, there's an interesting piece of
 * information in this problem that makes it easy to re-use the input array itself for the solution.
 * Hint 3:
 * The problem specifies that the numbers in the array will be in the range [1, n] where n is the number of elements
 * in the array. Can we use this information and modify the array in-place somehow to find what we need?
 */
public class FindDisappear {
    // may talk about hashset or boolean array solution, O(n) space
    // solution 1, index sort, n time, 1 space, 6ms, 53.74Mb.
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                int n = nums[i];
                nums[i] = nums[n - 1]; // take nums[n-1] and put at index i, do not increment i
                nums[n - 1] = n; // put n in place at index n-1
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < l; i++)
            if (nums[i] != i + 1) res.add(i + 1);
        return res;
    }

    // solution 2, set seen negative, n time, 1 space. 5ms, 53.48Mb.
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int n : nums) { // mark n is present by negative nums[n-1], may have been negatived before
            n = Math.abs(n);
            nums[n - 1] = -Math.abs(nums[n - 1]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > 0) res.add(i + 1);
        return res;
    }
}
