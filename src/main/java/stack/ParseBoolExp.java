package stack;

import java.util.ArrayList;
import java.util.List;
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

            // Traverse through the expression
            for (char currChar : expression.toCharArray()) {
                if (currChar == ',' || currChar == '(') continue; // Skip commas and open parentheses

                // Push operators and boolean values to the stack
                if (
                        currChar == 't' ||
                                currChar == 'f' ||
                                currChar == '!' ||
                                currChar == '&' ||
                                currChar == '|'
                ) st.push(currChar);
                    // Handle closing parentheses and evaluate the subexpression
                else if (currChar == ')') {
                    boolean hasTrue = false, hasFalse = false;

                    // Process the values inside the parentheses
                    while (
                            st.peek() != '!' && st.peek() != '&' && st.peek() != '|'
                    ) {
                        char topValue = st.pop();
                        if (topValue == 't') hasTrue = true;
                        if (topValue == 'f') hasFalse = true;
                    }

                    // Pop the operator and evaluate the subexpression
                    char op = st.pop();
                    if (op == '!') {
                        st.push(hasTrue ? 'f' : 't');
                    } else if (op == '&') {
                        st.push(hasFalse ? 'f' : 't');
                    } else {
                        st.push(hasTrue ? 't' : 'f');
                    }
                }
            }

            // The final result is at the top of the stack
            return st.peek() == 't';
        }
    }

    // recursive, n, n
    // another solution string manipulation n^2, n.
    static class Solution2 {

        int index = 0;

        public boolean parseBoolExpr(String expression) {
            return evaluate(expression);
        }

        // Recursively parse and evaluate the boolean expression
        private boolean evaluate(String expr) {
            char currChar = expr.charAt(index++);

            // Base cases: true ('t') or false ('f')
            if (currChar == 't') return true;
            if (currChar == 'f') return false;

            // Handle NOT operation '!(...)'
            if (currChar == '!') {
                index++; // Skip '('
                boolean result = !evaluate(expr);
                index++; // Skip ')'
                return result;
            }

            // Handle AND '&(...)' and OR '|(...)'
            List<Boolean> values = new ArrayList<>();
            index++; // Skip '('
            while (expr.charAt(index) != ')') {
                if (expr.charAt(index) != ',') {
                    values.add(evaluate(expr)); // Collect results of subexpressions
                } else {
                    index++; // Skip commas
                }
            }
            index++; // Skip ')'

            // Manual AND operation: returns false if any value is false
            if (currChar == '&') {
                for (Boolean v : values) {
                    if (!v) return false;
                }
                return true;
            }

            // Manual OR operation: returns true if any value is true
            if (currChar == '|') {
                for (Boolean v : values) {
                    if (v) return true;
                }
                return false;
            }

            return false; // This point should never be reached
        }
    }
}
