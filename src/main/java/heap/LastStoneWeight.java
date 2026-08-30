package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1046, easy, tags: array, heap, simulation.
 * <p>
 * You have a collection of stones, each with a positive integer weight. Each turn, pick the two heaviest stones
 * and smash them together. If equal weight, both destroyed. Otherwise, lighter is destroyed and heavier loses the
 * lighter's weight. Return weight of last remaining stone (0 if none).
 * <p>
 * Constraints:
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
@SuppressWarnings("unused")
public final class LastStoneWeight {

    private LastStoneWeight() {
    }

    // solution 1, max-heap. O(n log n) time, O(n) space.
    public static int lastStoneWeightHeap(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones) maxHeap.offer(s);
        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();
            int second = maxHeap.poll();
            if (first != second) maxHeap.offer(first - second);
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    // solution 2, sorted list with insertion. O(n^2) time, O(n) space.
    public static int lastStoneWeightSort(int[] stones) {
        List<Integer> list = new ArrayList<>();
        for (int s : stones) list.add(s);
        Collections.sort(list);
        while (list.size() > 1) {
            int first = list.remove(list.size() - 1);
            int second = list.remove(list.size() - 1);
            int diff = first - second;
            if (diff > 0) {
                int pos = Collections.binarySearch(list, diff);
                if (pos < 0) pos = -(pos + 1);
                list.add(pos, diff);
            }
        }
        return list.isEmpty() ? 0 : list.get(0);
    }
}
