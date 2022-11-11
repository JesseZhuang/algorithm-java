package princeton.jsl;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac Evaluate.java
 *  Execution:    java Evaluate
 *  Dependencies: Stack.java
 *
 *  Evaluates (fully parenthesized) arithmetic expressions using
 *  Dijkstra's two-stack algorithm.
 *
 *  % java Evaluate
 *  ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
 *  101.0
 *
 *  % java Evaulate
 *  ( ( 1 + sqrt ( 5 ) ) / 2.0 )
 *  1.618033988749895
 *
 *
 *  Note: the operators, operands, and parentheses must be
 *  separated by whitespace. Also, each operation must
 *  be enclosed in parentheses. For example, you must write
 *  ( 1 + ( 2 + 3 ) ) instead of ( 1 + 2 + 3 ).
 *  See EvaluateDeluxe.java for a fancier version.
 *
 *
 *  Remarkably, Dijkstra's algorithm computes the same
 *  answer if we put each operator *after* its two operands
 *  instead of *between* them.
 *
 *  % java Evaluate
 *  ( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )
 *  101.0
 *
 *  Moreover, in such expressions, all parentheses are redundant!
 *  Removing them yields an expression known as a postfix expression.
 *  1 2 3 + 4 5 * * +
 *
 *
 ******************************************************************************/

/**
 * Extensions. More ops, precedence order, associativity.
 */
public class EvaluateInfix {
    public static void main(String[] args) {
        String[] tests = new String[]{
                "sqrt ( 5 )", // 2.236
                "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )", // 212
                "( ( ( 5 + ( 7 * ( 1 + 1 ) ) ) * 3 ) + ( 2 * ( 1 + 1 ) ) )", // 61
                "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )", // 101
                "( ( 1 + sqrt ( 5 ) ) / 2.0 )" // 1.618033988749895
        };
        for (String test : tests) {
            String[] tokens = test.split(" ");
            Stack<String> ops = new Stack<>();
            Stack<Double> vals = new Stack<>();
            for (String token : tokens) {
                if (token.equals("(")) ;
                else if (token.equals("+")) ops.push(token);
                else if (token.equals("-")) ops.push(token);
                else if (token.equals("*")) ops.push(token);
                else if (token.equals("/")) ops.push(token);
                else if (token.equals("sqrt")) ops.push(token);
                else if (token.equals(")")) {
                    String op = ops.pop();
                    double v = vals.pop();
                    if (op.equals("+")) vals.push(vals.pop() + v);
                    else if (op.equals("-")) vals.push(vals.pop() - v);
                    else if (op.equals("*")) vals.push(vals.pop() * v);
                    else if (op.equals("/")) vals.push(vals.pop() / v);
                    else if (op.equals("sqrt")) vals.push(Math.sqrt(v));
                } else vals.push(Double.parseDouble(token));
            }
            StdOut.print(test + ": ");
            StdOut.println(vals.pop());
        }

    }
}
