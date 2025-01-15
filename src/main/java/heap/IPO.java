package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// leet 502, lint 1202
@SuppressWarnings("unused")
public class IPO {
    // 104 ms, 59.19 mb
    static class Solution {
        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            int n = profits.length;
            ArrayList<int[]> p = new ArrayList<>(n);
            for (int i = 0; i < n; i++) p.add(new int[]{capital[i], profits[i]});
            p.sort(Comparator.comparingInt(a -> a[0]));
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            int i = 0;
            while (k-- > 0) {
                while (i < n && p.get(i)[0] <= w) pq.add(p.get(i++)[1]);
                if (pq.isEmpty()) break;
                else w += pq.remove();
            }
            return w;
        }
    }
}
