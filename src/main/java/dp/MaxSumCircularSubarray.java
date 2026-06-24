package dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 918. Maximum Sum Circular Subarray, medium.
 *
 * Given a circular integer array nums, find the maximum possible sum of a non-empty subarray.
 */
public final class MaxSumCircularSubarray {

    private MaxSumCircularSubarray() {
    }

    /**
     * Solution 1: Kadane's algorithm for both max and min subarray.
     * Answer = max(maxSum, totalSum - minSum), unless all elements are negative.
     * O(n) time, O(1) space.
     */
    public static int maxSubarraySumCircularKadane(int[] nums) {
        int totalSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int curMax = 0;
        int curMin = 0;

        for (int num : nums) { // O(n) loop
            curMax = Math.max(curMax + num, num);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + num, num);
            minSum = Math.min(minSum, curMin);
            totalSum += num;
        }

        // If all elements are negative, maxSum is the largest single element.
        // totalSum - minSum would be 0, which is invalid (subarray must be non-empty).
        return maxSum < 0 ? maxSum : Math.max(maxSum, totalSum - minSum);
    }

    /**
     * Solution 2: Monotonic deque on prefix sums of doubled array.
     * For each j, find min prefix[i] with j - n <= i < j using a deque.
     * O(n) time, O(n) space.
     */
    public static int maxSubarraySumCircularDeque(int[] nums) {
        int n = nums.length;
        // prefix[k] = sum of nums[0..k-1] in the doubled conceptual array
        long[] prefix = new long[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) { // O(n) loop — build prefix sums
            prefix[i + 1] = prefix[i] + nums[i % n];
        }

        int ans = Integer.MIN_VALUE;
        // Deque stores indices into prefix; front is the index with minimum prefix value.
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);

        for (int j = 1; j <= 2 * n; j++) { // O(n) loop — sliding window max
            // Remove indices out of window (window size n means j - i <= n)
            while (!deque.isEmpty() && deque.peekFirst() < j - n) {
                deque.pollFirst();
            }
            ans = Math.max(ans, (int) (prefix[j] - prefix[deque.peekFirst()]));
            // Maintain increasing order of prefix values in deque
            while (!deque.isEmpty() && prefix[deque.peekLast()] >= prefix[j]) {
                deque.pollLast();
            }
            deque.addLast(j);
        }

        return ans;
    }
}
