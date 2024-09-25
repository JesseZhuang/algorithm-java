package array;

import java.util.List;

/**
 * LeetCode 539, medium, tags: array, math, string, sorting.
 * <p>
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes
 * difference between any two time-points in the list.
 * <p>
 * Example 1:
 * <p>
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 * Example 2:
 * <p>
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= timePoints.length <= 2 * 10^4
 * timePoints[i] is in the format "HH:MM".
 */
@SuppressWarnings("unused")
public class MinTimeDiff {
    // solution 1, bucket/counting sort, n, 1(24*60<<n). 3ms, 45.32mb.
    public int findMinDifference(List<String> timePoints) {
        // create buckets array for the times converted to minutes
        int mDay = 24 * 60; // minutes in a day, var name cannot start with number
        boolean[] minutes = new boolean[mDay];
        for (String time : timePoints) {
            int min = Integer.parseInt(time.substring(0, 2)) * 60 +
                    Integer.parseInt(time.substring(3)); // not 2, do not include :
            if (minutes[min]) return 0;
            minutes[min] = true;
        }
        int prev = -1, first = -1, last = -1, res = Integer.MAX_VALUE;
        // find differences between adjacent elements in sorted array
        for (int i = 0; i < mDay; i++) {
            if (!minutes[i]) continue;
            if (prev != -1) res = Math.min(res, i - prev);
            if (first == -1) first = i;
            prev = i;
            last = i;
        }
        return Math.min(res, mDay - last + first);
    }
}
