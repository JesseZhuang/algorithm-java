package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 90, medium, tags: array, backtracking, bit manipulation.
 * <p>
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * <p>
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public final class SubsetsII {

    private SubsetsII() {
    }

    /**
     * Backtracking with sort + skip duplicates at same recursion level.
     * O(n*2^n) time — each subset takes O(n) to copy.
     * O(n) space — recursion depth and current subset (excluding result).
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // O(n log n) sort so duplicates are adjacent
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] nums, int start, List<Integer> curr, List<List<Integer>> res) {
        res.add(new ArrayList<>(curr)); // O(n) copy current subset to result
        for (int i = start; i < nums.length; i++) {
            // skip duplicate at same recursion level: only pick first occurrence
            if (i > start && nums[i] == nums[i - 1]) continue; // O(1) duplicate skip
            curr.add(nums[i]);
            backtrack(nums, i + 1, curr, res); // recurse with next index
            curr.remove(curr.size() - 1); // O(1) backtrack
        }
    }

    /**
     * Iterative cascading: for duplicate elements, only extend subsets added in previous round.
     * O(n*2^n) time and space.
     */
    public static List<List<Integer>> subsetsWithDupCascade(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // start with empty subset
        Arrays.sort(nums); // O(n log n) sort so duplicates are adjacent
        int prevNewStart = 0; // tracks where new subsets from previous round begin
        for (int i = 0; i < nums.length; i++) {
            int startIdx;
            if (i > 0 && nums[i] == nums[i - 1]) {
                // duplicate element: only extend subsets added in the previous round
                startIdx = prevNewStart; // O(1) restrict extension range
            } else {
                // new element: extend all existing subsets
                startIdx = 0;
            }
            int resSize = res.size();
            for (int j = startIdx; j < resSize; j++) { // iterate over eligible subsets
                List<Integer> newSubset = new ArrayList<>(res.get(j)); // O(n) copy
                newSubset.add(nums[i]);
                res.add(newSubset);
            }
            prevNewStart = resSize; // O(1) record start of newly added subsets
        }
        return res;
    }
}
