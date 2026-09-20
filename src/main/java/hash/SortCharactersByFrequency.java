package hash;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/sort-characters-by-frequency/">LeetCode 451</a>, medium,
 * tags: hash table, string, sorting, heap, bucket sort.
 */
@SuppressWarnings("unused")
public final class SortCharactersByFrequency {
    private SortCharactersByFrequency() {}

    /**
     * HashMap + Sort: count frequencies, sort characters by frequency descending.
     * Time O(n + k log k) where k = unique chars (at most 62). Space O(n).
     */
    public static String frequencySortSort(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) count.merge(c, 1, Integer::sum); // O(n)
        List<Character> chars = new ArrayList<>(count.keySet());
        chars.sort((a, b) -> count.get(b) - count.get(a)); // O(k log k)
        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(String.valueOf(c).repeat(count.get(c))); // O(n)
        return sb.toString();
    }

    /**
     * Bucket Sort: use frequency as bucket index, iterate from highest bucket.
     * Time O(n), Space O(n).
     */
    @SuppressWarnings("unchecked")
    public static String frequencySortBucket(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) count.merge(c, 1, Integer::sum); // O(n)
        int maxFreq = Collections.max(count.values()); // O(k)
        List<Character>[] buckets = new List[maxFreq + 1]; // O(n)
        for (var entry : count.entrySet()) { // O(k)
            int freq = entry.getValue();
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (int freq = maxFreq; freq > 0; freq--) { // O(n) total
            if (buckets[freq] != null) {
                for (char c : buckets[freq]) sb.append(String.valueOf(c).repeat(freq));
            }
        }
        return sb.toString();
    }
}
