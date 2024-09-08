package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 47, medium, tags: array, backtracking.
 * <p>
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations
 * in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * Example 2:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermutationII {

    public static void main(String[] args) {
        PermutationII tbt = new PermutationII();
        int[] nums = new int[]{1, 1, 2};
        System.out.println(tbt.permute(nums));
    }

    // solution, use set to avoid duplicate. O(N*N!) time, O(N) space.
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
        HashSet<Integer> fixed = new HashSet<>();
        for (int i = begin; i < nums.size(); i++) {
            if (fixed.add(nums.get(i))) {
                swap(nums, begin, i); // swap i_th to begin_th
                permute(nums, begin + 1, res); // permute the rest
                swap(nums, begin, i);
            }
        }
    }

    private void swap(List<Integer> nums, int i, int j) {
        if (i != j) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }

    // editorial solution, modifies map while backtracking
    public List<List<Integer>> permuteMap(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int n : nums) counter.put(n, counter.getOrDefault(n, 0) + 1);
        LinkedList<Integer> comb = new LinkedList<>();
        backtrack(comb, nums.length, counter, res);
        return res;
    }

    void backtrack(LinkedList<Integer> comb, Integer N, HashMap<Integer, Integer> counter,
                   List<List<Integer>> res) {
        if (comb.size() == N) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            res.add(new ArrayList<>(comb));
            return;
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0) continue;
            // add this number into the current combination
            comb.addLast(num);
            counter.put(num, count - 1);
            // continue the exploration
            backtrack(comb, N, counter, res);
            // revert the choice for the next exploration
            comb.removeLast();
            counter.put(num, count);
        }
    }
}
