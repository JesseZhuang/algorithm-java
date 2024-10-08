package tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 729, medium, tags: array, segment tree, sorted set, binary search, design.
 * <p>
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause
 * a double booking.
 * <p>
 * A double booking happens when two events have some non-empty intersection (i.e., some moment is common to
 * both events.).
 * <p>
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open
 * interval [start, end), the range of real numbers x such that start <= x < end.
 * <p>
 * Implement the MyCalendar class:
 * <p>
 * MyCalendar() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without
 * causing a double booking. Otherwise, return false and do not add the event to the calendar.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * Output
 * [null, true, false, true]
 * <p>
 * Explanation
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
 * myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20,
 * but not including 20.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= start < end <= 10^9
 * At most 1000 calls will be made to book.
 * <p>
 * Hint 1
 * Store the events as a sorted list of intervals. If none of the events conflict, then the new event can be added.
 */
@SuppressWarnings("unused")
public class MyCalendarI {
    // solution 1, tree map. nlgn, n. 21ms, 45.8mb.
    static class MyCalendar {
        TreeMap<Integer, Integer> booked;

        MyCalendar() {
            booked = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Map.Entry<Integer, Integer> prev = booked.floorEntry(start), next = booked.ceilingEntry(start);
            if ((prev != null && prev.getValue() > start) ||
                    (next != null && end > next.getKey())) return false;
            booked.put(start, end);
            return true;
        }
    }
}
