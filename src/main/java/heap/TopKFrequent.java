package heap;

import java.util.*;

import static util.IntArrayUtil.swap;

/**
 * LeetCode 347, medium, tags: array, hash table, heap, divide and conquer, sorting, bucket sort, counting, quick select.
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
 * 1 <= nums.length <= 10^5, N
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
@SuppressWarnings("unused")
public class TopKFrequent {

    public static void main(String[] args) {
        TopKFrequent.SolutionQS tbt = new TopKFrequent.SolutionQS();
        System.out.println(Arrays.toString(tbt.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [1,2]
    }

    // solution 1, 10ms, 45 Mb. bucket, O(N) time and space.
    public int[] topKFrequentBucket(int[] nums, int k) {
        List<List<Integer>> bucket = new ArrayList<>(); // count->number
        for (int i = 0; i < nums.length + 1; i++) bucket.add(new ArrayList<>());
        Map<Integer, Integer> count = new HashMap<>(); // number->count
        for (int n : nums) count.put(n, count.getOrDefault(n, 0) + 1);
        for (int key : count.keySet()) {
            int frequency = count.get(key);
            bucket.get(frequency).add(key);
        }
        int[] res = new int[k];
        int j = 0;
        for (int i = nums.length; i > 0; i--) {
            if (bucket.get(i).isEmpty()) continue;
            for (int n : bucket.get(i)) {
                res[j++] = n;
                if (j == k) return res;
            }
        }
        return res;
    }

    // 11ms, 45.1 Mb. O(NLgK) time and O(N) space.
    public int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) counts.put(n, counts.getOrDefault(n, 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(Map.Entry::getValue)));
        maxHeap.addAll(counts.entrySet());
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
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(); // count->[numbers]
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

    // solution 2, quick select, 9ms. 46.6 Mb, O(N) average case, O(N^2) worse case, O(N) space.
    static class SolutionQS {
        int[] unique; // unique numbers in the array, set(nums)
        Map<Integer, Integer> count; // num->count
        Random r;

        public int[] topKFrequent(int[] nums, int k) {
            count = new HashMap<>();
            r = new Random();
            for (int num : nums) count.put(num, count.getOrDefault(num, 0) + 1);
            int n = count.size();
            unique = new int[n];
            int i = 0;
            for (int num : count.keySet()) unique[i++] = num;
            kthLargest(0, n - 1, k);
            return Arrays.copyOfRange(unique, n - k, n);
        }

        private int partition(int lo, int hi) {
            int pi = lo + r.nextInt(hi - lo + 1);
            int pivot = count.get(unique[pi]);
            swap(unique, pi, hi); // 1. move pivot to end
            pi = lo;
            for (int i = lo; i < hi; i++)// 2. move all less frequent elements to the lo
                if (count.get(unique[i]) < pivot) swap(unique, pi++, i);
            swap(unique, pi, hi); // 3. move pivot to its final place
            return pi;
        }

        private void kthLargest(int lo, int hi, int k) {
            while (lo < hi) {
                int pi = partition(lo, hi);
                if (pi == unique.length - k) break;
                else if (pi < unique.length - k) lo = pi + 1;
                else hi = pi - 1;
            }
        }
    }
}
