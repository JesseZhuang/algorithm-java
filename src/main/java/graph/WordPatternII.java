package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 291, medium, LintCode 829, tags: hash table, string, backtracking.
 * <p>
 * Given a pattern and a string s, return true if s matches the pattern.
 * <p>
 * A string s matches a pattern if there is some bijective mapping of single characters to non-empty strings such
 * that if each character in pattern is replaced by the string it maps to, then the resulting string is s.
 * A bijective mapping means that no two characters map to the same string, and no character maps to
 * two different strings.
 * <p>
 * Example 1:
 * <p>
 * Input: pattern = "abab", s = "redblueredblue"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "red"
 * 'b' -> "blue"
 * Example 2:
 * <p>
 * Input: pattern = "aaaa", s = "asdasdasdasd"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "asd"
 * Example 3:
 * <p>
 * Input: pattern = "aabb", s = "xyzabcxzyabc"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pattern.length, s.length <= 20, m
 * string length, n
 * pattern and s consist of only lowercase English letters.
 */
public class WordPatternII {
    private Set<String> marked; // already mapped
    private Map<Character, String> charString;
    private String p;  // pattern
    private String s;
    private int m; // pattern length
    private int n; // string length

    public boolean wordPatternMatch(String pattern, String s) {
        marked = new HashSet<>();
        charString = new HashMap<>();
        this.p = pattern;
        this.s = s;
        m = p.length();
        n = s.length();
        return dfs(0, 0); // backtracking i in pattern j in string s
    }

    private boolean dfs(int i, int j) {
        if (i == m && j == n) return true;
        if (i == m || j == n || m - i > n - j) return false;
        char c = p.charAt(i);
        for (int k = j + 1; k <= n; ++k) {
            String t = s.substring(j, k);
            if (t.equals(charString.get(c)))
                if (dfs(i + 1, k)) return true;
            if (!charString.containsKey(c) && !marked.contains(t)) {
                charString.put(c, t);
                marked.add(t);
                if (dfs(i + 1, k)) return true;
                marked.remove(t);
                charString.remove(c);
            }
        }
        return false;
    }
}
