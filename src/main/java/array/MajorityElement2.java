package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 229. Medium.
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * Note: The algorithm should run in linear time and in O(1) space.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>use a HashMap, O(n) time, O(n) space.</b>
 * <li>Boyer-Moore voting O(N) time, O(1) space.
 * </ul>
 */
public class MajorityElement2 {
    public List<Integer> majorityElementMap(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int oneThird = nums.length / 3;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) {
            if (!counts.containsKey(n)) counts.put(n, 1);
            else counts.put(n, counts.get(n) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : counts.entrySet())
            if (entry.getValue() > oneThird) result.add(entry.getKey());
        return result;
    }

    public List<Integer> majorityElementBMVoting(int[] nums) {
        // max number of majority elements is 2 for > [3/n]
        List<Integer> result = new ArrayList<>(2);
        if (nums == null || nums.length == 0) return result;
        int candidate1 = nums[0], candidate2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candidate1) count1++;
            else if (nums[i] == candidate2) count2++;
            else if (count1 == 0) {
                candidate1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candidate1) count1++;
            else if (nums[i] == candidate2) count2++;
        }
        if (count1 > len / 3) result.add(candidate1);
        if (count2 > len / 3) result.add(candidate2);
        return result;
    }

}
