package array;

import java.util.*;
import java.util.stream.Collectors;

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
        Solution tbt = new PermutationII.Solution();
        int[] nums = new int[]{1, 1, 2};
        System.out.println(tbt.permuteUnique(nums));
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

    // solution 1, use set to avoid duplicate. O(N*N!) time, O(N) space. 7ms, 45.24Mb.
    static class Solution {
        int[] nums;
        List<List<Integer>> res;

        public List<List<Integer>> permuteUnique(int[] nums) {
            this.nums = nums;
            res = new ArrayList<>();
            permute(0);
            return res;
        }

        void permute(int begin) {
            if (begin == nums.length) {
                res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
                return;
            }
            HashSet<Integer> fixed = new HashSet<>(); // note set construct out then check in for loop
            for (int i = begin; i < nums.length; i++) {
                if (!fixed.add(nums[i])) continue;
                swap(i, begin);
                permute(begin + 1);
                swap(i, begin);
            }
        }

        void swap(int i, int j) {
            if (i == j) return;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
