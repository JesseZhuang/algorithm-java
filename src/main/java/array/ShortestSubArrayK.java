package array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 862, hard, tags: array, binary search, queue, sliding window, heap, prefix sum, monotonic queue.
 */
@SuppressWarnings("unused")
public class ShortestSubArrayK {
    static class Solution {
        public int shortestSubarray(int[] A, int K) {
            int N = A.length, res = N + 1;
            long[] sum = new long[N + 1];
            for (int i = 0; i < N; i++) sum[i + 1] = sum[i] + A[i];
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < N + 1; i++) {
                while (!dq.isEmpty() && sum[i] - sum[dq.getFirst()] >= K) res = Math.min(res, i - dq.removeFirst());
                while (!dq.isEmpty() && sum[i] <= sum[dq.getLast()]) dq.removeLast();
                dq.addLast(i);
            }
            return res <= N ? res : -1;
        }
    }
}
