package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 78, medium, tags: array, backtracking, bit manipulation.
 * <p>
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10, n
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
public class Subsets {
    List<List<Integer>> res = new ArrayList();
    int n, k;

    void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        if (curr.size() == k) { // if the combination is done
            res.add(new ArrayList(curr));
            return;
        }
        for (int i = first; i < n; ++i) {
            curr.add(nums[i]); // add i into the current combination
            backtrack(i + 1, curr, nums); // use next integers to complete the combination
            curr.remove(curr.size() - 1); // backtrack
        }
    }

    // 1ms, 43 Mb, backtracking, O(n*2^n) time, O(n) space does not count result, modify curr in place.
    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) backtrack(0, new ArrayList<>(), nums); // k: subset size
        return res;
    }


    // 1ms, 43.1Mb, binary sorted, Don Knuth, bit mask. O(n*2^n) time and space.
    public List<List<Integer>> subsetsBS(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        int n = nums.length;
        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 (empty subset) to 1..11 (entire array subset)
            String bitmask = Integer.toBinaryString(i).substring(1);
            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            res.add(curr);
        }
        return res;
    }

    // 1ms, 42.5Mb, cascading, O(n*2^n) time and space.
    public static List<List<Integer>> subsetsCascade(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        res.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : res) {
                newSubsets.add(new ArrayList<Integer>(curr) {{ // double brace initialization
                    add(num);
                }});
            }
            for (List<Integer> curr : newSubsets) res.add(curr);
        }
        return res;
    }

    // [3,2,4,1] expected [[],[3],[2],[2,3],[4],[3,4],[2,4],[2,3,4],[1],[1,3],[1,2],[1,2,3],[1,4],[1,3,4],[1,2,4],[1,2,3,4]]
    // missing skipping subsets
    public static List<List<Integer>> subsetsWrong(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 1; i < nums.length; i++) { // n choose i
            for (int j = 0; j < nums.length; j++) { // starting element
                List<Integer> l = new ArrayList<>();
                for (int k = j; k < j + i; k++) l.add(nums[k % nums.length]);
                res.add(l);
            }
        }
        res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(subsetsWrong(new int[]{1, 2, 3}));
//        System.out.println(subsetsWrong(new int[]{1, 2, 3, 4}));
//        System.out.println(subsetsCascade(new int[]{3, 2, 4, 1}));
        Subsets tbt = new Subsets();
        System.out.println(tbt.subsets(new int[]{1, 2, 3, 4}));
    }
}
