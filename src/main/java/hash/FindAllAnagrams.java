package hash;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">LeetCode 438</a>, medium,
 * tags: hash table, string, sliding window.
 */
public final class FindAllAnagrams {
    private FindAllAnagrams() {}

    /**
     * Sliding window with count array; check all 26 slots each step.
     * Time O(n * 26) = O(n), Space O(1) (fixed 26-element array).
     */
    public static List<Integer> findAnagrams1(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        int[] cnt = new int[26];
        int n = s.length(), m = p.length();
        for (int i = 0; i < m; i++) { // O(m) — initialize window
            cnt[p.charAt(i) - 'a']++;
            cnt[s.charAt(i) - 'a']--;
        }
        if (allZero(cnt)) result.add(0);
        for (int i = m; i < n; i++) { // O(n) — slide window
            cnt[s.charAt(i) - 'a']--;
            cnt[s.charAt(i - m) - 'a']++;
            if (allZero(cnt)) result.add(i - m + 1); // O(26) check
        }
        return result;
    }

    private static boolean allZero(int[] cnt) {
        for (int c : cnt) if (c != 0) return false; // O(26)
        return true;
    }

    /**
     * Sliding window tracking number of matched slots (avoids scanning all 26 each step).
     * Time O(n), Space O(1).
     */
    public static List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        int[] cnt = new int[26];
        int n = s.length(), m = p.length();
        for (char c : p.toCharArray()) cnt[c - 'a']++; // O(m)
        int matches = 0;
        for (int i = 0; i < 26; i++) if (cnt[i] == 0) matches++; // initial matched slots
        for (int i = 0; i < n; i++) { // O(n) — expand window
            int idx = s.charAt(i) - 'a';
            cnt[idx]--;
            if (cnt[idx] == 0) matches++;       // slot just became balanced
            else if (cnt[idx] == -1) matches--; // slot just became unbalanced
            if (i >= m) { // O(1) — shrink window
                idx = s.charAt(i - m) - 'a';
                cnt[idx]++;
                if (cnt[idx] == 0) matches++;       // slot just became balanced
                else if (cnt[idx] == 1) matches--;  // slot just became unbalanced
            }
            if (matches == 26) result.add(i - m + 1); // all 26 slots balanced
        }
        return result;
    }
}
