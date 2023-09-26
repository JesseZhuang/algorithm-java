package array;


import util.Pair;

import java.util.HashMap;
import java.util.Map;

import static math.Euclidean.gcd;

/**
 * LeetCode 149, hard, tags: array, hash table, math, geometry.
 * <p>
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum
 * number of points that lie on the same straight line.
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 * Example 2:
 * <p>
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * All the points are unique.
 */
public class MaxPointsLine {
    // O(N^2) time and space, 21ms, 43.6 Mb. Use string as map key, 33 ms.
    public int maxPoints(int[][] points) {
        int result = 0, n = points.length;
        for (int i = 0; i < n; i++) {
            Map<Pair<Integer, Integer>, Integer> map = new HashMap();
            int overlap = 0, max = 0;
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0]; // bug points[j][0] - points[j][0] ...
                int dy = points[j][1] - points[i][1];
                if (dx == 0 && dy == 0) {
                    overlap++;
                    continue;
                }

                int gcd = gcd(dx, dy);
                if (gcd != 0) {
                    dx /= gcd;
                    dy /= gcd;
                }

                Pair<Integer, Integer> line = new Pair(dx, dy);
                map.put(line, map.containsKey(line) ? map.get(line) + 1 : 1);
                max = Math.max(max, map.get(line));
            }
            result = Math.max(result, max + overlap + 1);
        }
        return result;
    }
}
