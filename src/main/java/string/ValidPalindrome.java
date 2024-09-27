package string;

/**
 * LeetCode 125, easy, tags: two pointers, string.
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
 * all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include
 * letters and numbers.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 * <p>
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 * <p>
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 */
public class ValidPalindrome {
    // 3ms 41.9 Mb. two pointer, O(n) time, O(1) space.
    public boolean isPalindrome2P(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            while (!Character.isLetterOrDigit(s.charAt(l)) && l < r) l++;
            while (!Character.isLetterOrDigit(s.charAt(r)) && l < r) r--;
            if (l < r && Character.toUpperCase(s.charAt(l)) != Character.toUpperCase(s.charAt(r)))
                return false;
        }
        return true;
    }

    // O(n) time and space. 659ms, 43.3Mb.
    public boolean isPalindromeR(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuilder(actual).reverse().toString();
        return actual.equals(rev);
    }
}
