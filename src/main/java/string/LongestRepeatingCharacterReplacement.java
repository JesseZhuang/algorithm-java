package string;

/**
 * LeetCode 424 Longest Repeating Character Replacement, tags: string, sliding window, binary search, hash table.
 * <p>
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other
 * uppercase English letter. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing the above
 * operations.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * <p>
 * Example 2:
 * <p>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
@SuppressWarnings("unused")
public final class LongestRepeatingCharacterReplacement {
    private LongestRepeatingCharacterReplacement() {
    }

    // Solution 1: Sliding window. O(n) time, O(1) space.
    public static int slidingWindow(String s, int k) {
        int[] count = new int[26]; // O(1) space, fixed 26 letters
        int maxFreq = 0, left = 0, res = 0;
        for (int right = 0; right < s.length(); right++) { // O(n) iteration
            count[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']); // track max freq in window
            while (right - left + 1 - maxFreq > k) { // shrink window when replacements exceed k
                count[s.charAt(left) - 'A']--;
                left++;
            }
            res = Math.max(res, right - left + 1); // update longest valid window
        }
        return res;
    }

    // Solution 2: Binary search on answer length + sliding window check. O(n log n) time, O(1) space.
    public static int binarySearch(String s, int k) {
        int lo = 1, hi = s.length(), res = 0; // binary search on window length
        while (lo <= hi) { // O(log n) iterations
            int mid = lo + (hi - lo) / 2;
            if (canAchieve(s, k, mid)) { // O(n) check for each candidate length
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    // Check if a valid window of given length exists. O(n) time.
    private static boolean canAchieve(String s, int k, int len) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) { // slide window of fixed size len
            count[s.charAt(i) - 'A']++;
            if (i >= len) count[s.charAt(i - len) - 'A']--; // remove element leaving window
            if (i >= len - 1) {
                int maxFreq = 0;
                for (int c : count) maxFreq = Math.max(maxFreq, c); // O(1), 26 letters
                if (len - maxFreq <= k) return true; // valid if replacements needed <= k
            }
        }
        return false;
    }
}
