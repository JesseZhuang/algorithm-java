package string;

public class ValidPalindrome {
    // O(n) time and space. 659ms, 43.3Mb.
    public boolean isPalindromeR(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
