package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 56, medium, tags: array, sorting.
 * <p>
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
@SuppressWarnings("unused")
public class MergeIntervals {
    // O(nlgn) time, O(n) space. sort by start, iterate, merge overlapping.
    static class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            List<int[]> res = new ArrayList<>();
            for (int[] cur : intervals) {
                if (!res.isEmpty() && cur[0] <= res.getLast()[1]) {
                    res.getLast()[1] = Math.max(res.getLast()[1], cur[1]);
                } else {
                    res.add(new int[]{cur[0], cur[1]});
                }
            }
            return res.toArray(new int[0][]);
        }
    }
}
