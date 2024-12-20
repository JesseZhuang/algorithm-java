package string;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 242, easy, tags: hash table, string, sorting. LintCode 773.
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 */
public class ValidAnagram {

    // solution 1, 5ms, 42.3Mb. O(n) time, O(26) space.
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++; // remember to align with 'A'
            counts[t.charAt(i) - 'a']--;
        }
        for (int count : counts) if (count != 0) return false;
        return true;
    }

    // solution 2, 16ms, 42.5Mb. LintCode follow up if inputs contain unicode characters. O(n) time and space.
    public boolean isAnagramMap(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            map.compute(t.charAt(i), (k, v) -> v == null ? -1 : v - 1);
        }
        for (int count : map.values()) if (count != 0) return false;
        return true;
    }
}
