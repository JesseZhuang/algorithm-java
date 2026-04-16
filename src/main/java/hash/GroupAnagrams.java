package hash;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/group-anagrams/">LeetCode 49</a>, medium,
 * tags: array, hash table, string, sorting.
 */
public final class GroupAnagrams {
    private GroupAnagrams() {}

    /**
     * Sort each string to form a canonical key, group by that key.
     * Time O(n * k log k), where n = strs.length, k = max string length. Space O(n * k).
     */
    public static List<List<String>> groupAnagramsSort(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) { // O(n)
            char[] chars = s.toCharArray();
            Arrays.sort(chars); // O(k log k)
            String key = new String(chars);
            groups.computeIfAbsent(key, k1 -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    /**
     * Use character frequency count as key to avoid sorting.
     * Time O(n * k), Space O(n * k).
     */
    public static List<List<String>> groupAnagramsCount(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) { // O(n)
            int[] count = new int[26]; // O(1)
            for (char c : s.toCharArray()) count[c - 'a']++; // O(k)
            String key = Arrays.toString(count);
            groups.computeIfAbsent(key, k1 -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }
}
