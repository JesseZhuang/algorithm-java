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
@SuppressWarnings("unused")
public class ShortestPalindrome {
    // find longest from index 0. aacecaaa -> aacecaa, abcd -> a

    // kmp, O(n) time and space.
    static class Solution1 {

        public String shortestPalindrome(String s) {
            String reverse = new StringBuilder(s).reverse().toString();
            String combine = s + "#" + reverse;
            int[] table = new KMP1D(combine).restartTable;
            int palinLen = table[combine.length() - 1];
            if (palinLen == s.length()) return s;
            StringBuilder suffix = new StringBuilder(s.substring(palinLen)).reverse();
            return suffix.append(s).toString();
        }
    }

    // rolling hash, similar to rabin karp. O(n) time and space.
    static class Solution2 {

        public String shortestPalindrome(String s) {
            long base = 29;
            long mod = (long) 1e9 + 7;
            long forward = 0, reverse = 0, pow = 1;
            int end = -1; // palindrome end index
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                forward = (forward * base + (c - 'a' + 1)) % mod;
                reverse = (reverse + (c - 'a' + 1) * pow) % mod;
                pow = (pow * base) % mod;
                if (forward == reverse) end = i;
            }
            String suffix = s.substring(end + 1);
            StringBuilder reversedSuffix = new StringBuilder(suffix).reverse();
            return reversedSuffix.append(s).toString();
        }
    }

    // Manacher's algorithm, O(n) time and space. 5ms, 44.45mb.
    static class Solution3 {
        public String shortestPalindrome(String s) {
            // Return early if the string is null or empty
            if (s == null || s.isEmpty()) return s;
            int mppl = new Manacher(s).mppl;
            // Construct the shortest palindrome by reversing the suffix and prepending it to the original string
            StringBuilder suffix = new StringBuilder(s.substring(mppl)).reverse();
            return suffix.append(s).toString();
        }
    }

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
                    return reversedString.substring(0, i) + s;
            return "";
        }
    }
}
