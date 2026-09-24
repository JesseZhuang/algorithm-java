package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 503, medium, tags: array, stack, monotonic stack.
 * <p>
 * Given a circular integer array nums, return the next greater number for every element in nums.
 * <p>
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for
 * this number.
 * <p>
 * Example 1:
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public final class NextGreaterElementII {
    private NextGreaterElementII() {}

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>(); // monotonic decreasing stack of indices
        for (int i = 0; i < 2 * n; i++) { // O(n)
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) { // O(n) total
                res[stack.pop()] = nums[i % n];
            }
            if (i < n) stack.push(i);
        }
        return res; // Time O(n), Space O(n)
    }

    public static int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) { // O(n)
            for (int j = 1; j < n; j++) { // O(n)
                if (nums[(i + j) % n] > nums[i]) {
                    res[i] = nums[(i + j) % n];
                    break;
                }
            }
        }
        return res; // Time O(n^2), Space O(n)
    }
}
