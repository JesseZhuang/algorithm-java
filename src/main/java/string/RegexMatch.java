package string;

/**
 * LeetCode 10, hard, tags: string, dynamic programming, recursion.
 * <p>
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20, m
 * 1 <= p.length <= 30, n
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class RegexMatch {

    // dp, O(mn) time and space. 2ms, 40.7MB. backwards, logic same to recursive.
    public boolean isMatch(String s, String p) {// remember by correlate to recursive 1 method
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true; // both empty string, true
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) { // dp[i<s.length()][p.length()] all false
                boolean firstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                    dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                else dp[i][j] = firstMatch && dp[i + 1][j + 1];
            }
        }
        return dp[0][0];
    }

    // https://leetcode.com/problems/regular-expression-matching/discuss/191830/Java-DP-solution-beats-100-with-explanation
    // 2ms, 41.1 Mb. DP, forward.
    public boolean isMatch2(String s, String p) {
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        for (int j = 1; j <= p.length(); j++) {
            for (int i = 1; i <= s.length(); i++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i][j - 2] ||
                            ((s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
            }
        }
        return dp[s.length()][p.length()];
    }

    // O((m+n)2^(m+n/2)) time and space. TLE. T(m,n) = T(m-1,n)+T(m,n-2), recursive.
    public boolean isMatchR1(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*')
            return isMatchR1(s, p.substring(2)) || (firstMatch && isMatchR1(s.substring(1), p));
        else return firstMatch && isMatchR1(s.substring(1), p.substring(1));
    }

    // TLE, recursive 2.
    public boolean isMatchR2(String s, String p) {
        return isMatchR2(0, s, 0, p);
    }

    private boolean isMatchR2(int i, String s, int j, String p) {
        int sn = s.length(), pn = p.length();
        if (j == pn) return i == sn;
        char si = s.charAt(i), pj = p.charAt(j);
        boolean firstMatch = i < sn && (pj == si || pj == '.');
        if (j + 1 < pn && p.charAt(j + 1) == '*')
            return isMatchR2(i, s, j + 2, p) || (firstMatch && isMatchR2(i + 1, s, j, p));
        else return firstMatch && isMatchR2(i + 1, s, j + 1, p);
    }

    public static void main(String[] args) {
        RegexMatch tbt = new RegexMatch();
        tbt.isMatch("aab", "c*a*b");
    }

}
