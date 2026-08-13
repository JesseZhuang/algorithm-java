package array;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/interval-list-intersections/">LeetCode 986</a>, medium,
 * tags: array, two pointers.
 * <p>
 * You are given two lists of closed intervals, firstList and secondList, where
 * firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is
 * pairwise disjoint and in sorted order. Return the intersection of these two interval lists.
 * <p>
 * Example 1:
 * <p>
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[13,12],[15,23],[24,24],[25,25]]
 * <p>
 * Example 2:
 * <p>
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 10^9
 * 0 <= startj < endj <= 10^9
 * starti < starti+1
 * startj < startj+1
 */
public final class IntervalListIntersections {
    private IntervalListIntersections() {}

    /**
     * Two pointers. O(m+n) time, O(1) space excluding output.
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int lo = Math.max(firstList[i][0], secondList[j][0]);
            int hi = Math.min(firstList[i][1], secondList[j][1]);
            if (lo <= hi) res.add(new int[]{lo, hi});
            if (firstList[i][1] < secondList[j][1]) i++;
            else j++;
        }
        return res.toArray(new int[0][]);
    }
}
