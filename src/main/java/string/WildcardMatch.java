package string;

/**
 * LeetCode 44, hard, tags: string, dynamic programming, greedy, recursion.
 * <p>
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 * <p>
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 */
public class WildcardMatch {

    // 3ms, 43 Mb, recursive.
    public boolean isMatchRecur(String s, String p) {
        return dfs(s, p, 0, 0) == 2;
    }

    /**
     * @param si index into s
     * @param pi index into p, pattern
     * @return 2: matched; 1: unmatched without reaching end of s; 0: reached end of s, unmatched
     */
    int dfs(String s, String p, int si, int pi) {
        if (si == s.length() && pi == p.length()) return 2;
        if (si == s.length() && p.charAt(pi) != '*') return 0;
        if (pi == p.length()) return 1;
        if (p.charAt(pi) == '*') {
            if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') return dfs(s, p, si, pi + 1); // skip duplicate *
            for (int i = 0; i <= s.length() - si; i++) {
                int ret = dfs(s, p, si + i, pi + 1);
                if (ret == 0 || ret == 2) return ret;
            }
        }
        if (p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi)) return dfs(s, p, si + 1, pi + 1);
        return 1;
    }
}
