package heap;

import java.util.PriorityQueue;

/**
 * LeetCode 2064, medium.
 * <p>
 * let k = max(quantities)
 * m == quantities.length
 * 1 <= m <= n <= 10^5
 * 1 <= quantities[i] <= 10^5
 */
@SuppressWarnings("unused")
public class MinMaxProducts {
    // Time O(m+(n-m)log_m), Space O(m).
    static class Solution1 {
        public int minimizedMaximum(int n, int[] A) {
            int m = A.length;
            // [quantity, store cnt] for each product type, b0/b1<a0/a1, i.e., b0*a1<a0*b1
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> Long.compare((long) b[0] * a[1], (long) a[0] * b[1]));
            for (int q : A) pq.add(new int[]{q, 1});
            for (int i = 0; i < n - m; i++) {
                int[] pair = pq.remove();
                int q = pair[0], cnt = pair[1];
                pq.offer(new int[]{q, cnt + 1}); // add one store for this type
            }
            int[] max = pq.remove(); // get the max ratio after all stores are assigned
            int q = max[0], cnt = max[1];
            return (q + cnt - 1) / cnt;
        }
    }


    // @lee(lee215), nlgk, 1
    static class Solution2 {
        public int minimizedMaximum(int n, int[] A) {
            int l = 1, r = 100000;
            while (l < r) { // bisect left, r still qualifies before converge, l still qualify after converge
                int mid = (l + r) / 2, sum = 0;
                for (int a : A) sum += (a + mid - 1) / mid;
                if (sum > n) l = mid + 1;
                else r = mid;
            }
            return l;
        }
    }
}
