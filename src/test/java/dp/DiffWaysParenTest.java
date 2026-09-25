package dp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiffWaysParenTest {
    final DiffWaysParen.Solution1 s1 = new DiffWaysParen.Solution1();
    final DiffWaysParen.Solution2 s2 = new DiffWaysParen.Solution2();

    private List<Integer> sorted(List<Integer> list) {
        List<Integer> copy = new java.util.ArrayList<>(list);
        copy.sort(null);
        return copy;
    }

    private void check(String expression, List<Integer> expected) {
        assertEquals(expected, sorted(s1.diffWaysToCompute(expression)));
        assertEquals(expected, sorted(s2.diffWaysToCompute(expression)));
    }

    @Test
    void testExample1() {
        check("2-1-1", List.of(0, 2));
    }

    @Test
    void testExample2() {
        check("2*3-4*5", List.of(-34, -14, -10, -10, 10));
    }

    @Test
    void testSingleNumber() {
        check("3", List.of(3));
    }

    @Test
    void testTwoDigitNumber() {
        check("11", List.of(11));
    }

    @Test
    void testSingleOperatorAdd() {
        check("2+3", List.of(5));
    }

    @Test
    void testSingleOperatorSub() {
        check("5-2", List.of(3));
    }

    @Test
    void testSingleOperatorMul() {
        check("4*3", List.of(12));
    }

    @Test
    void testAllAddition() {
        check("1+2+3", List.of(6, 6));
    }

    @Test
    void testAllMultiplication() {
        check("2*3*4", List.of(24, 24));
    }

    @Test
    void testMixed() {
        check("1+2*3", List.of(7, 9));
    }

    @Test
    void testTwoDigitOperands() {
        check("10+5", List.of(15));
    }

    @Test
    void testZeros() {
        check("0+0", List.of(0));
    }

    @Test
    void testNegativeResults() {
        check("1-2-3", List.of(-4, 2));
    }
}
