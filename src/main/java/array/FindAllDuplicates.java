package array;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 442, medium, tags: array, hash table.
 * <p>
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer
 * appears at most twice, return an array of all the integers that appear twice.
 * <p>
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,1,2]
 * Output: [1]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * Each element in nums appears once or twice.
 */
public class FindAllDuplicates {

    // solution 1, negation marking, O(n) time, O(1) space. 5ms, 51Mb.
    public static List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1; // map value to index, O(1)
            if (nums[idx] < 0) res.add(idx + 1); // already visited, idx+1 is duplicate
            else nums[idx] = -nums[idx]; // mark visited by negating, O(1)
        }
        return res;
    }

    // solution 2, cyclic sort, O(n) time, O(1) space. 6ms, 51Mb.
    public static List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ) { // note do not auto increment i
            int v = nums[i];
            if (v == i + 1) i++; // already in place, nums[i]==i+1
            else if (v == nums[v - 1]) i++; // duplicate detected, v already at nums[v-1], skip
            else { // swap nums[i] and nums[v-1] to place v at index v-1, O(1)
                nums[i] = nums[v - 1];
                nums[v - 1] = v;
            }
        }
        for (int i = 0; i < nums.length; i++) // scan for mismatches, O(n)
            if (nums[i] != i + 1) res.add(nums[i]); // nums[i] is a duplicate
        return res;
    }
}
