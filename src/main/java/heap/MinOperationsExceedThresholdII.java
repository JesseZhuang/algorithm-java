package heap;

import java.util.PriorityQueue;

public final class MinOperationsExceedThresholdII {
    private MinOperationsExceedThresholdII() {
    }

    // O(n log n) time, O(n) space.
    public static int minOperations(int[] nums, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer((long) num); // O(log n)
        }

        int operations = 0;
        while (minHeap.peek() < k) {
            long first = minHeap.poll(); // O(log n)
            long second = minHeap.poll(); // O(log n)
            minHeap.offer(2 * first + second); // O(log n)
            operations++;
        }
        return operations;
    }
}
