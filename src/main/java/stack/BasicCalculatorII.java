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
 * 1 <= s.length <= 3 * 10^5
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */
@SuppressWarnings("unused")
public class BasicCalculatorII {

    // solution 1 with dummy end, same complexity as solution 1 below. 14ms, 44.23mb.
    public static int calculate3(String s) {
        int cur = 0, last = 0, res = 0; // 3 tier cache
        s = s + "##";  // 3+2*2, for the last two #: last*=cur 4=2*2 then res+=last 7=3+4
        char prevOp = '+'; // operation
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isWhitespace(c)) continue;
            if (Character.isDigit(c)) cur = c + cur * 10 - '0';
            else {
                if (prevOp == '*') last *= cur; // fold cur->last
                else if (prevOp == '/') last /= cur; // fold cur->last
                else {
                    res += last; // accumulate last to res
                    last = prevOp == '+' ? cur : -cur; // set last to cur
                }
                prevOp = c;
                cur = 0; // res+=last, last=cur or -cur, cur=0
            }
        }
        return res;
    }


    // solution 1, O(N) time, O(1) space. 34ms, 46.1Mb.
    public static int calculate2(String s) {
        int length = s.length();
        int cur = 0, last = 0, res = 0;
        char op = '+'; // operation
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) cur = (cur * 10) + (c - '0');
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == length - 1) {// not else if
                if (op == '+' || op == '-') {
                    res += last;
                    last = (op == '+') ? cur : -cur;
                } else if (op == '*') last = last * cur;
                else if (op == '/') last = last / cur;
                op = c;
                cur = 0;
            }
        }
        res += last;
        return res;
    }

    // solution 2, stack, 20ms, 44.3Mb. O(n) time and space.
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

    public static void main(String[] args) {
        String[] tests = {"3+2*2", " 3/2 ", " 3+5 / 2 ", "1-1+1", "2147483647"};
        int[] expected = {7, 1, 5, 1, 2147483647};
        for (String test : tests) {
            System.out.println(calculate3(test));
        }
    }
}
