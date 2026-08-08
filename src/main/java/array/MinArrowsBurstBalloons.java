package array;

import java.util.Arrays;

/**
 * LeetCode 452 - Minimum Number of Arrows to Burst Balloons.
 */
public final class MinArrowsBurstBalloons {

    private MinArrowsBurstBalloons() {
    }

    /**
     * Sort by end coordinate, greedily shoot at each balloon's rightmost endpoint.
     */
    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        // O(n log n)
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int arrowPos = points[0][1];

        // O(n)
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > arrowPos) {
                arrows++;
                arrowPos = points[i][1];
            }
        }
        return arrows;
    }

    /**
     * Sort by start coordinate, merge overlapping intervals tracking the common overlap region.
     */
    public static int findMinArrowShots2(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        // O(n log n)
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int arrows = 1;
        int overlapEnd = points[0][1];

        // O(n)
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > overlapEnd) {
                // No overlap, need a new arrow
                arrows++;
                overlapEnd = points[i][1];
            } else {
                // Shrink the overlap region
                overlapEnd = Math.min(overlapEnd, points[i][1]);
            }
        }
        return arrows;
    }
}
