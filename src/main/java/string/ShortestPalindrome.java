package string;

/**
 * LeetCode 214, hard, tags: string, rolling hash, hash function, string matching.
 * <p>
 * You are given a string s. You can convert s to a
 * palindrome
 * by adding characters in front of it.
 * <p>
 * Return the shortest palindrome you can find by performing this transformation.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: "dcbabcd"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 10^4
 * s consists of lowercase English letters only.
 */
public class ShortestPalindrome {
    // find longest from index 0. aacecaaa -> aacecaa, abcd -> a


    // 2 pointers, recursive T(n)=T(n-2)+O(n), O(n^2) time, O(n) space.
    static class Solution4 {

        public String shortestPalindrome(String s) {
            int n = s.length();
            if (n == 0) return s;
            // Find the longest palindromic prefix
            int left = 0;
            for (int right = n - 1; right >= 0; right--)
                if (s.charAt(right) == s.charAt(left)) left++;
            // If the whole string is a palindrome, return the original string
            if (left == n) return s;
            // Extract the suffix that is not part of the palindromic prefix
            String nonPalindromeSuffix = s.substring(left);
            StringBuilder reverseSuffix = new StringBuilder(nonPalindromeSuffix).reverse();
            // Form the shortest palindrome by prepending the reversed suffix
            return reverseSuffix
                    .append(shortestPalindrome(s.substring(0, left)))
                    .append(nonPalindromeSuffix)
                    .toString();
        }
    }

    // brute force, O(n^2) time, O(n) space.
    static class Solution5 {
        public String shortestPalindrome(String s) {
            int length = s.length();
            String reversedString = new StringBuilder(s).reverse().toString();
            // Iterate through the string to find the longest palindromic prefix
            for (int i = 0; i < length; i++)
                if (s.substring(0, length - i).equals(reversedString.substring(i)))
                    return new StringBuilder(reversedString.substring(0, i))
                            .append(s).toString();
            return "";
        }
    }
}
