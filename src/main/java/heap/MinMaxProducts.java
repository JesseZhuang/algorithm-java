package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 2064, medium.
 * <p>
 * let k = max(quantities)
 */
@SuppressWarnings("unused")
public class MinMaxProducts {
    // todo editorial
    static class Solution1 {
        public int minimizedMaximum(int n, int[] A) {
            int m = A.length;
            // [quantity,cnt]
            Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> (double) a[0] / a[1]));
            for (int q : A) pq.add(new int[]{q, 1});
            for (int i = 0; i < n - m; i++) {
                int[] pair = pq.remove();
                int q = pair[0], cnt = pair[1];
                pq.offer(new int[]{q, cnt + 1}); // add one store for this type
            }
            // Pop the first element
            int[] pairWithMaxRatio = pq.poll();
            int totalQuantityOfType = pairWithMaxRatio[0];
            int storesAssignedToType = pairWithMaxRatio[1];

            // Return the maximum minimum ratio
            return (int) Math.ceil(
                    (double) totalQuantityOfType / storesAssignedToType
            );
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
