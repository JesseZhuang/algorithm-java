package sliding;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EqualSubstringWithinBudgetTest {

    @ParameterizedTest
    @CsvSource({
            "abcd, bcdf, 3, 3",
            "abcd, cdef, 3, 1",
            "abcd, acde, 0, 1",
            "aaaaa, abdaa, 2, 2",
            "aaaaa, bcaaa, 2, 4",
            "abcde, abcde, 0, 5",
            "abc, bcd, 3, 3",
            "abc, def, 0, 0",
            "a, b, 1, 1",
            "a, z, 24, 0",
            "z, a, 25, 1"
    })
    void returnsLongestSubstringWithinBudget(String s, String t, int maxCost, int expected) {
        assertEquals(expected, EqualSubstringWithinBudget.equalSubstring(s, t, maxCost));
    }
}
