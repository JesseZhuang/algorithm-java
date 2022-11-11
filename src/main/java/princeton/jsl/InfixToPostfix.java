package princeton.jsl;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac InfixToPostfix.java
 *  Execution:    java InfixToPostFix
 *  Dependencies: Stack.java StdIn.java StdOut.java
 *
 *  Reads in a fully parenthesized infix expression from standard input
 *  and prints an equivalent postfix expression to standard output.
 *
 *  Windows users: replace [Ctrl-d] with [Ctrl-z] to signify end of file.
 *
 *  % java InfixToPostfix
 *  ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
 *  [Ctrl-d]
 *  2 3 4 + 5 6 * * +
 *
 *  % java InfixToPostfix
 *  ( ( ( 5 + ( 7 * ( 1 + 1 ) ) ) * 3 ) + ( 2 * ( 1 + 1 ) ) )
 *  5 7 1 1 + * + 3 * 2 1 1 + * +
 *
 *  % java InfixToPostfix | java EvaluatePostfix
 *  ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
 *  [Ctrl-d]
 *  212
 *
 ******************************************************************************/

public class InfixToPostfix {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        String[] tests = new String[]{
                "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )",
                "( ( ( 5 + ( 7 * ( 1 + 1 ) ) ) * 3 ) + ( 2 * ( 1 + 1 ) ) )",
                "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"
        };
        for (String test : tests) {
            String[] tokens = test.split(" ");
            StringBuilder postFix = new StringBuilder();
            for (String token : tokens) {
                if (token.equals("+")) stack.push(token);
                else if (token.equals("*")) stack.push(token);
                else if (token.equals(")")) postFix.append(stack.pop() + " ");
                else if (token.equals("("));
                else postFix.append(token + " ");
            }
            StdOut.println(postFix.toString());
        }
    }
}
