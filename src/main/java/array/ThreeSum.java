package array;

import java.util.*;

/**
 * LeetCode 15, medium, tags: array, two pointers, sorting.
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 */
@SuppressWarnings("unused")
public final class ThreeSum {

    private ThreeSum() {
    }

    // Sort + Two Pointers. O(n^2) time, O(1) space (excluding output).
    static class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums); // O(n log n) sort to enable two-pointer approach
            for (int i = 0; i < nums.length - 2; i++) { // O(n) outer loop
                if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate first element
                if (nums[i] > 0) break; // early termination: smallest > 0 means no triplet possible
                int lo = i + 1, hi = nums.length - 1; // two pointers
                while (lo < hi) { // O(n) inner scan
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if (sum < 0) lo++; // sum too small, move left pointer right
                    else if (sum > 0) hi--; // sum too large, move right pointer left
                    else {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++; // skip duplicate second element
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--; // skip duplicate third element
                        lo++;
                        hi--;
                    }
                }
            }
            return res;
        }
    }

    // Hash Set approach. O(n^2) time, O(n) space.
    static class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums); // sort for dedup and early termination
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length - 2; i++) { // O(n) outer loop
                if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate first element
                if (nums[i] > 0) break; // early termination
                Set<Integer> seen = new HashSet<>(); // O(n) space for hash set
                for (int j = i + 1; j < nums.length; j++) { // O(n) inner loop
                    int complement = -nums[i] - nums[j]; // target for third element
                    if (seen.contains(complement)) {
                        res.add(Arrays.asList(nums[i], complement, nums[j]));
                        while (j + 1 < nums.length && nums[j] == nums[j + 1]) j++; // skip duplicate third element
                    }
                    seen.add(nums[j]); // track seen values
                }
            }
            return res;
        }
    }
}
