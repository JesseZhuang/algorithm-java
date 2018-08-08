package hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>
 * LeetCode 219. Easy.
 * <p>
 * Given an array of integers and an integer k, find out whether
 * there are two distinct indices i and j in the array such that nums[i] =
 * nums[j] and the difference between i and j is at most k.
 * <pre>
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * </pre>
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>use HashSet, keep last seen k-1 numbers (sliding window), O(1) space (~ k)
 * <li>use HashMap, O(N) time (average case, worse case NlgN), O(N) space.
 * <li>brute force quadratic time, no space.
 */
public class ContainsDuplicateII {

    public boolean containsNearbyDuplicateSet(int[] numbers, int k) {
        if(numbers == null) return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (i > k) set.remove(numbers[i - k - 1]);
            if (!set.add(numbers[i])) return true;
        }
        return false;
    }

    /**
     * Uses HashMap<ArrayValue, ArrayIndex>
     */
    public boolean containsNearbyDuplicateMap(int[] nums, int k) {
        if (nums != null) {
            HashMap<Integer, Integer> seen = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Integer pre = seen.put(nums[i], i);
                if (pre != null && i - pre <= k) return true;
            }
        }
        return false;
    }
}
