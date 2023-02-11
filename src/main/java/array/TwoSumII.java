package array;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 167. Easy.
 * <p>
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up t
 * o a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1
 * must be less than index2.
 * <p>
 * Note:
 * <p>
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 * <p>
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * <p>
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>HashMap, O(N) time, O(N) space.</b>
 * <li>Binary search, O(NlgN) time, O(1) space.
 * <li>Two pointer, O(N) time, O(1) space.
 * </ul>
 */
public class TwoSumII {
    public int[] twoSumBS(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int lo = i + 1, hi = numbers.length - 1;
            if (lo > hi) continue;
            int other = target - numbers[i];
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (numbers[mid] == other) return new int[]{i + 1, mid + 1};
                else if (numbers[mid] < other) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return result;
    }

    public int[] twoSumMap(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> numIndex = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numIndex.containsKey(target - numbers[i])) return new int[]{numIndex.get(target - numbers[i]), i + 1};
            numIndex.put(numbers[i], i + 1);
        }
        return result;
    }

    public int[] twoSum2P(int[] numbers, int target) {
        int[] result = new int[2];
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum > target) hi--;
            else if (sum < target) lo++;
            else return new int[]{lo + 1, hi + 1};
        }
        return result;
    }

}
