package heap;

import java.util.PriorityQueue;

/**
 * LeetCode 2542 - Maximum Subsequence Score.
 *
 * Given two integer arrays nums1 and nums2 of length n and a positive integer k,
 * select k indices. Score = (sum of selected nums1[i]) * (min of selected nums2[i]).
 * Return the maximum possible score.
 */
public final class MaximumSubsequenceScore {

    private MaximumSubsequenceScore() {
    }

    /**
     * Sort pairs by nums2 descending, use a min-heap of size k on nums1 values.
     * Time: O(n log n), Space: O(n).
     */
    public static long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        // Sort indices by nums2 descending
        java.util.Arrays.sort(indices, (a, b) -> nums2[b] - nums2[a]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0;
        long result = 0;

        for (int idx : indices) {
            sum += nums1[idx];
            minHeap.offer(nums1[idx]);
            if (minHeap.size() > k) {
                sum -= minHeap.poll();
            }
            if (minHeap.size() == k) {
                result = Math.max(result, sum * nums2[idx]);
            }
        }
        return result;
    }
}
