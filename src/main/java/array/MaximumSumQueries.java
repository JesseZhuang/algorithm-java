package array;

import java.util.Arrays;

/**
 * LeetCode 2736. Hard. Tags: array, sorting, binary indexed tree.
 * Offline descending sweep with a Fenwick tree of maximum sums.
 * Sorting costs O(n log n + q log q); the sweep costs O((n + q) log n).
 * O(n + q) extra space.
 */
public final class MaximumSumQueries {

    private MaximumSumQueries() {
    }

    public static int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = nums1[i];
            points[i][1] = nums2[i];
        }
        Arrays.sort(points, (a, b) -> Integer.compare(b[0], a[0]));

        int[] coordinates = nums2.clone();
        Arrays.sort(coordinates);
        int distinct = 0;
        for (int coordinate : coordinates) {
            if (distinct == 0 || coordinate != coordinates[distinct - 1]) {
                coordinates[distinct++] = coordinate;
            }
        }

        Integer[] order = new Integer[queries.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(queries[b][0], queries[a][0]));

        int[] maxima = new int[distinct + 1];
        Arrays.fill(maxima, -1);
        int[] answers = new int[queries.length];
        Arrays.fill(answers, -1);
        int next = 0;
        for (int queryIndex : order) {
            int[] query = queries[queryIndex];
            // Each point enters once; reversed ranks make b >= y a prefix query.
            while (next < n && points[next][0] >= query[0]) {
                int[] point = points[next++];
                int rank = distinct - lowerBound(coordinates, distinct, point[1]);
                int sum = point[0] + point[1];
                // Fenwick maximum update visits O(log n) ancestors.
                for (int i = rank; i <= distinct; i += i & -i) {
                    maxima[i] = Math.max(maxima[i], sum);
                }
            }
            int rank = distinct - lowerBound(coordinates, distinct, query[1]);
            // Prefix maximum visits O(log n) disjoint Fenwick ranges.
            for (int i = rank; i > 0; i -= i & -i) {
                answers[queryIndex] = Math.max(answers[queryIndex], maxima[i]);
            }
        }
        return answers;
    }

    // First coordinate >= target in O(log n), including targets absent from nums2.
    private static int lowerBound(int[] coordinates, int length, int target) {
        int left = 0;
        int right = length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (coordinates[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }
}
