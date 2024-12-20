package stack;

import java.util.ArrayDeque;

/**
 * LeetCode 150, medium, tags: array, math, stack.
 * <p>
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * <p>
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 * <p>
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= tokens.length <= 10^4
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class ReversePolishNotation {
    // 5ms, 41.3 Mb. O(n) time and space.
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            int val1, val2;
            switch (tokens[i]) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    val2 = stack.pop();
                    val1 = stack.pop();
                    stack.push(val1 - val2);
                }
                case "/" -> {
                    val2 = stack.pop();
                    val1 = stack.pop();
                    stack.push(val1 / val2);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                default -> stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}
