package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * LintCode 1375, medium, tags: hash table, string, sliding window, two pointers.
 * <p>
 * Given a string S with only lowercase characters.
 * <p>
 * Return the number of substrings that contains at least k distinct characters.
 * <p>
 * Constraints:
 * <p>
 * 10≤length(S)≤1,000,000
 * 1≤k≤26
 * <p>
 * Example
 * Example 1:
 * <p>
 * Input: S = "abcabcabca", k = 4
 * Output: 0
 * Explanation: There are only three distinct characters in the string.
 * Example 2:
 * <p>
 * Input: S = "abcabcabcabc", k = 3
 * Output: 55
 * Explanation: Any substring whose length is not smaller than 3 contains a, b, c.
 * For example, there are 10 substrings whose length are 3, "abc", "bca", "cab" ... "abc"
 * There are 9 substrings whose length are 4, "abca", "bcab", "cabc" ... "cabc"
 * ...
 * There is 1 substring whose length is 12, "abcabcabcabc"
 * So the answer is 1 + 2 + ... + 10 = 55.
 */
public class SubstringKDC {
    public long kDistinctCharacters(String s, int k) {
        long res = 0;
        int r = 0; // right
        char c;
        Map<Character, Integer> charCnt = new HashMap<>();
        for (int l = 0; l < s.length(); l++) { // l left
            while (r < s.length() && charCnt.size() < k) {
                c = s.charAt(r);
                if (charCnt.containsKey(c)) {
                    charCnt.put(c, charCnt.get(c) + 1);
                } else {
                    charCnt.put(c, 1);
                }
                r++;
            }
            if (charCnt.size() == k) {
                res = res + (s.length() - r + 1);
            }
            c = s.charAt(l);
            if (charCnt.containsKey(c)) {
                if (charCnt.get(c) > 1) {
                    charCnt.put(c, charCnt.get(c) - 1);
                } else {
                    charCnt.remove(c);
                }
            }
        }
        return res;
    }
}
