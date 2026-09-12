package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public final class LongestValidParentheses {

    private LongestValidParentheses() {}

    public static int longestValidParenthesesStack(String s) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // base index
        for (int i = 0; i < s.length(); i++) { // O(n)
            if (s.charAt(i) == '(') {
                stack.push(i); // each index pushed/popped once, O(n) total
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // new base
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public static int longestValidParenthesesTwoPass(String s) {
        int max = 0;
        int open = 0, close = 0;
        for (int i = 0; i < s.length(); i++) { // O(n) left-to-right
            if (s.charAt(i) == '(') open++;
            else close++;
            if (open == close) max = Math.max(max, 2 * close);
            else if (close > open) { open = 0; close = 0; }
        }
        open = 0; close = 0;
        for (int i = s.length() - 1; i >= 0; i--) { // O(n) right-to-left
            if (s.charAt(i) == '(') open++;
            else close++;
            if (open == close) max = Math.max(max, 2 * open);
            else if (open > close) { open = 0; close = 0; }
        }
        return max;
    }
}
