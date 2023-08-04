package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 15, medium, tags: array, two pointers, sorting.
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
 * and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
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
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 * <p>
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 * <p>
 * <p>
 * Constraints:
 * <pre>
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 * </pre>
 * Hints
 * <p>
 * So, we essentially need to find three numbers x, y, and z such that they add up to the given value.
 * If we fix one of the numbers say x, we are left with the two-sum problem at hand!
 * <p>
 * For the two-sum problem, if we fix one of the numbers, say x, we have to scan the entire array to find the next
 * number y, which is value - x where value is the input parameter. Can we change our array somehow so that this
 * search becomes faster?
 * <p>
 * The second train of thought for two-sum is, without changing the array, can we use additional space somehow?
 * Like maybe a hash map to speed up the search?
 */
public class ThreeSumLC {

    /**
     * O(N^2) time. 1413 ms, 119.2 Mb. With hash set for dedupe.
     * For all methods, space is O(N) because worst case, all values from the array are in the result list.
     */
    public List<List<Integer>> threeSumSet(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums.length < 0) return Collections.emptyList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {// O(N)
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {// O(N)
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) result.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                else if (sum > 0) k--;
                else if (sum < 0) j++;
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * O(N^2) time. 29 ms, 58.7 Mb. 2 pointer.
     */
    public List<List<Integer>> threeSum2P(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) { // O(N)
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) { // O(N)
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return result;
    }

    /**
     * 162 ms, 46.4 Mb. Hashmap to dedupe.
     */
    public List<List<Integer>> threeSumMap(int[] nums) {
        Arrays.sort(nums);
        if (nums.length < 3 || nums[0] > 0) return Collections.emptyList();
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) valueToIndex.put(nums[i], i); // duplicates will keep the rightest index
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) { // O(N)
            if (nums[i] > 0) break;
            for (int j = i + 1; j < nums.length - 1; j++) { // O(N)
                int required = 0 - nums[i] - nums[j];
                if (valueToIndex.containsKey(required) && valueToIndex.get(required) > j)
                    result.add(Arrays.asList(nums[i], nums[j], required));
                j = valueToIndex.get(nums[j]);
            }
            i = valueToIndex.get(nums[i]);
        }
        return result;
    }
}
