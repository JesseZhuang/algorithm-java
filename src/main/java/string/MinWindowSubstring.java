package string;

/**
 * LeetCode 76, hard, tags: hash table, string, sliding window.
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that
 * every character in t (including duplicates) is included in the window. If there is no such substring,
 * return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 * Hints:
 * Use two pointers to create a window of letters in s, which would have all the characters from t.
 * Expand the right pointer until all the characters of t are covered.
 * Once all the characters are covered, move the left pointer and ensure that all the characters are still covered
 * to minimize the subarray size.
 * Continue expanding the right and left pointers until you reach the end of s.
 */
public class MinWindowSubstring {
    // sliding window. 2ms, 42.5Mb. O(S+T) time, O(128) space.
    // Other methods use hashmap to store character counts of t and s
    public String minWindow(String s, String t) {
        int[] map = new int[128]; // ascii
        for (int i = 0; i < t.length(); i++) map[t.charAt(i)]++;
        int tNotFound = t.length(), left = 0, right = 0, minL = 0, minR = s.length() + 1;
        while (right < s.length()) { // move right to find a valid window
            if (map[s.charAt(right++)]-- > 0) tNotFound--;
            while (tNotFound == 0) {
                if (right - left < minR - minL) {
                    minR = right;
                    minL = left;
                }
                if (map[s.charAt(left++)]++ == 0) tNotFound++; // move left to find a smaller window
            }
        }
        return minR == s.length() + 1 ? "" : s.substring(minL, minR);
    }
}
