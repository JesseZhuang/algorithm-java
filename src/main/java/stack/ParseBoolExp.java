package stack;

import jdk.jshell.JShell;

import javax.script.ScriptException;
import java.util.Stack;

/**
 * LeetCode 1106, hard, tags: recursion, stack, string.
 * <p>
 * A boolean expression is an expression that evaluates to either true or false. It can be in one of the following
 * shapes:
 * <p>
 * 't' that evaluates to true.
 * 'f' that evaluates to false.
 * '!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
 * '&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions
 * subExpr1, subExpr2, ..., subExprn where n >= 1.
 * '|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions
 * subExpr1, subExpr2, ..., subExprn where n >= 1.
 * Given a string expression that represents a boolean expression, return the evaluation of that expression.
 * <p>
 * It is guaranteed that the given expression is valid and follows the given rules.
 * <p>
 * Example 1:
 * <p>
 * Input: expression = "&(|(f))"
 * Output: false
 * Explanation:
 * First, evaluate |(f) --> f. The expression is now "&(f)".
 * Then, evaluate &(f) --> f. The expression is now "f".
 * Finally, return false.
 * Example 2:
 * <p>
 * Input: expression = "|(f,f,f,t)"
 * Output: true
 * Explanation: The evaluation of (false OR false OR false OR true) is true.
 * Example 3:
 * <p>
 * Input: expression = "!(&(f,t))"
 * Output: true
 * Explanation:
 * First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
 * Then, evaluate !(f) --> NOT false --> true. We return true.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= expression.length <= 2 * 10^4
 * expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.
 * <p>
 * Hint 1
 * Write a function "parse" which calls helper functions "parse_or", "parse_and", "parse_not".
 */
@SuppressWarnings("unused")
public class ParseBoolExp {
    // optimized stack solution, n, n.
    static class Solution1 {
        public boolean parseBoolExpr(String expression) {
            Stack<Character> st = new Stack<>();
            for (char c : expression.toCharArray()) {
                if (c == ',' || c == '(') continue;
                // Push operators and boolean values to the stack
                if (c == 't' || c == 'f' || c == '!' || c == '&' || c == '|') st.push(c);
                    // Handle closing parentheses and evaluate the subexpression
                else if (c == ')') {
                    boolean hasTrue = false, hasFalse = false;
                    // Process the values inside the parentheses
                    while (st.peek() != '!' && st.peek() != '&' && st.peek() != '|') {
                        char ch = st.pop();
                        if (hasTrue && hasFalse) continue; // small optimization
                        if (ch == 't') hasTrue = true;
                        if (ch == 'f') hasFalse = true;
                    }
                    // Pop the operator and evaluate the subexpression
                    char op = st.pop();
                    if (op == '!') st.push(hasTrue ? 'f' : 't');
                    else if (op == '&') st.push(hasFalse ? 'f' : 't');
                    else st.push(hasTrue ? 't' : 'f');
                }
            }
            return st.peek() == 't';
        }
    }

    // recursive, n, n
    // another solution string manipulation n^2, n.
    static class Solution2 {

        public boolean parseBoolExpr(String expression) {
            return parse(expression, 0, expression.length());
        }

        private boolean parse(String s, int lo, int hi) {
            char c = s.charAt(lo);
            if (hi - lo == 1) return c == 't'; // base case.
            boolean res = c == '&'; // only when c is &, set res to true; otherwise(|,!) false.
            for (int i = lo + 2, start = i, level = 0; i < hi; ++i) {
                char d = s.charAt(i);
                if (level == 0 && (d == ',' || d == ')')) { // a valid sub-expression.
                    boolean cur = parse(s, start, i); // recurse to sub-problem.
                    start = i + 1; // next sub-expression start index.
                    if (c == '&') res &= cur;
                    else if (c == '|') res |= cur;
                    else res = !cur; // c == '!'.
                }
                if (d == '(') ++level;
                if (d == ')') --level;
            }
            return res;
        }
    }

    static class Solution3 {
        // stackoverflow java eval
        public static boolean parseBoolExpr1(String expression) throws ScriptException {
            // method 1, gradle 9.0 does not build
//            ScriptEngineManager manager = new ScriptEngineManager();
//            ScriptEngine engine = manager.getEngineByName("js");
//            System.out.println(engine.eval("4*5"));
//            System.out.println(engine.eval(expression));
            // method 2
            try (JShell js = JShell.create()) {
                return js.eval(js.sourceCodeAnalysis().analyzeCompletion(expression).source())
                        .getFirst().value().equals("true");
            }
        }

        public static void main(String[] args) throws ScriptException {
            System.out.println(parseBoolExpr1("true || false"));
        }
    }
}
