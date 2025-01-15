package heap;

import java.util.TreeMap;

/**
 * companies: salesforce.
 */
@SuppressWarnings("unused")
public class MeetingRoomsS {
    static class Solution {
        static int minResources(int[][] events, int capacity) {
            int count = 0, res = 0;
            TreeMap<Integer, Integer> map1 = new TreeMap<>();
            for (int[] e : events) {
                map1.merge(e[0], 1, Integer::sum);
                map1.merge(e[1], -1, Integer::sum);
            }
            for (int t : map1.keySet()) {
                count += map1.get(t);
                res = Math.max(res, (int) Math.ceil(count / (double) capacity));
            }
            return res;
        }
    }
}
