package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * LeetCode 169. Easy.
 * <p>
 * Given an array of size n, find the majority element. The majority element is the element that
 * appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3] Output: 3
 * <p>
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2] Output: 2
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * <b>Summary:</b>
 * <p>
 * This is generalization of https://en.wikipedia.org/wiki/Element_distinctness_problem. Decision tree complexity
 * O(NlgK). Quantum complexity Theta(N^2/3) for distinctness (k = N).
 * <p>
 * <ul>
 * <li><b>use a HashMap, O(n) time, O(n) space.</b>
 * <li>Sort O(NlgN) time, O(1) or O(n) space.
 * <li>Boyer-Moore voting, O(n) time, O(1) space.
 * <li>Random pick and check, O(infinity) unbounded worst case time, O(n) probability time, O(1) space.
 * <li>Divide and conquer. O(NlgN) time, O(lgN) space.
 * </ul>
 */
public class MajorityElement {

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) if (nums[i] == num) count++;
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority element.
        if (lo == hi) return nums[lo];

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) return left;

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElementRecursive(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countOccurrence(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) if (nums[i] == num) count++;
        return count;
    }

    public int majorityElementRandom(int[] nums) {
        Random rand = new Random();
        int majorityCount = nums.length / 2;
        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurrence(nums, candidate) > majorityCount) return candidate;
        }
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) counts.put(num, 1);
            else counts.put(num, counts.get(num) + 1);
        }
        return counts;
    }

    public int majorityElementMap1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) map.put(num, 1);
            else {
                Integer count = map.get(num);
                count++;
                map.put(num, count);
            }
            if (map.get(num) > nums.length / 2) return num;
        }
        throw new RuntimeException("No majority element.");
    }

    public int majorityElementMap2(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet())
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) majorityEntry = entry;
        return majorityEntry.getKey();
    }

    public int majorityElementBMVoting1(int[] num) {
        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major = num[i];
            } else if (major == num[i]) count++;
            else count--;
        }
        return major;
    }

    public int majorityElementBMVoting2(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElementMap3(int[] nums) {
        int L = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < L; i++) {
            if (map.containsKey(nums[i])) {
                if (map.get(nums[i]) >= L / 2) return nums[i];
                map.replace(nums[i], map.get(nums[i]) + 1);
            } else map.put(nums[i], 1);
        }
        return nums[L - 1];
    }
}
