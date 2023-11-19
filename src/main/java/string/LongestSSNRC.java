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
 * 0 <= s.length <= 5 * 10^4, n, m: number of unique characters
 * s consists of English letters, digits, symbols and spaces. so m < 128, ascii
 */
public class LongestSSNRC {
    // solution 1, 5ms, 42.8 Mb. O(n) time, O(m) space. m: number of unique characters
    public static int lengthOfLongestSubstringMap(String s) { // abcabcbb
        int len = s.length(), ans = 0;
        Map<Character, Integer> lastSeen = new HashMap<>(); // current index of character
        for (int r = 0, l = 0; r < len; r++) { // l,r left,right of current substring
            char c = s.charAt(r);
            if (lastSeen.containsKey(c)) // r 0-2, false; 3 (seen a),4-7 true
                l = Math.max(lastSeen.get(c) + 1, l); // r:3-7 l:1,2,3,5,7, max needed, e.g., abba
            ans = Math.max(ans, r - l + 1); // 1,2,3,3,3,3(3>2),3(3>1) max including char at r
            lastSeen.put(c, r); // {a:0,b:1,c:2} -> {a:3,b:1,c:2}, ... {a:3,b:4,c:5}, ... {a:3,b:7,c:5}
        }
        return ans;
    }

    // solution 2, 2ms, 42.3Mb. Use Integer[] instead of map. O(n) time, O(128) space.
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        Integer[] charIndex = new Integer[128];
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (charIndex[c] != null) {
                l = Math.max(charIndex[c] + 1, l);
            }
            charIndex[c] = r;
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

}
