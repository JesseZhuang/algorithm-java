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
public class LongestPalindrome {
    int left, maxLen;

    // 23 ms, 41.9Mb. O(n^2) time, O(1) space.
    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            extendPalindrome(s, i, i); // odd length
            extendPalindrome(s, i, i + 1);
        }
        return s.substring(left, left + maxLen);
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        } // left, right will stop when out of bound or no longer match
        if (maxLen < right - left - 1) {
            this.left = left + 1;
            maxLen = right - left - 1;
        }
    }

    // O(n) time and space. manachers algorithm, GFG. 8ms, 42.1Mb.
    // https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm
    public static String manachers(String s) {
        int N = s.length();
        if (N == 0) return "";
        if (N == 1) return s;
        N = 2 * N + 1; // center count, center could be character or space between
        int[] L = new int[N]; // O(n) space, LPS Length Array
        L[0] = 0;
        L[1] = 1;
        int c = 1, cr = 2; // center, center right
        int i = 0; // currentRightPosition

        int iMirror; // currentLeftPosition
        int expand, diff;
        int maxLPSLength = 0, maxLPSCenterPosition = 0;
        int start, end;

        for (i = 2; i < N; i++) {
            iMirror = 2 * c - i;
            expand = 0; // Reset expand - means no expansion required
            diff = cr - i;
            if (diff >= 0) { // If currentRightPosition i is within centerRightPosition R
                if (L[iMirror] < diff) L[i] = L[iMirror]; // Case 1
                else if (L[iMirror] == diff && cr == N - 1) L[i] = L[iMirror];
                else if (L[iMirror] == diff && cr < N - 1) {
                    L[i] = L[iMirror];
                    expand = 1; // Expansion required
                } else if (L[iMirror] > diff) { // Case 4
                    L[i] = diff;
                    expand = 1;
                }
            } else {
                L[i] = 0;
                expand = 1;
            }
            if (expand == 1) {
                try { // Attempt to expand palindrome centered at currentRightPosition i.
                    while (((i + L[i]) < N && (i - L[i]) > 0)
                            && (((i + L[i] + 1) % 2 == 0)
                            || (s.charAt((i + L[i] + 1) / 2) == s.charAt((i - L[i] - 1) / 2)))) {
                        L[i]++;
                    }
                } catch (Exception e) {
                    assert true;
                }
            }
            if (L[i] > maxLPSLength) {// Track maxLPSLength
                maxLPSLength = L[i];
                maxLPSCenterPosition = i;
            }
            // If palindrome centered at currentRightPosition i expand beyond centerRightPosition R,
            // adjust centerPosition C based on expanded palindrome.
            if (i + L[i] > cr) {
                c = i;
                cr = i + L[i];
            }
        }
        start = (maxLPSCenterPosition - maxLPSLength) / 2;
        end = start + maxLPSLength - 1;
        return s.substring(start, end + 1);
    }
}
