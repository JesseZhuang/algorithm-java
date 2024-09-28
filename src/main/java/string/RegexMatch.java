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
@SuppressWarnings("unused")
public class RegexMatch {

    public static void main(String[] args) {
        RegexMatch.SolutionRecur tbt = new RegexMatch.SolutionRecur();
        tbt.isMatch("aab", "c*a*b");
    }

    // dp, O(mn) time and space. 2ms, 40.7MB. backwards, logic same to recursive.
    static class SolutionDP {
        public boolean isMatch(String s, String p) {// remember by correlate to recursive method
            int n = s.length(), m = p.length();
            boolean[][] dp = new boolean[n + 1][m + 1];
            dp[n][m] = true; // both empty string, true
            for (int i = n; i >= 0; i--) { // p may match empty string in s, i start with n
                for (int j = m - 1; j >= 0; j--) { // dp[i<s.length()][p.length()] all false
                    boolean firstMatch = i < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'); // i<n important
                    if (j + 1 < m && p.charAt(j + 1) == '*')
                        dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                        // ignore the two chars in p, e.g., s:ab, p:c*ab, ignore c* in p, ab matches ab
                        // or first match && ignore first char in s, e.g., s:ab p: a*b, ignore a in s, b matches a*b
                    else dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
            return dp[0][0];
        }

        // https://leetcode.com/problems/regular-expression-matching/discuss/191830/Java-DP-solution-beats-100-with-explanation
        // 2ms, 41.1 Mb. DP, forward.
        public boolean isMatch2(String s, String p) {
            boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
            dp[0][0] = true;
            // set first row of dp, match anything followed by star to empty string
            for (int j = 2; j <= p.length(); j++) dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
            for (int j = 1; j <= p.length(); j++) {
                for (int i = 1; i <= s.length(); i++) {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') // first char match
                        dp[i][j] = dp[i - 1][j - 1];
                    else if (p.charAt(j - 1) == '*')
                        dp[i][j] = dp[i][j - 2] || // ignore the two chars in p
                                ((s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                    // or s[i-1] matches p[j-2] so we ignore s[i-1] and take dp[i-1][j]
                }
            }
            return dp[s.length()][p.length()];
        }
    }

    // O((m+n)2^(m+n/2)) time and space. TLE (2024 99sms, 45.42mb). T(m,n) = T(m-1,n)+T(m,n-2), recursive.
    static class SolutionRecur {
        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) return s.isEmpty();
            boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
            if (p.length() >= 2 && p.charAt(1) == '*')
                return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
            else return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

}
