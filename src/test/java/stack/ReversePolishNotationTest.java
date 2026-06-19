package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReversePolishNotationTest {

    private final ReversePolishNotation rpn = new ReversePolishNotation();

    @Test
    void testExample1() {
        assertEquals(9, rpn.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    @Test
    void testExample2() {
        assertEquals(6, rpn.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    @Test
    void testExample3() {
        assertEquals(22, rpn.evalRPN(
                new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    @Test
    void testSingleNumber() {
        assertEquals(42, rpn.evalRPN(new String[]{"42"}));
    }

    @Test
    void testNegativeResult() {
        assertEquals(-1, rpn.evalRPN(new String[]{"1", "2", "-"}));
    }

    @Test
    void testDivisionTruncatesTowardZero() {
        // 6 / -132 = 0 (truncates toward zero, not floor)
        assertEquals(0, rpn.evalRPN(new String[]{"6", "-132", "/"}));
        // -7 / 2 = -3 (not -4)
        assertEquals(-3, rpn.evalRPN(new String[]{"-7", "2", "/"}));
    }

    @Test
    void testNegativeOperands() {
        // -3 + -4 = -7
        assertEquals(-7, rpn.evalRPN(new String[]{"-3", "-4", "+"}));
        // -3 * -4 = 12
        assertEquals(12, rpn.evalRPN(new String[]{"-3", "-4", "*"}));
    }
}
