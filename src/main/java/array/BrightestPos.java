package array;

import java.util.TreeMap;

import static util.CP.chmax;

/**
 * LeetCode 2021, LintCode 3745, medium, tags: array, ordered set, prefix sum.
 * <p>
 * A perfectly straight street is represented by a number line. The street has streetlamp(s) on it and is represented
 * by a 2D integer array lights. Each lights[i] = [positioni, rangei] indicates that there is a streetlamp at
 * position positioni that lights up the area from [positioni - rangei, positioni + rangei] (inclusive).
 * <p>
 * The brightness of a position p is defined as the number of streetlamp that light up the position p.
 * <p>
 * Given lights, return the brightest position on the street. If there are multiple brightest positions, return
 * the smallest one.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: lights = [[-3,2],[1,2],[3,3]]
 * Output: -1
 * Explanation:
 * The first streetlamp lights up the area from [(-3) - 2, (-3) + 2] = [-5, -1].
 * The second streetlamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].
 * The third streetlamp lights up the area from [3 - 3, 3 + 3] = [0, 6].
 * Position -1 has a brightness of 2, illuminated by the first and second streetlight.
 * Positions 0, 1, 2, and 3 have a brightness of 2, illuminated by the second and third streetlight.
 * Out of all these positions, -1 is the smallest, so return it.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: lights = [[1,0],[0,1]]
 * Output: 1
 * Explanation:
 * The first streetlamp lights up the area from [1 - 0, 1 + 0] = [1, 1].
 * The second streetlamp lights up the area from [0 - 1, 0 + 1] = [-1, 1].
 * <p>
 * Position 1 has a brightness of 2, illuminated by the first and second streetlight.
 * Return 1 because it is the brightest position on the street.
 * Example 3:
 * <p>
 * Input: lights = [[1,2]]
 * Output: -1
 * Explanation:
 * The first streetlamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].
 * <p>
 * Positions -1, 0, 1, 2, and 3 have a brightness of 1, illuminated by the first streetlight.
 * Out of all these positions, -1 is the smallest, so return it.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= lights.length <= 10^5, n
 * lights[i].length == 2
 * -10^8 <= positioni <= 10^8
 * 0 <= rangei <= 10^8
 */
@SuppressWarnings("unused")
public class BrightestPos {
    static class Solution {
        public int brightestPosition(int[][] lights) {
            TreeMap<Integer, Integer> d = new TreeMap<>(); // position -> difference array value
            for (var x : lights) {
                int l = x[0] - x[1], r = x[0] + x[1];
                d.merge(l, 1, Integer::sum);
                d.merge(r + 1, -1, Integer::sum);
            }
            int res = 0, s = 0, max = 0;
            for (var x : d.entrySet()) {
                int v = x.getValue();
                s += v; // prefix sum
                var p = chmax(max, s);
                max = p.getKey();
                if (p.getValue()) res = x.getKey();
            }
            return res;
        }
    }
}
