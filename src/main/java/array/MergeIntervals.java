package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * LeetCode 56, medium, tags: array, sorting.
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class MergeIntervals {
    // 10ms, 47 Mb. O(NLgN) time, O(LgN) space for sorting (assuming quick sort in place). Not including result space.
    // Another idea for identifying connected components and merging. O(N^2) time and space.
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> result = new LinkedList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > result.getLast()[1]) result.add(intervals[i]);
            else result.getLast()[1] = Math.max(result.getLast()[1], intervals[i][1]);
        }
        return result.toArray(int[][]::new); // int[][]::new for java 11+, new int[0][0] for lower versions
    }
}
