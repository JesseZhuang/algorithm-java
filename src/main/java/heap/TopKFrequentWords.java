package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 692, medium, tags: hash table, string, trie, sorting, heap, bucket sort.
 * <p>
 * Given an array of strings words and an integer k, return the k most frequent strings sorted by
 * frequency from highest to lowest. Words with the same frequency should be sorted by their
 * lexicographical order.
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * <p>
 * Example 2:
 * <p>
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 500
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * k is in the range [1, The number of unique words[i]]
 * <p>
 * Follow-up: Could you solve it in O(n log k) time and O(n) extra space?
 */
@SuppressWarnings("unused")
public final class TopKFrequentWords {

    private TopKFrequentWords() {
    }

    // solution 1, max-heap. O(n + m log m) time where m is unique words; O(n) space for map + heap.
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>(); // O(n) space
        for (String w : words) freq.merge(w, 1, Integer::sum); // O(n) time
        // max-heap: higher freq first; same freq -> lexicographically smaller first
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue())
                        ? a.getKey().compareTo(b.getKey()) // O(1) lex compare (word len <= 10)
                        : b.getValue() - a.getValue()      // descending frequency
        );
        maxHeap.addAll(freq.entrySet()); // O(m log m) where m = unique words
        List<String> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) result.add(maxHeap.poll().getKey()); // O(k log m)
        return result;
    }

    // solution 2, bucket sort. O(n + m * L log L) time where L is max bucket size; O(n) space.
    @SuppressWarnings("unchecked")
    public static List<String> topKFrequentBucket(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>(); // O(n) space
        for (String w : words) freq.merge(w, 1, Integer::sum); // O(n) time
        int maxFreq = Collections.max(freq.values()); // O(m)
        List<String>[] buckets = new List[maxFreq + 1]; // O(n) space, index = frequency
        for (Map.Entry<String, Integer> e : freq.entrySet()) { // O(m)
            int f = e.getValue();
            if (buckets[f] == null) buckets[f] = new ArrayList<>();
            buckets[f].add(e.getKey());
        }
        List<String> result = new ArrayList<>(k);
        for (int i = maxFreq; i >= 1 && result.size() < k; i--) { // collect from highest freq
            if (buckets[i] == null) continue;
            Collections.sort(buckets[i]); // O(L log L) lexicographic sort within bucket
            for (String w : buckets[i]) {
                result.add(w);
                if (result.size() == k) break;
            }
        }
        return result;
    }
}
