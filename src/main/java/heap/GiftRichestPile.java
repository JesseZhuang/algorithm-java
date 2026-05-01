package heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LeetCode 2558, easy, tags: array, heap, simulation.
 */
@SuppressWarnings("unused")
public final class GiftRichestPile {

    private GiftRichestPile() {
    }

    // O(n + k log n) time, O(n) space.
    static class Solution {
        public static long pickGifts(int[] gifts, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int g : gifts) pq.offer(g);
            while (k-- > 0) {
                int cur = pq.poll();
                pq.offer((int) Math.sqrt(cur));
            }
            long res = 0;
            while (!pq.isEmpty()) res += pq.poll();
            return res;
        }
    }
}
