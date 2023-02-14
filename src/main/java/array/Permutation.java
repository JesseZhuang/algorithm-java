package array;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 46, medium, tags: array, backtracking.
 * <p>
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutation {

    // 1ms, 42.2 Mb. O(N*N!) time, O(N) space.
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int n : nums) numList.add(n);
        permute(numList, 0, res);
        return res;
    }

    void permute(List<Integer> nums, int begin, List<List<Integer>> res) {
        if (begin == nums.size()) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for (int i = begin; i < nums.size(); i++) {
            swap(nums, begin, i);
            permute(nums, begin + 1, res);
            swap(nums, begin, i);
        }
    }

    private void swap(List<Integer> nums, int i, int j) {
        if (i != j) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }
}
