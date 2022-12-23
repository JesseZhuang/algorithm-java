package dp;

/**
 * LeetCode 91, medium, tags: string, dynamic programming.
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse
 * of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 * <p>
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {

    // dp backwards O(n) time, O(1) space. 1ms, 40.7Mb.
    public int numDecodingDp1(String s) {
        int soFar = 1, bSoFar = 0; // ways so far, ways at one char ago
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int ways = c == '0' ? 0 : soFar;
            if (i < s.length() - 1 && (c == '1' || (c == '2' && s.charAt(i + 1) < '7'))) ways += bSoFar;
            bSoFar = soFar;
            soFar = ways;
        }
        return soFar;
    }

    // dp forward, bSoFar init 0, 0ms, 40.7Mb
    public int numDecodingDp2(String s) {
        if (s.charAt(0) == '0') return 0;
        int soFar = 1, bSoFar = 0; // ways so far, ways at one char ago
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i), p = s.charAt(i - 1);
            int ways = (c == '0') ? 0 : soFar;
            if (p == '1' || (p == '2' && c < '7')) ways += i >= 2 ? bSoFar : 1;
            bSoFar = soFar;
            soFar = ways;
        }
        return soFar;
    }
}
