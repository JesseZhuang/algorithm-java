package heap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode 373, medium, tags: array, heap.
 * <p>
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 10^5
 * -10^9 <= nums1[i], nums2[j] <= 10^9
 * nums1 and nums2 are sorted in non-decreasing order.
 * 1 <= k <= 10^4
 */
public final class FindKPairsSmallestSums {

    private FindKPairsSmallestSums() {
    }

    /**
     * Solution 1: Min-heap. Push first min(k, nums1.length) rows with column 0,
     * then pop and advance column.
     * Time O(k log k), Space O(k).
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) return res;
        // pq stores {sum, i, j} where i indexes nums1, j indexes nums2
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int rows = Math.min(k, nums1.length); // O(k) initial entries
        for (int i = 0; i < rows; i++) {
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        while (!pq.isEmpty() && res.size() < k) { // O(k log k) total pops
            int[] cur = pq.poll();
            int i = cur[1], j = cur[2];
            res.add(List.of(nums1[i], nums2[j]));
            if (j + 1 < nums2.length) { // advance column
                pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        return res;
    }

    /**
     * Solution 2: BFS-like with visited set. Start from (0,0), expand right (i, j+1) and down (i+1, j).
     * Time O(k log k), Space O(k).
     */
    public static List<List<Integer>> kSmallestPairsBFS(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) return res;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<Long> visited = new HashSet<>(); // O(k) space for visited set
        pq.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(0L);
        while (!pq.isEmpty() && res.size() < k) { // O(k log k) total pops
            int[] cur = pq.poll();
            int i = cur[1], j = cur[2];
            res.add(List.of(nums1[i], nums2[j]));
            // expand right: (i, j+1)
            if (j + 1 < nums2.length) {
                long key = (long) i * nums2.length + (j + 1);
                if (visited.add(key)) {
                    pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                }
            }
            // expand down: (i+1, j)
            if (i + 1 < nums1.length) {
                long key = (long) (i + 1) * nums2.length + j;
                if (visited.add(key)) {
                    pq.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                }
            }
        }
        return res;
    }
}
