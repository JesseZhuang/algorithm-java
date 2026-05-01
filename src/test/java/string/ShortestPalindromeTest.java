package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortestPalindromeTest {
    private static ShortestPalindrome.Solution1 tbt1;
    private static ShortestPalindrome.Solution2 tbt2;

    @BeforeAll
    static void setup() {
        tbt1 = new ShortestPalindrome.Solution1();
        tbt2 = new ShortestPalindrome.Solution2();
    }

    @ParameterizedTest(name = "shortestPalindrome({0}) = {1}")
    @CsvSource({
            "aacecaaa, aaacecaaa",
            "abcd, dcbabcd",
            "a, a",
            "aba, aba",
            "aaabc, cbaaabc",
    })
    void testShortestPalindrome(String input, String expected) {
        assertEquals(expected, tbt1.shortestPalindrome(input));
        assertEquals(expected, tbt2.shortestPalindrome(input));
    }
}
