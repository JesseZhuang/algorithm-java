package binary_search;

import java.util.Arrays;

/**
 * LeetCode 2251, hard, tags: array, binary search, sorting, prefix sum, ordered set.
 * <p>
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [start_i, end_i] means the ith flower
 * will be in full bloom from start_i to end_i (inclusive). You are also given a 0-indexed integer array people,
 * where people[i] is the time that the ith person will arrive to see the flowers.
 * <p>
 * Return an integer array answer of size people.length, where answer[i] is the number of flowers that are in
 * full bloom when the ith person arrives.
 * <p>
 * Example 1:
 * Input: flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
 * Output: [1,2,2,2]
 * <p>
 * Example 2:
 * Input: flowers = [[1,10],[3,3]], people = [3,3,2]
 * Output: [2,2,1]
 * <p>
 * Constraints:
 * 1 <= flowers.length <= 5 * 10^4
 * flowers[i].length == 2
 * 1 <= start_i <= end_i <= 10^9
 * 1 <= people.length <= 5 * 10^4
 * 1 <= people[i] <= 10^9
 */
public final class FlowersInFullBloom {

    private FlowersInFullBloom() {
    }

    /**
     * Binary Search. O((n + q) log n) time, O(n) space.
     * Sort starts and ends separately. For each person at time t:
     * count = (flowers started <= t) - (flowers ended < t) = bisect_right(starts, t) - bisect_left(ends, t).
     */
    public static int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) { // O(n)
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }
        Arrays.sort(starts); // O(n log n)
        Arrays.sort(ends);   // O(n log n)

        int q = people.length;
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // O(q log n)
            int t = people[i];
            // number of flowers that have started by time t (start <= t)
            int started = bisectRight(starts, t);
            // number of flowers that have ended before time t (end < t, i.e., end <= t-1)
            int ended = bisectLeft(ends, t);
            result[i] = started - ended;
        }
        return result;
    }

    /** Returns index of first element > val (upper bound). */
    private static int bisectRight(int[] arr, int val) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= val) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    /** Returns index of first element >= val (lower bound). */
    private static int bisectLeft(int[] arr, int val) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < val) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    /**
     * Sweep Line. O((n + q) log(n + q)) time, O(n + q) space.
     * Create events (start, +1) and (end+1, -1). Sort events and queries together.
     * Sweep through to accumulate bloom count for each person.
     */
    public static int[] fullBloomFlowersSweep(int[][] flowers, int[] people) {
        int n = flowers.length;
        int q = people.length;

        // Build events: each flower contributes +1 at start, -1 at end+1
        int[][] events = new int[2 * n][2]; // O(n) space
        for (int i = 0; i < n; i++) {
            events[2 * i] = new int[]{flowers[i][0], 1};
            events[2 * i + 1] = new int[]{flowers[i][1] + 1, -1};
        }
        Arrays.sort(events, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        // Sort people by arrival time, keeping original indices
        Integer[] indices = new Integer[q]; // O(q) space
        for (int i = 0; i < q; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> Integer.compare(people[a], people[b])); // O(q log q)

        int[] result = new int[q];
        int ei = 0, count = 0;
        for (int idx : indices) { // O(n + q) total across all iterations
            int t = people[idx];
            // process all events with time <= t
            while (ei < events.length && events[ei][0] <= t) {
                count += events[ei][1];
                ei++;
            }
            result[idx] = count;
        }
        return result;
    }
}
