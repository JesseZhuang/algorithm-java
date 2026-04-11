package heap;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * LeetCode 215, medium, tags: array, divide and conquer, sorting, heap, quick select.
 * <p>
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Can you solve it without sorting?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
@SuppressWarnings("unused")
public final class KthLargestElement {

    private KthLargestElement() {
    }

    // solution 1, min-heap. O(n log k) time, O(k) space.
    public static int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // min-heap of size k
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll(); // evict smallest, keep k largest
        }
        return minHeap.peek(); // smallest of the k largest is the kth largest
    }

    // solution 2, quickselect with random pivot. O(n) average time, O(n^2) worst case, O(1) space.
    public static int findKthLargestQuickSelect(int[] nums, int k) {
        Random rand = new Random();
        int target = nums.length - k; // kth largest == (n-k)th smallest (0-indexed)
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int pi = partition(nums, lo, hi, rand);
            if (pi == target) return nums[pi];
            else if (pi < target) lo = pi + 1;
            else hi = pi - 1;
        }
        return nums[lo];
    }

    private static int partition(int[] nums, int lo, int hi, Random rand) {
        int pi = lo + rand.nextInt(hi - lo + 1); // random pivot
        int pivot = nums[pi];
        swap(nums, pi, hi); // move pivot to end
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (nums[i] < pivot) swap(nums, store++, i);
        }
        swap(nums, store, hi); // move pivot to final position
        return store;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
