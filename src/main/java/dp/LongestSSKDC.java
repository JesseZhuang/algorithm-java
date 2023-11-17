package dp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LeetCode 340, LintCode 386, medium, tags: hash table, string, sliding window, two pointers, LinkedHashMap.
 * <p>
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k
 * distinct characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 10^4
 * 0 <= k <= 50
 */
public class LongestSSKDC {
    // solution 1, sliding window LinkedHashMap, lint code 683ms, 27.70Mb. n time, 1 space.
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int res = 0, n = s.length();
        Map<Character, Integer> lastSeen = new LinkedHashMap<>();
        for (int l = 0, r = 0; r < n; r++) {
            char c = s.charAt(r);
            // important, key position in linked list, bug: eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh, k=16
            // https://stackoverflow.com/questions/29377949/deletion-in-linkedhashmap-vs-hashmap, O(1) time
            if (lastSeen.containsKey(c)) lastSeen.remove(c);
            lastSeen.put(c, r);
            if (lastSeen.size() <= k) res = Math.max(res, r - l + 1);
            else {
                Iterator<Map.Entry<Character, Integer>> it = lastSeen.entrySet().iterator(); // O(1) time
                l = it.next().getValue() + 1;
                it.remove(); // better than remove from map
            }
        }
        return res;
    }

    // solution 2, sliding window 2p, lint code 651ms, 27.13MB. n time, k(character set) space.
    // if all are english letters, 52 space. probably still considered O(1) space.
    // imagine 1_000_000 a followed by unique characters (each), map size will be > k
    // unicode 0(hex) to 10FFFF(hex) 1,114,112 code points, java use 21 bit of int to represent
    // see Character javadoc, java Character can represent 16-bit 65536 characters
    public int lengthOfLongestSubstringKDistinct1(String s, int k) {
        int res = 0, n = s.length();
        Map<Character, Integer> count = new HashMap<>(); // use map instead of array, need size()
        for (int l = 0, r = 0; r < n; r++) {
            char c = s.charAt(r);
            count.put(c, count.getOrDefault(c, 0) + 1);
            if (count.size() <= k) res = Math.max(res, r - l);
            else {
                char lc = s.charAt(l++);
                int cnt = count.get(lc);
                if (cnt == 1) count.remove(lc);
                else count.put(lc, cnt - 1);
            }
        }
        return res;
    }


}
