package string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongestPalindromeTest {

    @ParameterizedTest
    @CsvSource({"babad,3", "cbbd,2", "a,1", "aa,2", "aaaa,4", "racecar,7", "xabcbay,5"})
    void testSolution1(String s, int expectedLen) {
        LongestPalindrome.Solution1 sol = new LongestPalindrome.Solution1();
        String res = sol.longestPalindrome(s);
        assertEquals(expectedLen, res.length());
        assertTrue(isPalindrome(res));
    }

    @ParameterizedTest
    @CsvSource({"babad,3", "cbbd,2", "a,1", "aa,2", "aaaa,4", "racecar,7", "xabcbay,5"})
    void testSolution2(String s, int expectedLen) {
        LongestPalindrome.Solution2 sol = new LongestPalindrome.Solution2();
        String res = sol.longestPalindrome(s);
        assertEquals(expectedLen, res.length());
        assertTrue(isPalindrome(res));
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
