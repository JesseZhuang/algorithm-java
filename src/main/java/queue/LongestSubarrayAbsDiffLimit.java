package queue;

import java.util.ArrayDeque;
import java.util.TreeMap;

/**
 * LeetCode 1438, medium, tags: array, queue, sliding window, ordered set, monotonic queue.
 * <p>
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 * <p>
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [2,4] with maximum absolute diff |4-2| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |7-2| = 5 > 4.
 * [4,7] with maximum absolute diff |7-4| = 3 <= 4.
 * So the size of the longest subarray is 2.
 * <p>
 * Example 2:
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the max absolute diff is |7-2| = 5 <= 5.
 * <p>
 * Example 3:
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 */
@SuppressWarnings("unused")
public final class LongestSubarrayAbsDiffLimit {
    private LongestSubarrayAbsDiffLimit() {
    }

    /**
     * Solution 1: Monotonic deques. O(n) time, O(n) space.
     * Use a decreasing deque for max and an increasing deque for min.
     */
    public static int longestSubarrayDeque(int[] nums, int limit) {
        int n = nums.length, res = 0, left = 0;
        ArrayDeque<Integer> maxDq = new ArrayDeque<>(); // decreasing: front is max index
        ArrayDeque<Integer> minDq = new ArrayDeque<>(); // increasing: front is min index
        for (int right = 0; right < n; right++) { // O(n) each element enqueued/dequeued at most once
            // maintain decreasing deque for window max
            while (!maxDq.isEmpty() && nums[maxDq.peekLast()] <= nums[right]) maxDq.removeLast();
            maxDq.addLast(right);
            // maintain increasing deque for window min
            while (!minDq.isEmpty() && nums[minDq.peekLast()] >= nums[right]) minDq.removeLast();
            minDq.addLast(right);
            // shrink window from left until constraint satisfied
            while (nums[maxDq.peekFirst()] - nums[minDq.peekFirst()] > limit) { // O(1) amortized
                left++;
                if (maxDq.peekFirst() < left) maxDq.removeFirst();
                if (minDq.peekFirst() < left) minDq.removeFirst();
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    /**
     * Solution 2: TreeMap. O(n log n) time, O(n) space.
     * TreeMap maintains sorted element counts; firstKey/lastKey give min/max in O(log n).
     */
    public static int longestSubarrayTreeMap(int[] nums, int limit) {
        int n = nums.length, res = 0, left = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>(); // O(n) space for window elements
        for (int right = 0; right < n; right++) {
            map.merge(nums[right], 1, Integer::sum); // O(log n) insert/update
            // shrink window until max - min <= limit
            while (map.lastKey() - map.firstKey() > limit) { // O(log n) for firstKey/lastKey
                int val = nums[left++];
                int cnt = map.get(val);
                if (cnt == 1) map.remove(val);
                else map.put(val, cnt - 1);
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
