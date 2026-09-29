package array;

import java.util.Arrays;

/**
 * LeetCode 1094 - Car Pooling.
 * Given trips[i] = [numPassengers, from, to] and capacity,
 * return whether it's possible to pick up and drop off all passengers.
 */
public final class CarPooling {
    private CarPooling() {}

    /**
     * Difference array approach.
     * Time O(n + 1001), Space O(1001).
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001]; // O(1001) space — stops bounded [0, 1000]
        for (int[] t : trips) { // O(n)
            diff[t[1]] += t[0]; // add passengers at pickup
            diff[t[2]] -= t[0]; // subtract passengers at drop-off
        }
        int curr = 0;
        for (int d : diff) { // O(1001)
            curr += d;
            if (curr > capacity) return false;
        }
        return true;
    }

    /**
     * Sorted events sweep approach.
     * Time O(n log n), Space O(n).
     */
    public static boolean carPooling2(int[][] trips, int capacity) {
        int[][] events = new int[trips.length * 2][2]; // O(n) space
        int idx = 0;
        for (int[] t : trips) { // O(n)
            events[idx++] = new int[]{t[1], t[0]};  // pickup: +passengers
            events[idx++] = new int[]{t[2], -t[0]}; // drop-off: -passengers
        }
        // Sort by location; ties broken by delta so drop-offs come before pickups
        Arrays.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]); // O(n log n)
        int curr = 0;
        for (int[] e : events) { // O(n)
            curr += e[1];
            if (curr > capacity) return false;
        }
        return true;
    }
}
