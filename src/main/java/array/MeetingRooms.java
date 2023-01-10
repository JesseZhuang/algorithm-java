package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 252, easy.
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: true
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get
 * new method signature.
 */
public class MeetingRooms {
    // O(NLgN) time, O(LgN) space for sorting.
    public boolean canAttend(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        for (int i = 0; i < intervals.length - 1; i++)
            if (intervals[i][1] > intervals[i + 1][0]) return false;
        return true;
    }
}
