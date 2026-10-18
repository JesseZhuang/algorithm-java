package dp;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * LeetCode 1696, Jump Game VI: maximize the score when jumping up to k positions forward.
 */
public final class JumpGameVI {
    private JumpGameVI() {
    }

    /** O(n) time and O(n) space. */
    public static int maxResultDeque(int[] nums, int k) {
        int[] best = new int[nums.length];
        best[0] = nums[0];
        Deque<Integer> candidates = new ArrayDeque<>();
        candidates.addLast(0);

        for (int index = 1; index < nums.length; index++) {
            while (!candidates.isEmpty() && candidates.peekFirst() < index - k) {
                candidates.removeFirst();
            }
            best[index] = nums[index] + best[candidates.peekFirst()];
            while (!candidates.isEmpty() && best[candidates.peekLast()] <= best[index]) {
                candidates.removeLast();
            }
            candidates.addLast(index);
        }
        return best[nums.length - 1];
    }

    /** O(n log n) time and O(n) space. */
    public static int maxResultPriorityQueue(int[] nums, int k) {
        PriorityQueue<int[]> candidates = new PriorityQueue<>(
                Comparator.<int[]>comparingInt(candidate -> candidate[0]).reversed());
        candidates.add(new int[]{nums[0], 0});

        int best = nums[0];
        for (int index = 1; index < nums.length; index++) {
            while (candidates.peek()[1] < index - k) {
                candidates.remove();
            }
            best = nums[index] + candidates.peek()[0];
            candidates.add(new int[]{best, index});
        }
        return best;
    }
}
