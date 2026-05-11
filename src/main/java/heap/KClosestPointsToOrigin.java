package heap;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * LeetCode 973, medium, tags: array, divide and conquer, geometry, sorting, heap, quick select.
 * <p>
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 * <p>
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., sqrt(x^2 + y^2)).
 * <p>
 * You may return the answer in any order. The answer is guaranteed to be unique
 * (except for the order that it is in).
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation: The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8). Since sqrt(8) < sqrt(10),
 * (-2, 2) is closer to the origin. We only want the closest k = 1 points from the origin,
 * so the answer is just [[-2,2]].
 * <p>
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= points.length <= 10^4
 * -10^4 <= xi, yi <= 10^4
 */
@SuppressWarnings("unused")
public final class KClosestPointsToOrigin {

    private KClosestPointsToOrigin() {
    }

    // solution 1, max-heap of size k. O(n log k) time: iterate n points, each heap op O(log k). O(k) space.
    public static int[][] kClosestHeap(int[][] points, int k) {
        // max-heap by squared distance; evict farthest when size exceeds k
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> distSq(b) - distSq(a)
        );
        for (int[] p : points) { // O(n log k): iterate n, each heap op O(log k)
            maxHeap.offer(p);
            if (maxHeap.size() > k) maxHeap.poll(); // evict farthest, keep k closest
        }
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) result[i] = maxHeap.poll();
        return result;
    }

    // solution 2, quickselect with random pivot. O(n) average time, O(n^2) worst case. O(1) extra space.
    public static int[][] kClosestQuickSelect(int[][] points, int k) {
        Random rand = new Random();
        int lo = 0, hi = points.length - 1;
        while (lo < hi) { // O(n) average: each partition halves search space
            int pi = partition(points, lo, hi, rand);
            if (pi == k) break;
            else if (pi < k) lo = pi + 1;
            else hi = pi - 1;
        }
        int[][] result = new int[k][2];
        System.arraycopy(points, 0, result, 0, k);
        return result;
    }

    private static int partition(int[][] points, int lo, int hi, Random rand) {
        int pivotIdx = lo + rand.nextInt(hi - lo + 1);
        int pivotDist = distSq(points[pivotIdx]);
        swap(points, pivotIdx, hi); // move pivot to end
        int storeIdx = lo;
        for (int i = lo; i < hi; i++) {
            if (distSq(points[i]) < pivotDist) {
                swap(points, storeIdx++, i);
            }
        }
        swap(points, storeIdx, hi); // move pivot to final position
        return storeIdx;
    }

    private static void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private static int distSq(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
