package array;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public static void main(String[] args) {
        Permutation tbt = new Permutation();
        int[] nums = new int[]{1, 2, 3};
        System.out.println(tbt.permute(nums));
    }

    // Iterative, O(N*N!) time, O(1) space not including result space.
    // GFG Johnson and Trotter maybe better but harder to implement
    public List<List<Integer>> permuteI(int[] num) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l0 = new ArrayList<>();
        l0.add(num[0]);
        res.add(l0);
        for (int i = 1; i < num.length; i++) { // num[i] will be inserted at jth index for each existing list
            List<List<Integer>> new_res = new ArrayList<>();
            for (int j = 0; j <= i; ++j) {
                for (List<Integer> l : res) {
                    List<Integer> new_l = new LinkedList<>(l);
                    new_l.add(j, num[i]);
                    new_res.add(new_l);
                }
            }
            res = new_res;
        }
        return res;
    }

    // 1ms, 42.2 Mb. O(N*N!) time, not considering result space: O(N) space or O(1) space if input is already a list.
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int n : nums) numList.add(n);
        permute(numList, 0, res);
        return res;
    }

    // A[1] + permutation of the rest
    // A[2] + permutation of the rest
    // see resources/permutation.png GFG all string permutations
    void permute(List<Integer> nums, int begin, List<List<Integer>> res) {
        if (begin == nums.size()) {
            res.add(new ArrayList<>(nums));
            // Arrays.stream(nums).boxed().collect(Collectors.toList()) can just use the int[] nums as parameter
            return;
        }
        for (int i = begin; i < nums.size(); i++) {
            swap(nums, begin, i); // swap i_th to begin_th
            permute(nums, begin + 1, res); // permute the rest
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
