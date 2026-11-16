package sliding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfSubstringsContainingAllThreeCharactersTest {

    @ParameterizedTest
    @CsvSource({
            "abcabc, 10",
            "aaacb, 3",
            "abc, 1",
            "cba, 1",
            "aaaa, 0",
            "ababab, 0",
            "a, 0",
            "ab, 0",
            "aabbcc, 4",
            "abccba, 7",
            "'', 0"
    })
    void countsSubstringsContainingEveryCharacter(String s, int expected) {
        assertEquals(expected, NumberOfSubstringsContainingAllThreeCharacters.numberOfSubstrings(s));
        assertEquals(expected, NumberOfSubstringsContainingAllThreeCharacters.numberOfSubstringsSlidingWindow(s));
    }

    @Test
    void countsSubstringsAtMaximumLength() {
        String s = "abc".repeat(16_666) + "ab";
        assertEquals(1_249_925_001,
                NumberOfSubstringsContainingAllThreeCharacters.numberOfSubstrings(s));
        assertEquals(1_249_925_001,
                NumberOfSubstringsContainingAllThreeCharacters.numberOfSubstringsSlidingWindow(s));
    }
}
