package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LetterCPhoneNumberTest {

    static LetterCPhoneNumber tbt;

    @BeforeAll
    static void setup() {
        tbt = new LetterCPhoneNumber();
    }

    @Test
    void testDigits23() {
        List<String> expected = List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        List<String> res1 = tbt.letterCombinations("23");
        List<String> res2 = tbt.letterCombinationsQ("23");
        Collections.sort(res1);
        Collections.sort(res2);
        assertEquals(expected, res1);
        assertEquals(expected, res2);
    }

    @Test
    void testEmpty() {
        assertEquals(List.of(), tbt.letterCombinations(""));
        assertEquals(List.of(), tbt.letterCombinationsQ(""));
    }

    @Test
    void testSingleDigit2() {
        List<String> expected = List.of("a", "b", "c");
        assertEquals(expected, tbt.letterCombinations("2"));
        assertEquals(expected, tbt.letterCombinationsQ("2"));
    }

    @Test
    void testSingleDigit7() {
        List<String> expected = List.of("p", "q", "r", "s");
        assertEquals(expected, tbt.letterCombinations("7"));
        assertEquals(expected, tbt.letterCombinationsQ("7"));
    }

    @Test
    void testSingleDigit9() {
        List<String> expected = List.of("w", "x", "y", "z");
        assertEquals(expected, tbt.letterCombinations("9"));
        assertEquals(expected, tbt.letterCombinationsQ("9"));
    }

    @Test
    void testThreeDigits234() {
        List<String> res1 = tbt.letterCombinations("234");
        List<String> res2 = tbt.letterCombinationsQ("234");
        assertEquals(27, res1.size());
        assertEquals(27, res2.size());
        assertTrue(res1.contains("adg"));
        assertTrue(res2.contains("adg"));
    }

    @Test
    void testTwoDigits22() {
        List<String> res1 = tbt.letterCombinations("22");
        List<String> res2 = tbt.letterCombinationsQ("22");
        assertEquals(9, res1.size());
        assertEquals(9, res2.size());
        assertTrue(res1.contains("aa"));
        assertTrue(res2.contains("aa"));
    }
}
