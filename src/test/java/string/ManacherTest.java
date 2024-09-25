package string;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManacherTest {

    Manacher tbt;

    @ParameterizedTest
    @CsvSource({"babcbabcbaccba,abcbabcba", "abaaba,abaaba", "caba,aba"})
    void longestPalindromicSubstring(String s, String longestPalindrome) {
        assertEquals(longestPalindrome, new Manacher(s).longestPalindromicSubstring());
    }

    @Test
    void testLongestPalindromicSubstring() {
        String s = "abba";
        tbt = new Manacher(s);
        String[] expected = {"a", "", "b", "abba", "b", "", "a"};
        for (int i = 0; i <= s.length() * 2 - 2; i++) {
            assertEquals(expected[i], tbt.longestPalindromicSubstring(i));
        }
    }
}