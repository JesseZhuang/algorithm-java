package array;

import util.IntArrayUtil;

import java.util.PriorityQueue;

/**
 * LeetCode 215, medium, tags: array, divide and conquer, sorting, heap(priority queue), quick select.
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
public class KthLargest {

    // solution 2, O(NlgK) time, O(K) space, 56ms, 56.97 Mb.
    public int findKthLargestPQ(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int n : nums) {
            q.add(n);
            if (q.size() > k) q.remove();
        }
        return q.peek();
    }

    /**
     * solution 1, 11ms, 56.59Mb. O(n) time, O(1) space average case. Quick select. With shuffle: 11ms, 56.42Mb.
     * Time limit exceeded on LeetCode for a large array with duplicates for 3 way partition, see
     * {@link princeton.jsl.QuickSort}.
     */
    static class SolutionQS {

        static int findKthLargestQS(int[] nums, int k) {
            IntArrayUtil.FYShuffle2(nums);
            return QuickSelect.kthLargest(nums, k);
        }
    }
}
