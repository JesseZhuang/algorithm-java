package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 227, medium, tags: math, string, stack.
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 * You may assume that the given expression is always valid. All intermediate results will be
 * in the range of [-231, 231 - 1].
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as
 * mathematical expressions, such as eval().
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 * <p>
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 * <p>
 * Input: s = " 3+5 / 2 "
 * Output: 5
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculatorII {
    // 20ms, 44.3Mb. O(n) time and space.
    public static int calculate(String s) {
        Deque<Integer> vals = new ArrayDeque<>();
        char lastOp = '+';
        for (int i = 0, n = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) n = n * 10 + s.charAt(i) - '0';
            if (!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i) || i == s.length() - 1) {
                if (lastOp == '+') vals.push(n);
                else if (lastOp == '-') vals.push(-n);
                else if (lastOp == '*') vals.push(vals.pop() * n);
                else if (lastOp == '/') vals.push(vals.pop() / n);
                lastOp = s.charAt(i);
                n = 0;
            }
        }
        int val = 0;
        while (!vals.isEmpty()) val += vals.pop();
        return val;
    }

    // O(N) time, O(1) space. 34ms, 46.1Mb.
    public static int calculate2(String s) {
        int length = s.length();
        int curVal = 0, lastVal = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) curVal = (curVal * 10) + (currentChar - '0');
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastVal;
                    lastVal = (operation == '+') ? curVal : -curVal;
                } else if (operation == '*') {
                    lastVal = lastVal * curVal;
                } else if (operation == '/') {
                    lastVal = lastVal / curVal;
                }
                operation = currentChar;
                curVal = 0;
            }
        }
        result += lastVal;
        return result;
    }

    public static void main(String[] args) {
        String[] tests = {"3+2*2", " 3/2 ", " 3+5 / 2 ", "1-1+1"};
        int[] expected = {7, 1, 5, 1};
        for (int i = 0; i < tests.length; i++) {
            System.out.println(calculate(tests[i]));
        }
    }
}
