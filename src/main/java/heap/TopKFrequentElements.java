package heap;

import java.util.*;

/**
 * LeetCode 347, medium, tags: array, hash table, divide and conquer, sorting, heap, bucket sort, counting, quickselect.
 * <p>
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in
 * any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
@SuppressWarnings("unused")
public final class TopKFrequentElements {

    private TopKFrequentElements() {
    }

    // solution 1, bucket sort. O(n) time, O(n) space.
    public static int[] topKFrequentBucket(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) count.merge(num, 1, Integer::sum); // O(n)
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[nums.length + 1]; // index = frequency
        for (var entry : count.entrySet()) { // O(n) distribute
            int freq = entry.getValue();
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(entry.getKey());
        }
        int[] res = new int[k];
        int idx = 0;
        for (int freq = nums.length; freq > 0 && idx < k; freq--) { // O(n) collect
            if (buckets[freq] != null) {
                for (int num : buckets[freq]) {
                    res[idx++] = num;
                    if (idx == k) return res;
                }
            }
        }
        return res;
    }

    // solution 2, min-heap of size k. O(n log k) time, O(n) space.
    public static int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) count.merge(num, 1, Integer::sum); // O(n)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // min-heap by freq
        for (var entry : count.entrySet()) { // O(n log k)
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
            if (pq.size() > k) pq.poll(); // evict least frequent
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = pq.poll()[0];
        return res;
    }
}
