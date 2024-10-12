package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 2406, medium, tags: array, two pointers, greedy, sorting, heap, prefix sum.
 * <p>
 * You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval
 * [lefti, righti].
 * <p>
 * You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no
 * two intervals that are in the same group intersect each other.
 * <p>
 * Return the minimum number of groups you need to make.
 * <p>
 * Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5]
 * and [5, 8] intersect.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * Output: 3
 * Explanation: We can divide the intervals into the following groups:
 * - Group 1: [1, 5], [6, 8].
 * - Group 2: [2, 3], [5, 10].
 * - Group 3: [1, 10].
 * It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
 * Example 2:
 * <p>
 * Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
 * Output: 1
 * Explanation: None of the intervals overlap, so we can put all of them in one group.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 10^5, n
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^6, k
 * <p>
 * Hint 1
 * Can you find a different way to describe the question?
 * Hint 2
 * The minimum number of groups we need is equivalent to the maximum number of intervals that overlap at some point.
 * How can you find that?
 */
@SuppressWarnings("unused")
public class DivIntervalMinGroups {
    // n + k, k. line sweep range bucket
    // another solution line sweep with treemap. nlgn, n
    static class Solution {
        public int minGroups(int[][] intervals) {
            int minS = Integer.MAX_VALUE; // min start time
            int maxE = Integer.MIN_VALUE; // max end time
            for (int[] interval : intervals) {
                minS = Math.min(minS, interval[0]);
                maxE = Math.max(maxE, interval[1]);
            }
            int[] pCnt = new int[maxE + 2]; // point on the line and count, start +1, end -1
            for (int[] interval : intervals) {
                pCnt[interval[0]]++; // Increment at the start of the interval
                pCnt[interval[1] + 1]--; // Decrement right after the end of the interval
            }
            int overlap = 0;
            int maxOverlap = 0;
            for (int i = minS; i <= maxE; i++) {
                overlap += pCnt[i];
                maxOverlap = Math.max(maxOverlap, overlap);
            }
            return maxOverlap;
        }
    }

    // sort, or use heap. nlgn, n.
    static class Solution2 {
        public int minGroups(int[][] intervals) {
            //intervals: start as {start, 1} and end as {end + 1, -1}
            List<int[]> events = new ArrayList<>();
            for (int[] interval : intervals) {
                events.add(new int[]{interval[0], 1}); // Start event
                events.add(new int[]{interval[1] + 1, -1}); // End event (interval[1] + 1)
            }
            // sort by time then by type (-1 end, 1 start), need <int[]) for chaining
            events.sort(Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));
            int overlap = 0;
            int maxOverlap = 0;
            for (int[] event : events) {
                overlap += event[1];
                maxOverlap = Math.max(maxOverlap, overlap);
            }
            return maxOverlap;
        }
    }
}
