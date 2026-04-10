package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>LeetCode 3. Medium. Tags: hash table, string, sliding window.</p>
 * <p>
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Solution 1: Sliding window with HashMap. O(n) time, O(min(n,m)) space where m is charset size.
 * <br>
 * Solution 2: Sliding window with HashSet. O(n) time, O(min(n,m)) space where m is charset size.
 */
public final class LongestSubstringWithoutRepeating {

    private LongestSubstringWithoutRepeating() {}

    // solution 1: sliding window with hash map. O(n) time, O(min(n,m)) space.
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>(); // O(min(n,m)) space for char->index
        int max = 0, left = 0;
        for (int right = 0; right < s.length(); right++) { // O(n) single pass
            char c = s.charAt(right);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1; // O(1) jump past previous occurrence
            }
            lastSeen.put(c, right); // O(1) update last seen index
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    // solution 2: sliding window with hash set. O(n) time, O(min(n,m)) space.
    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> window = new HashSet<>(); // O(min(n,m)) space for current window chars
        int max = 0, left = 0;
        for (int right = 0; right < s.length(); right++) { // O(n) outer pass
            char c = s.charAt(right);
            while (window.contains(c)) { // shrink window one step at a time
                window.remove(s.charAt(left)); // O(1) remove leftmost char
                left++;
            }
            window.add(c); // O(1) add current char
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
