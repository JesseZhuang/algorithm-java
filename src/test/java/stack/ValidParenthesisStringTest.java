package stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidParenthesisStringTest {

    @Test
    void testBasicValid() {
        assertTrue(ValidParenthesisString.checkValidString("()"));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass("()"));
    }

    @Test
    void testStarAsEmpty() {
        assertTrue(ValidParenthesisString.checkValidString("(*)"));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass("(*)"));
    }

    @Test
    void testStarAsOpen() {
        assertTrue(ValidParenthesisString.checkValidString("(*))"));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass("(*))"));
    }

    @Test
    void testStarAsClose() {
        // "(*" is valid: '*' treated as ')'
        assertTrue(ValidParenthesisString.checkValidString("(*"));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass("(*"));
    }

    @Test
    void testAllStars() {
        assertTrue(ValidParenthesisString.checkValidString("***"));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass("***"));
    }

    @Test
    void testSingleStar() {
        assertTrue(ValidParenthesisString.checkValidString("*"));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass("*"));
    }

    @Test
    void testEmpty() {
        // Edge: length >= 1 per constraints, but empty string is trivially valid
        assertTrue(ValidParenthesisString.checkValidString(""));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {")(", "(()", "(()))(", ")*("})
    void testInvalid(String s) {
        assertFalse(ValidParenthesisString.checkValidString(s));
        assertFalse(ValidParenthesisString.checkValidStringTwoPass(s));
    }

    @Test
    void testComplexValid() {
        assertTrue(ValidParenthesisString.checkValidString("(*()*"));
        assertTrue(ValidParenthesisString.checkValidStringTwoPass("(*()*"));
    }

    @Test
    void testOnlyOpenParen() {
        assertFalse(ValidParenthesisString.checkValidString("("));
        assertFalse(ValidParenthesisString.checkValidStringTwoPass("("));
    }

    @Test
    void testOnlyCloseParen() {
        assertFalse(ValidParenthesisString.checkValidString(")"));
        assertFalse(ValidParenthesisString.checkValidStringTwoPass(")"));
    }
}
