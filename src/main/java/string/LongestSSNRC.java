package string;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3 substring no repeating characters, medium, tags: hash table, string, sliding window.
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104, m
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSSNRC {
    // 2ms, 42.3Mb. Use Integer[] instead of map.
    public static int lengthOfLongestSubstring(String s) {
        Integer[] charIndex = new Integer[128];
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            Integer index = charIndex[c];
            if (index != null && index >= left && index < right) left = index + 1;
            res = Math.max(res, right - left + 1);
            charIndex[c] = right;
            right++;
        }
        return res;
    }

    // 5ms, 42.8 Mb. O(n) time, O(min(m,n)) space.
    public static int lengthOfLongestSubstringMap(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> lastSeen = new HashMap<>(); // current index of character
        for (int j = 0, i = 0; j < n; j++) { // i,j left,right of current substring
            if (lastSeen.containsKey(s.charAt(j)))
                i = Math.max(lastSeen.get(s.charAt(j)), i);
            ans = Math.max(ans, j - i + 1);
            lastSeen.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}
