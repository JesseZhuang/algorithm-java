package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 907, medium, tags: array, dynamic programming, stack, monotonic stack.
 * <p>
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * Example 2:
 * <p>
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 */
@SuppressWarnings("unused")
public final class SumOfSubarrayMinimums {
    private SumOfSubarrayMinimums() {}

    static final int MOD = 1_000_000_007;

    /**
     * Monotonic stack with sentinels, single pass. O(n) time, O(n) space.
     */
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long result = 0;
        Deque<Integer> stack = new ArrayDeque<>(); // indices with increasing values
        // Use sentinels: treat index -1 as value 0 and index n as value 0
        stack.push(-1);

        for (int i = 0; i <= n; i++) { // O(n), each index pushed/popped once
            int curVal = (i == n) ? 0 : arr[i];
            while (stack.peek() != -1 && arr[stack.peek()] > curVal) {
                int mid = stack.pop();
                int left = mid - stack.peek(); // distance to previous <= element
                int right = i - mid; // distance to next < element
                result = (result + (long) arr[mid] * left * right) % MOD;
            }
            stack.push(i);
        }
        return (int) result;
    }

    /**
     * Monotonic stack two pass. O(n) time, O(n) space.
     */
    public static int sumSubarrayMins2(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) { // O(n)
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) { // O(n)
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long result = 0;
        for (int i = 0; i < n; i++) { // O(n)
            result = (result + (long) arr[i] * left[i] * right[i]) % MOD;
        }
        return (int) result;
    }
}
