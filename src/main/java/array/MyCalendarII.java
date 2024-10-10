package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 731, medium, tags: array, binary search, design, segment tree, prefix sum, ordered set.
 * <p>
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not
 * cause a triple booking.
 * <p>
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to
 * all the three events.).
 * <p>
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open
 * interval [start, end), the range of real numbers x such that start <= x < end.
 * <p>
 * Implement the MyCalendarTwo class:
 * <p>
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without
 * causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 * <p>
 * Explanation
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // return True, The event can be booked.
 * myCalendarTwo.book(50, 60); // return True, The event can be booked.
 * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
 * myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
 * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already
 * double booked.
 * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked
 * with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with
 * the second event.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= start < end <= 10^9
 * At most 1000 calls will be made to book.
 * <p>
 * Hint 1
 * Store two sorted lists of intervals: one list will be all times that are at least single booked, and another list
 * will be all times that are definitely double booked. If none of the double bookings conflict, then the booking
 * will succeed, and you should update your single and double bookings accordingly.
 */
@SuppressWarnings("unused")
public class MyCalendarII {
    // solution 1, line sweep more flexible. n, n.
    static class MyCalendarTwoI {
        private TreeMap<Integer, Integer> bCnt; // booking cnt
        private int maxBook; // max overlapping

        public MyCalendarTwoI() {
            bCnt = new TreeMap<>();
            maxBook = 2;
        }

        public boolean book(int start, int end) {
            bCnt.put(start, bCnt.getOrDefault(start, 0) + 1); // + indicates start
            bCnt.put(end, bCnt.getOrDefault(end, 0) - 1); // - indicates end
            int overlapped = 0;
            // Calculate the prefix sum of bookings. O(n) iterate in sequence.
            for (Map.Entry<Integer, Integer> entry : bCnt.entrySet()) {
                overlapped += entry.getValue();
                if (overlapped > maxBook) { // roll back
                    bCnt.put(start, bCnt.get(start) - 1);
                    bCnt.put(end, bCnt.get(end) + 1);
                    if (bCnt.get(start) == 0) bCnt.remove(start);
                    if (bCnt.get(end) == 0) bCnt.remove(end);
                    return false;
                }
            }
            return true;
        }
    }

    // solution 2, O(n) time and space. 46ms, 46.09mb.
    static class MyCalendarTwo {
        private List<int[]> booked; // booked
        private List<int[]> dBooked; // double booked time intervals

        public MyCalendarTwo() {
            booked = new ArrayList<>();
            dBooked = new ArrayList<>();
        }

        // Return true if the booking [start1, end1) & [start2, end2) overlaps.
        private boolean overlap(int start1, int end1, int start2, int end2) {
            return Math.max(start1, start2) < Math.min(end1, end2);
        }

        // Return overlapping booking between [start1, end1) & [start2, end2).
        private int[] getOverlap(int start1, int end1, int start2, int end2) {
            return new int[]{Math.max(start1, start2), Math.min(end1, end2)};
        }

        public boolean book(int start, int end) {
            for (int[] booking : dBooked)
                if (overlap(booking[0], booking[1], start, end)) return false;
            for (int[] booking : booked)
                if (overlap(booking[0], booking[1], start, end))
                    dBooked.add(getOverlap(booking[0], booking[1], start, end));
            booked.add(new int[]{start, end});
            return true;
        }
    }
}
