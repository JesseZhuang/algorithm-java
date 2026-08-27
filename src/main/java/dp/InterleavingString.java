package dp;

/**
 * LeetCode 97, medium, tags: string, dynamic programming.
 * <p>
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings
 * respectively, such that the interleaving is s1 + t1 + s2 + t2 + ... or t1 + s1 + t2 + s2 + ...
 * (the relative order of characters in each string is preserved).
 * <p>
 * Constraints:
 * <ul>
 *   <li>0 <= s1.length, s2.length <= 100</li>
 *   <li>0 <= s3.length <= 200</li>
 *   <li>s1, s2, and s3 consist of lowercase English letters.</li>
 * </ul>
 */
public final class InterleavingString {
    private InterleavingString() {}

    /**
     * Solution 1: 1D DP (space-optimized). O(mn) time, O(n) space.
     * dp[j] represents whether s3[0..i+j-1] can be formed by interleaving s1[0..i-1] and s2[0..j-1].
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int j = 1; j <= n; j++) dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1); // base case
        for (int i = 1; i <= m; i++) { // O(m) rows
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= n; j++) { // O(n) cols
                dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n];
    }

    /**
     * Solution 2: 2D DP. O(mn) time, O(mn) space.
     * dp[i][j] = true if s3[0..i+j-1] can be formed by interleaving s1[0..i-1] and s2[0..j-1].
     */
    public static boolean isInterleave2(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1); // O(m) rows
        for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1); // O(n) cols
        for (int i = 1; i <= m; i++) { // O(m) rows
            for (int j = 1; j <= n; j++) { // O(n) cols
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[m][n];
    }
}
