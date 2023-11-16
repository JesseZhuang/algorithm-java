package string;

/**
 * LeetCode 424 repeating character replacement, medium, tags: hash table, string, sliding window.
 * You are given a string s and an integer k. You can choose any character of the string and change it to any
 * other uppercase English character. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing
 * the above operations.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * <p>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105, n
 * s consists of only uppercase English letters. 26 letters
 * 0 <= k <= s.length
 */
public class LongestRCR {

    // solution 1, sliding window with 2 pointer, 4ms, 41.8Mb, O(n) time, O(26) space. easier to read
    public int characterReplacementSW2(String s, int k) {
        int len = s.length();
        int count[] = new int[26], maxCnt = 0, res = 0;
        for (int l = 0, r = 0; r < len; r++) {
            maxCnt = Math.max(maxCnt, ++count[s.charAt(r) - 'A']);
            if (r - l + 1 - maxCnt > k) // no =
                count[s.charAt(l++) - 'A']--;
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    // solution 2, 15ms, 41.5Mb. Binary Search, O(nLgn) time, O(26) space.
    public int characterReplacementBS(String s, int k) {
        int min = 1, max = s.length() + 1; // min max of the repeating sequence length
        while (min + 1 < max) { // s.length >= 1, so max >= 2
            int mid = min + (max - min) / 2;
            if (canMakeValidSubstring(s, mid, k)) min = mid;
            else max = mid;
        }
        return min;
    }

    /**
     * take a window of length `substringLength` on the given
     * string, and move it from left to right. If this window
     * satisfies the condition of a valid string, then we return true
     */
    private Boolean canMakeValidSubstring(String s, int substringLength, int k) {
        int count[] = new int[26], maxCnt = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {// O(n)
            if (r + 1 - l > substringLength)// increment start of the sliding window
                count[s.charAt(l++) - 'A']--; // update freq for original start
            maxCnt = Math.max(maxCnt, ++count[s.charAt(r) - 'A']);
            if (substringLength - maxCnt <= k) return true;
        }
        return false;
    }

    // solution 1b, sliding window, 6ms, 42.1Mb. O(n) time, O(26) space.
    public int characterReplacementSW1(String s, int k) {
        int res = 0, maxCnt = 0, count[] = new int[26];
        // AABABBA, k=1
        // count A1    A2    B1    A3    B2    B3    A2
        // maxC  1     2     2     3     3     3     3
        // res   1     2     3     4     A3->2 A2->1 B3->2
        for (int i = 0; i < s.length(); ++i) {
            maxCnt = Math.max(maxCnt, ++count[s.charAt(i) - 'A']);
            if (res - maxCnt < k) res++; // no =
            else count[s.charAt(i - res) - 'A']--; // shift sliding window right, keep counts for index (i-res,i]
        }
        return res;
    }


}
