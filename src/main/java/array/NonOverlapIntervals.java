package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 435, medium, tags: array, dynamic programming, greedy, sorting.
 * https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization
 * Given an array of intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class NonOverlapIntervals {
    // O(NLgN) time, O(1) space. 71ms , 101.8Mb. sort by right bound, count non-overlapping.
    public int eraseOverlapIntervals1(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[1])); // sort by interval right bound
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) continue;
            count++;
            end = intervals[i][1];
        }
        return intervals.length - count;
    }

    // sort by right bound, count overlapping. 73ms, 101.8Mb.
    public int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[1]));
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) count++;
            else end = intervals[i][1];
        }
        return count;
    }

    // 73ms, 101.6Mb. sort by left, count overlapping.
    public int eraseOverlapIntervals3(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int count = 0, pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[pre][1]) {
                count++;
                if (intervals[i][1] < intervals[pre][1]) pre = i;
            } else pre = i;
        }
        return count;
    }
}
