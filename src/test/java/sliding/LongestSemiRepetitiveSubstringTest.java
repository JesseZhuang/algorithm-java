package sliding;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSemiRepetitiveSubstringTest {

    @ParameterizedTest
    @CsvSource({
            "52233, 4",
            "5494, 4",
            "123456, 6",
            "122345, 6",
            "112233, 4",
            "1233112, 5",
            "111111, 2",
            "1123455, 6",
            "12, 2",
            "11, 2",
            "5, 1"
    })
    void returnsLongestSubstringWithAtMostOneEqualAdjacentPair(String input, int expected) {
        assertEquals(expected, LongestSemiRepetitiveSubstring.longestSemiRepetitiveSubstring(input));
    }
}
