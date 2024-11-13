package string;

/**
 * LeetCode 647, medium, tags: string, dynamic programming.
 * Companies: salesforce.
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
 * Hints 1-3:
 * How can we reuse a previously computed palindrome to compute a larger palindrome?
 * If “aba” is a palindrome, is “xabax” a palindrome? Similarly is “xabay” a palindrome?
 * Complexity based hint:
 * If we use brute force and check whether for every start and end position a substring is a palindrome we have
 * O(n^2) start - end pairs and O(n) palindromic checks. Can we reduce the time for palindromic checks to O(1)
 * by reusing some previous computation?
 */
@SuppressWarnings("unused")
public class PalindromeSS {

    // solution 1, 2ms, 40.7 Mb. Manacher's algorithm. O(n) time and space.
    public int manachers(String s) {
        return new Manacher(s).cntPalindromeSubstring();
    }

    // solution 2, two pointer, 3ms, 40.2Mb. O(n^2) time, O(1) space.
    // another solution dp[][] to indicate s[i,j] palindrome or not, O(n^2) time and space.
    static class Solution2P {
        String s;

        public int countSubstrings2(String s) {
            int count = 0;
            for (int i = 0; i < s.length(); i++)
                count += expand(i, i) + expand(i, i + 1); // no duplicate checks
            return count;
        }

        /**
         * expand from [l,r] and count palindromes with [l,r] as center.
         * 1, i,i center is one letter
         * 2, i,i+1 center is the gap between the two letters
         *
         * @param l left boundary
         * @param r right boundary
         * @return number of palindromes centered here.
         */
        private int expand(int l, int r) {
            int res = 0;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                res++;
            }
            return res;
        }
    }

}
