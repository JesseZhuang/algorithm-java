package stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestValidParenthesesTest {

    @ParameterizedTest
    @CsvSource({
        "'(())',    4",
        "')()())', 4",
        "'',       0",
        "'(',      0",
        "')',      0",
        "'((((',   0",
        "'()()',   4",
        "'()()()', 6",
        "'((()))', 6",
        "'()(())', 6",
        "'>(())()(()))(', 10",
        "'()()()(', 6",
    })
    void testStack(String s, int expected) {
        // CsvSource encodes ">(())()(()))(": leading > stands in for )
        String input = s.replace('>', ')');
        assertEquals(expected, LongestValidParentheses.longestValidParenthesesStack(input));
    }

    @ParameterizedTest
    @CsvSource({
        "'(())',    4",
        "')()())', 4",
        "'',       0",
        "'(',      0",
        "')',      0",
        "'((((',   0",
        "'()()',   4",
        "'()()()', 6",
        "'((()))', 6",
        "'()(())', 6",
        "'>(())()(()))(', 10",
        "'()()()(', 6",
    })
    void testTwoPass(String s, int expected) {
        String input = s.replace('>', ')');
        assertEquals(expected, LongestValidParentheses.longestValidParenthesesTwoPass(input));
    }
}
