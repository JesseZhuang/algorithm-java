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
 * <p>
 */
@SuppressWarnings("unused")
public class SubstringKDC {
    // hashmap, n, n. 528ms, 26.66mb.
    public long kDistinctCharacters(String s, int k) {
        long res = 0;
        int r = 0, n = s.length(); // right pointer
        Map<Character, Integer> charCnt = new HashMap<>();
        for (int l = 0; l < n; l++) { // l left
            while (r < n && charCnt.size() < k)
                charCnt.compute(s.charAt(r++), (key, v) -> v == null ? 1 : v + 1);
            if (charCnt.size() == k) res += n - r + 1; // r-1 pointing at kth char
            char c = s.charAt(l);
            if (charCnt.containsKey(c)) {
                int cnt = charCnt.get(c);
                if (cnt == 1) charCnt.remove(c);
                else charCnt.put(c, cnt - 1);
            }
        }
        return res;
    }
}
