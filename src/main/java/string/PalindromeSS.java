package string;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 647, medium, tags: string, dynamic programming.
 * Given a string s, return the number of palindromic substrings in it.
 * <p>
 * A string is a palindrome when it reads the same backward as forward.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * <p>
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 * Hints:
 * How can we reuse a previously computed palindrome to compute a larger palindrome?
 * If “aba” is a palindrome, is “xabax” a palindrome? Similarly is “xabay” a palindrome?
 * Complexity based hint:
 * If we use brute force and check whether for every start and end position a substring is a palindrome we have
 * O(n^2) start - end pairs and O(n) palindromic checks. Can we reduce the time for palindromic checks to O(1)
 * by reusing some previous computation?
 */
public class PalindromeSS {
    Set<Integer[]> palBounds;

    // 110ms, 88.2Mb. O(n^2) space and time.
    public int countSubstrings(String s) {
        palBounds = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            findPalindrome(i, i, s, palBounds);
            findPalindrome(i, i + 1, s, palBounds);
        }
        return palBounds.size();
    }

    private void findPalindrome(int l, int r, String s, Set<Integer[]> palBounds) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            Integer[] bounds = {l, r};
            if (palBounds.contains(bounds)) continue;
            palBounds.add(bounds);
            l--;
            r++;
        }
    }


    // 3ms, 40.2Mb. O(n^2) time, O(1) space.
    public int countSubstrings2(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++)
            count += countPalindrome(i, i, s) + countPalindrome(i, i + 1, s); // no duplicate checks
        return count;
    }

    private int countPalindrome(int l, int r, String s) {
        int count = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
            count++;
        }
        return count;
    }

    // dp, 15ms, 42.9Mb. O(n^2) space and time.
    public int countSubstringsDP(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i + 1 < 3 || dp[i + 1][j - 1]);
                if (dp[i][j]) ++res;
            }
        }
        return res;
    }

    // 2ms, 40.7 Mb. Manachers algorithm. O(n) time and space.
    public int manachers(String s) {
        StringBuilder sExpand = new StringBuilder("@#");
        for (int i = 0; i < s.length(); ++i) {
            sExpand.append(s.charAt(i));
            sExpand.append("#");
        }
        sExpand.append("$");
        int n = sExpand.length();
        int[] palRadii = new int[n]; // max length palindrome centered at i
        int right = 0;
        int center = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (i < right) {
                palRadii[i] = Math.min(right - i, palRadii[center - (i - center)]);
            }
            while (sExpand.charAt(i + palRadii[i] + 1) == sExpand.charAt(i - palRadii[i] - 1)) ++palRadii[i];
            if (i + palRadii[i] > right) {
                center = i;
                right = i + palRadii[i];
            }
        }
        int cnt = 0;
        for (int c = 1; c < n - 1; ++c) cnt += (palRadii[c] + 1) / 2;
        // number of palindromes at this center == (maxLen + 1) / 2
        // e.g., aba(b,aba), cnt == 2 == (len:3 +1)/2; a 1 == (1+1)/2; abba(bb,abba) 2 == (4+1)/2;
        return cnt;
    }
}
