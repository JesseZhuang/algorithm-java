package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumRemoveToMakeValidParenthesesTest {

    @Test
    void testExample1() {
        String result = MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("lee(t(c)o)de)");
        // Multiple valid answers: "lee(t(c)o)de", "lee(t(co)de)", "lee(t(c)ode)"
        assertTrue(isValid(result));
        assertEquals(12, result.length()); // should be 12 chars after removing 1 ')'
    }

    @Test
    void testExample2() {
        assertEquals("ab(c)d", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("a)b(c)d"));
    }

    @Test
    void testExample3() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("))(("));
    }

    @Test
    void testEmptyString() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid(""));
    }

    @Test
    void testAllValid() {
        assertEquals("a(b(c)d)e", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("a(b(c)d)e"));
    }

    @Test
    void testOnlyOpenParentheses() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("(((("));
    }

    @Test
    void testOnlyCloseParentheses() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("))))"));
    }

    @Test
    void testNested() {
        assertEquals("a((bc))", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("a((bc))"));
    }

    @Test
    void testMixedInvalid() {
        assertEquals("abc", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid(")a)b(c("));
    }

    @Test
    void testNoParentheses() {
        assertEquals("abcdef", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("abcdef"));
    }

    @Test
    void testSingleValidPair() {
        assertEquals("()", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("()"));
    }

    @Test
    void testMultipleValidPairs() {
        assertEquals("()()()", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("()()()"));
    }

    @Test
    void testUnmatchedAtStart() {
        assertEquals("(ab)", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid(")))((ab)"));
    }

    @Test
    void testUnmatchedAtEnd() {
        assertEquals("(ab)", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("(ab)((("));
    }

    @Test
    void testComplexMixed() {
        String result = MinimumRemoveToMakeValidParentheses.minRemoveToMakeValid("(a(b(c)d)e)f)g(");
        assertTrue(isValid(result));
    }

    // Test the two-pass approach
    @Test
    void testTwoPassExample1() {
        String result = MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("lee(t(c)o)de)");
        assertTrue(isValid(result));
        assertEquals(12, result.length());
    }

    @Test
    void testTwoPassExample2() {
        assertEquals("ab(c)d", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("a)b(c)d"));
    }

    @Test
    void testTwoPassExample3() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("))(("));
    }

    @Test
    void testTwoPassEmptyString() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass(""));
    }

    @Test
    void testTwoPassAllValid() {
        assertEquals("a(b(c)d)e", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("a(b(c)d)e"));
    }

    @Test
    void testTwoPassOnlyOpen() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("(((("));
    }

    @Test
    void testTwoPassOnlyClose() {
        assertEquals("", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("))))"));
    }

    @Test
    void testTwoPassMixedInvalid() {
        assertEquals("abc", MinimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass(")a)b(c("));
    }

    /**
     * Helper method to validate that a string has valid parentheses.
     */
    private boolean isValid(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }
}
