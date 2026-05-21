package heap;

import java.util.PriorityQueue;

// leet 2462
@SuppressWarnings("unused")
public final class TotalCostHireKWorkers {
    private TotalCostHireKWorkers() {
    }

    // Two PriorityQueues: front and back — O(k log candidates) time
    static class Solution1 {
        public long totalCost(int[] costs, int k, int candidates) {
            int n = costs.length;
            PriorityQueue<int[]> front = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            PriorityQueue<int[]> back = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            int lo = 0, hi = n - 1;
            for (int i = 0; i < candidates && lo <= hi; i++) front.add(new int[]{costs[lo], lo++});
            for (int i = 0; i < candidates && lo <= hi; i++) back.add(new int[]{costs[hi], hi--});
            long total = 0;
            while (k-- > 0) {
                int[] f = front.peek(), b = back.peek();
                if (b == null || (f != null && f[0] <= b[0])) {
                    total += front.poll()[0];
                    if (lo <= hi) front.add(new int[]{costs[lo], lo++});
                } else {
                    total += back.poll()[0];
                    if (lo <= hi) back.add(new int[]{costs[hi], hi--});
                }
            }
            return total;
        }
    }

    // Single PriorityQueue with side tracking
    static class Solution2 {
        public long totalCost(int[] costs, int k, int candidates) {
            int n = costs.length;
            // entries: [cost, index, side] where side 0=front, 1=back
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0];
                return a[1] - b[1];
            });
            int lo = 0, hi = n - 1;
            for (int i = 0; i < candidates && lo <= hi; i++) pq.add(new int[]{costs[lo], lo, 0});
            lo--;
            // careful: need to track separately
            // restart with clean pointers
            pq.clear();
            lo = 0;
            hi = n - 1;
            for (int i = 0; i < candidates && lo <= hi; i++) {
                pq.add(new int[]{costs[lo], lo++, 0});
            }
            for (int i = 0; i < candidates && lo <= hi; i++) {
                pq.add(new int[]{costs[hi], hi--, 1});
            }
            long total = 0;
            while (k-- > 0) {
                int[] top = pq.poll();
                total += top[0];
                if (lo <= hi) {
                    if (top[2] == 0) {
                        pq.add(new int[]{costs[lo], lo++, 0});
                    } else {
                        pq.add(new int[]{costs[hi], hi--, 1});
                    }
                }
            }
            return total;
        }
    }
}
