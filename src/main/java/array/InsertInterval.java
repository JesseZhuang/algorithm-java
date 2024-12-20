package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 57 medium, tags: array.
 * You are given an array of non-overlapping intervals where intervals[i] = [start_i, end_i]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by start_i.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i and intervals
 * still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= start_i <= end_i <= 10^5
 * intervals is sorted by start_i in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 */
public class InsertInterval {
    // solution 1, 1ms, 44.9Mb. O(N) time and space. O(1) space not including the result.
    // another method, binary search to find the position, insert, then merge
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
//        return result.toArray(new int[result.size()][2]);
        return result.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(InsertInterval.insert(
                new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
        // {{1,2},{3,10},{12,16}}
    }
}
