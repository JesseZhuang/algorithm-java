package array;

import tree.SegmentTreeAI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * LeetCode 2158, hard, tags: array, segment tree, ordered set.
 * https://leetcode.ca/2022-02-19-2158-Amount-of-New-Area-Painted-Each-Day/
 * https://algo.monster/liteproblems/2158
 * https://walkccc.me/LeetCode/problems/2158/
 * <p>
 * There is a long and thin painting that can be represented by a number line. You are given a 0-indexed 2D integer array paint of length n, where paint[i] = [starti, endi]. This means that on the ith day you need to paint the area between starti and endi.
 * <p>
 * Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting at most once.
 * <p>
 * Return an integer array worklog of length n, where worklog[i] is the amount of new area that you painted on the ith day.
 * <p>
 * Example 1:
 * <p>
 * Input: paint = [[1,4],[4,7],[5,8]]
 * Output: [3,3,1]
 * Explanation:
 * On day 0, paint everything between 1 and 4.
 * The amount of new area painted on day 0 is 4 - 1 = 3.
 * On day 1, paint everything between 4 and 7.
 * The amount of new area painted on day 1 is 7 - 4 = 3.
 * On day 2, paint everything between 7 and 8.
 * Everything between 5 and 7 was already painted on day 1.
 * The amount of new area painted on day 2 is 8 - 7 = 1.
 * Example 2:
 * <p>
 * Input: paint = [[1,4],[5,8],[4,7]]
 * Output: [3,3,1]
 * Explanation:
 * On day 0, paint everything between 1 and 4.
 * The amount of new area painted on day 0 is 4 - 1 = 3.
 * On day 1, paint everything between 5 and 8.
 * The amount of new area painted on day 1 is 8 - 5 = 3.
 * On day 2, paint everything between 4 and 5.
 * Everything between 5 and 7 was already painted on day 1.
 * The amount of new area painted on day 2 is 5 - 4 = 1.
 * Example 3:
 * <p>
 * Input: paint = [[1,5],[2,4]]
 * Output: [4,0]
 * Explanation:
 * On day 0, paint everything between 1 and 5.
 * The amount of new area painted on day 0 is 5 - 1 = 4.
 * On day 1, paint nothing because everything between 2 and 4 was already painted on day 0.
 * The amount of new area painted on day 1 is 0.
 * <p>
 * Constraints:
 * <p>
 * 1 <= paint.length <= 10^5, n
 * paint[i].length == 2
 * 0 <= start_i < end_i <= 5 * 10^4, m
 */
public class NewAreaPainted {
    // solution 1, treeset, (m+n)lg m time, m space.
    public int[] amountPainted1(int[][] paint) {
        int l = paint.length, res[] = new int[l];
        TreeSet<Integer> unpainted = new TreeSet<>();
        ;
        for (int i = 0; i < 50000; i++) unpainted.add(i); // mlgm time
        for (int i = 0; i < l; i++) { // n time
            int left = paint[i][0], right = paint[i][1];
            // Repeatedly delete the first element >= left until it becomes >= right
            // This clears values in [left, right) from the TreeSet
            while (true) {
                int next = unpainted.ceiling(left); // O(lg m)
                if (next >= right) break;
                unpainted.remove(next);
                res[i]++;
            }
        }
        return res;
    }

    // segment tree idea, nm+nlgm time, can be improved to (m+n)lgm, m space.
    public int[] amountPainted2(int[][] paint) {
        int len = paint.length, res[] = new int[len];
        SegmentTreeAI tree = new SegmentTreeAI(new int[50001]);
        for (int i = 0; i < len; i++) { // O(n)
            int l = paint[i][0], r = paint[i][1] - 1;
            int v = tree.sumRange(l, r); // O(lgm)
            res[i] = r - l - v;
            // check if value is already 1 before update, improve time complexity
            for (int j = l; j <= r; j++) tree.update(j, 1);
        }
        return res;
    }

    // sort, nlgn time, n space.
    public int[] amountPainted3(int[][] paint) {
        final int n = paint.length;
        final int min = Arrays.stream(paint).mapToInt(x -> x[0]).min().getAsInt(); // n time
        final int max = Arrays.stream(paint).mapToInt(x -> x[1]).max().getAsInt(); // n time
        int[] res = new int[n];
        // Stores the indices of paints that are available now.
        TreeSet<Integer> runningIndices = new TreeSet<>(); // n space
        List<int[]> events = new ArrayList<>(); // value, index, type 1:start, -1:end, n space
        for (int i = 0; i < n; ++i) {
            int start = paint[i][0], end = paint[i][1];
            events.add(new int[]{start, i, 1});
            events.add(new int[]{end, i, -1});
        }
        Collections.sort(events, Comparator.comparingInt(a -> a[1])); // sort by (start or end), nlgn time
        int i = 0; // events' index
        for (int j = min; j < max; ++j) { // m
            while (i < events.size() && events.get(i)[0] == j) {
                if (events.get(i)[2] == 1) runningIndices.add(events.get(i)[1]);
                else runningIndices.remove(events.get(i)[1]);
                ++i;
            }
            if (!runningIndices.isEmpty()) ++res[runningIndices.first()]; // lgm
        }

        return res;
    }

}
