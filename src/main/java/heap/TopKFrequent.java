package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

/**
 * LeetCode 347, medium,
 * tags: array, hash table, heap, divide and conquer, sorting, bucket sort, counting, quick select.
 * <p>
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer
 * in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105, N
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequent {

    // 10ms, 45 Mb. bucket, O(N) time and space.
    public int[] topKFrequentBucket(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) count.put(n, count.getOrDefault(n, 0) + 1);
        for (int key : count.keySet()) {
            int frequency = count.get(key);
            if (bucket[frequency] == null) bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }
        int[] res = new int[k];
        int j = 0;
        for (int i = nums.length; i >= 0; i--) {
            if (bucket[i] == null) continue;
            for (int n : bucket[i]) {
                res[j++] = n;
                if (j == k) return res;
            }
        }
        return res;
    }

    int[] unique;
    Map<Integer, Integer> count;

    // 9ms. 46.6 Mb, O(N) average case, O(N^2) worse case, O(N) space.
    public int[] topKFrequent(int[] nums, int k) {
        count = new HashMap();
        for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num : count.keySet()) unique[i++] = num;
        quickSelect(0, n - 1, n - k); // Do a partial sort
        return Arrays.copyOfRange(unique, n - k, n);
    }

    private void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    private int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        swap(pivot_index, right); // 1. move pivot to end
        int store_index = left;
        for (int i = left; i <= right; i++) { // 2. move all less frequent elements to the left
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }
        swap(store_index, right); // 3. move pivot to its final place
        return store_index;
    }

    private void quickSelect(int left, int right, int k_smallest) {
        if (left == right) return; // base case: the list contains only one element
        Random random_num = new Random(); // select a random pivot_index
        int pivot_index = left + random_num.nextInt(right - left);
        pivot_index = partition(left, right, pivot_index); // find the pivot position in a sorted list
        if (k_smallest < pivot_index) quickSelect(left, pivot_index - 1, k_smallest); // go left
        else if (k_smallest > pivot_index) quickSelect(pivot_index + 1, right, k_smallest); // go right
    }

    // 11ms, 45.1 Mb. O(NLgK) time and O(N) space.
    public int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(e -> e.getValue())));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) maxHeap.add(entry);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res[i] = entry.getKey();
        }
        return res;
    }

    // 15ms, 44.6 Mb. O(NLgK) time and O(N) space.
    public int[] topKFrequentMap(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) count.put(n, count.getOrDefault(n, 0) + 1);
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (!map.containsKey(entry.getValue())) map.put(entry.getValue(), new ArrayList<>());
            map.get(entry.getValue()).add(entry.getKey());
        }
        List<Integer> res = new ArrayList<>();
        while (res.size() < k) res.addAll(map.pollLastEntry().getValue());
        int[] result = new int[k];
        for (int i = 0; i < k; i++) result[i] = res.get(i);
        return result;
    }

    public static void main(String[] args) {
        TopKFrequent tbt = new TopKFrequent();
        System.out.println(Arrays.toString(tbt.topKFrequentMap(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [1,2]
    }
}
