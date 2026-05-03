package dp;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenParenthesesTest {
    private final GenParentheses sol = new GenParentheses();

    @Test void dpN1() {
        List<String> res = sol.generateParenthesis(1);
        Collections.sort(res);
        assertEquals(List.of("()"), res);
    }

    @Test void dpN2() {
        List<String> res = sol.generateParenthesis(2);
        Collections.sort(res);
        assertEquals(List.of("(())", "()()"), res);
    }

    @Test void dpN3() {
        List<String> res = sol.generateParenthesis(3);
        Collections.sort(res);
        assertEquals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"), res);
    }

    @Test void dpN4() {
        assertEquals(14, sol.generateParenthesis(4).size());
    }

    @Test void btN1() {
        List<String> res = sol.generateParenthesisBT(1);
        Collections.sort(res);
        assertEquals(List.of("()"), res);
    }

    @Test void btN2() {
        List<String> res = sol.generateParenthesisBT(2);
        Collections.sort(res);
        assertEquals(List.of("(())", "()()"), res);
    }

    @Test void btN3() {
        List<String> res = sol.generateParenthesisBT(3);
        Collections.sort(res);
        assertEquals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"), res);
    }

    @Test void btN4() {
        assertEquals(14, sol.generateParenthesisBT(4).size());
    }
}
