package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * LeetCode 253, medium, LintCode 919, tags: treemap, heap.
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: 1
 * <p>
 * Hint 1: Think about how we would approach this problem in a very simplistic way.
 * We will allocate rooms to meetings that occur earlier in the day v/s the ones that occur later on, right?
 * Hint 2: If you've figured out that we have to sort the meetings by their start time,
 * the next thing to think about is how do we do the allocation?
 * There are two scenarios possible here for any meeting. Either there is no meeting room available and
 * a new one has to be allocated, or a meeting room has freed up and this meeting can take place there.
 * Hint 3: An important thing to note is that we don't really care which room gets freed up while
 * allocating a room for the current meeting. As long as a room is free, our job is done.
 * We already know the rooms we have allocated till now and we also know when are they due to get free because
 * of the end times of the meetings going on in those rooms. We can simply check the room which is due to
 * get vacated the earliest amongst all the allocated rooms.
 * Hint 4: Following up on the previous hint, we can make use of a min-heap to store the end times of
 * the meetings in various rooms. So, every time we want to check if any room is free or not,
 * simply check the topmost element of the min heap as that would be the room that would
 * get free the earliest out of all the other rooms currently occupied.
 * If the room we extracted from the top of the min heap isn't free,
 * then no other room is. So, we can save time here and simply allocate a new room.
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 10^4
 * 0 <= start_i < end_i <= 10^6
 */
public class MeetingRoomsII {
    // solution 1, lint code 266ms, 20.37Mb.
    // O(NLgN) time, O(max(LgN,k) space, k is min number of meeting rooms, worst case K == N
    public int minMeetingRoomsHeap(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0])); // do not forget intervals
        Queue<Integer> heap = new PriorityQueue<>();
        heap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= heap.peek()) heap.remove(); // meeting in heap ended, note >=
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }

    // solution 2, O(NLgN) time, O(N) space. lint code, 325ms, 20.21Mb.
    public int minMeetingRoomsTreeMap(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>(); // store start and end times and counts
        for (int[] interval : intervals) {
            map.put(interval[0], 1 + map.getOrDefault(interval[0], 0)); // take 1 room
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1); // free 1 room
        }
        int active = 0, result = 0;
        for (int v : map.values()) { // sweep line
            active += v;
            result = Math.max(result, active);
        }
        return result;
    }

    // solution 3, O(NLgN) time, O(N) space. lint code, 363ms, 20.32Mb.
    public int minMeetingRoomsSort(int[][] intervals) {
        int[] starts = new int[intervals.length], ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0, endPointer = 0;
        for (int i = 0; i < intervals.length; i++) { // seep line
            if (starts[i] < ends[endPointer]) rooms++; // occupy one more room on conflict
            else endPointer++; // meeting[endPointer] ended, take that room
        }
        return rooms;
    }

}
