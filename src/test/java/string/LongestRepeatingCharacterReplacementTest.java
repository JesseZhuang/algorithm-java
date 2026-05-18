package string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestRepeatingCharacterReplacementTest {

    @ParameterizedTest(name = "slidingWindow(\"{0}\", {1}) = {2}")
    @CsvSource({
            "ABAB, 2, 4",
            "AABABBA, 1, 4",
            "A, 0, 1",
            "AAAA, 2, 4",
            "AABBC, 0, 2",
            "ABCDE, 4, 5",
            "ABABAB, 2, 5",
            "ABCAA, 2, 5",
    })
    void testSlidingWindow(String s, int k, int expected) {
        assertEquals(expected, LongestRepeatingCharacterReplacement.slidingWindow(s, k));
    }

    @ParameterizedTest(name = "binarySearch(\"{0}\", {1}) = {2}")
    @CsvSource({
            "ABAB, 2, 4",
            "AABABBA, 1, 4",
            "A, 0, 1",
            "AAAA, 2, 4",
            "AABBC, 0, 2",
            "ABCDE, 4, 5",
            "ABABAB, 2, 5",
            "ABCAA, 2, 5",
    })
    void testBinarySearch(String s, int k, int expected) {
        assertEquals(expected, LongestRepeatingCharacterReplacement.binarySearch(s, k));
    }
}
