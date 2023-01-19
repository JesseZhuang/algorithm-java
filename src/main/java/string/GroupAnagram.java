package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49, medium, tags: array, hash table, string, sorting.
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 * <p>
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104, m
 * 0 <= strs[i].length <= 100, n
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagram {
    // 6ms, 45.9Mb. O(mn) time, O(mn) space.
    // Other: construct key with delimiter and count; sort O(mnLgn) time;prime number for a-z, multiple as key
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        final int R = 26;
        for (String s : strs) {
            char[] counts = new char[R];
            for (char c : s.toCharArray()) counts[c - 'a']++; // counting sort O(26)
            String key = String.valueOf(counts);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s); // must be after creating list, must not be in else
        }
        return new ArrayList<>(map.values());
    }
}
