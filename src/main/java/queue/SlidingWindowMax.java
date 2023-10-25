package queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 239, hard, tags: array, queue, sliding window, heap, monotonic queue.
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left
 * of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves
 * right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindowMax {
    // O(n) time and O(k) extra space. 32ms, 62.87 Mb.
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int cur = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // keep elements in [i-(k-1), i], size k
            while (!q.isEmpty() && q.peek() < i - k + 1) q.removeFirst();
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) q.removeLast();
            q.add(i);
            if (i >= k - 1) res[cur++] = nums[q.peek()];
        }
        return res;
    }
}
