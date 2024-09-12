package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 2402, hard, tags: array, heap, hash table, sorting, simulation.
 * <p>
 * You are given an integer n. There are n rooms numbered from 0 to n - 1.
 * <p>
 * You are given a 2D integer array meetings where meetings[i] = [start_i, end_i] means that a meeting will be held
 * during the half-closed time interval [start_i, end_i). All the values of start_i are unique.
 * <p>
 * Meetings are allocated to rooms in the following manner:
 * <p>
 * Each meeting will take place in the unused room with the lowest number.
 * If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should
 * have the same duration as the original meeting.
 * When a room becomes unused, meetings that have an earlier original start time should be given the room.
 * Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the
 * lowest number.
 * <p>
 * A half-closed interval [a, b) is the interval between a and b including a and not including b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * Output: 0
 * Explanation:
 * - At time 0, both rooms are not being used. The first meeting starts in room 0.
 * - At time 1, only room 1 is not being used. The second meeting starts in room 1.
 * - At time 2, both rooms are being used. The third meeting is delayed.
 * - At time 3, both rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
 * - At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
 * Both rooms 0 and 1 held 2 meetings, so we return 0.
 * Example 2:
 * <p>
 * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * Output: 1
 * Explanation:
 * - At time 1, all three rooms are not being used. The first meeting starts in room 0.
 * - At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
 * - At time 3, only room 2 is not being used. The third meeting starts in room 2.
 * - At time 4, all three rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
 * - At time 6, all three rooms are being used. The fifth meeting is delayed.
 * - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
 * Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * 1 <= meetings.length <= 10^5, m
 * meetings[i].length == 2
 * 0 <= start_i < end_i <= 5 * 10^5
 * All the values of start_i are unique.
 * <p>
 * Hint 1
 * Sort meetings based on start times.
 * Hint 2
 * Use two min heaps, the first one keeps track of the numbers of all the rooms that are free.
 * The second heap keeps track of the end times of all the meetings that are happening and the room that they are in.
 * Hint 3
 * Keep track of the number of times each room is used in an array.
 * Hint 4
 * With each meeting, check if there are any free rooms. If there are, then use the room with the smallest number.
 * Otherwise, assign the meeting to the room whose meeting will end the soonest.
 */
public class MeetingRoomsIII {

    // O(mlgm+mlgn) time, O(n+nlgn) space. 74ms, 97.6Mb.
    static class Solution1 {
        public int mostBooked(int n, int[][] meetings) {
            int[] count = new int[n];
            PriorityQueue<long[]> used = new PriorityQueue<>( // sort by end time then room id
                    (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));
            PriorityQueue<Integer> unused = new PriorityQueue<>();
            for (int i = 0; i < n; i++) unused.offer(i); // init do not forget
            Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
            for (int[] m : meetings) {
                int start = m[0], end = m[1];
                while (!used.isEmpty() && used.peek()[0] <= start) {
                    int room = (int) used.remove()[1];
                    unused.add(room); // free the available rooms
                }
                int room;
                if (!unused.isEmpty()) { // use the first room available
                    room = unused.remove();
                    used.add(new long[]{end, room});
                } else { // delay this meeting
                    long[] first = used.remove();
                    long firstEnd = first[0];
                    room = (int) first[1];
                    used.offer(new long[]{firstEnd + end - start, room});
                }
                count[room]++;
            }
            int maxId = 0;
            for (int i = 1; i < n; i++) if (count[i] > count[maxId]) maxId = i;
            return maxId;
        }
    }

    // sort, 52ms, 100.27Mb. O(mlgm+mn) time, O(n+sort) space.
    // In Python, the sort method sorts a list using the Timsort algorithm which is a combination of Merge Sort
    // and Insertion Sort and has a space complexity of O(N).
    // In C++, the sort() function is implemented as a hybrid of Quick Sort, Heap Sort, and Insertion Sort,
    // with a worst-case space complexity of O(logN).
    // In Java, Arrays.sort() is implemented using a variant of the Quick Sort algorithm which has
    // a space complexity of O(logN).
    static class Solution2 {
        public int mostBooked(int n, int[][] meetings) {
            long[] endTime = new long[n]; // room's meeting end time
            int[] count = new int[n]; // room's meeting count
            Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
            for (int[] m : meetings) {
                int start = m[0], end = m[1];
                long first = Long.MAX_VALUE;// first available rime
                int minInd = -1;
                boolean found = false; // found room
                for (int i = 0; i < n; i++) {
                    if (endTime[i] <= start) {
                        found = true;
                        count[i]++;
                        endTime[i] = end;
                        break;
                    }
                    if (first > endTime[i]) {
                        first = endTime[i];
                        minInd = i;
                    }
                }
                if (!found) {
                    endTime[minInd] += end - start;
                    count[minInd]++;
                }
            }
            int maxCnt = -1, res = -1;
            for (int i = 0; i < n; i++) {
                if (count[i] > maxCnt) {
                    maxCnt = count[i];
                    res = i;
                }
            }
            return res;
        }
    }

}
