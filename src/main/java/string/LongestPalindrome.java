package string;

/**
 * LeetCode 5, medium, tags: dynamic programming, string.
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
@SuppressWarnings("unused")
public class LongestPalindrome {

    // solution 1 expand from center, 23 ms, 41.9Mb. O(n^2) time, O(1) space.
    static class Solution1 {
        int left, maxLen;
        String s;

        public String longestPalindrome(String s) {
            this.s = s;
            for (int i = 0; i < s.length(); i++) {
                extendPalindrome(i, i); // odd length
                extendPalindrome(i, i + 1); // even length
            }
            return s.substring(left, left + maxLen);
        }

        /**
         * find the length of the longest palindrome centered at left, right
         *
         * @param left  left index
         * @param right right index
         */
        private void extendPalindrome(int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } // left, right will stop when out of bound or no longer match
            if (maxLen < right - left - 1) {
                this.left = left + 1; // move back to a matched index
                maxLen = right - left - 1;
            }
        }
    }

    // O(n) time and space. Manacher's algorithm. 10ms, 42.75mb.
    // https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm
    static class Solution2 {
        public String longestPalindrome(String s) {
            return new Manacher(s).longestPalindromicSubstring();
        }
    }
}
