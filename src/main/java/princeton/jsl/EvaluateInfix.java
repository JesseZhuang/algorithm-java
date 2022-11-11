package princeton.jsl;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Extensions. More ops, precedence order, associativity.
 */
public class EvaluateInfix {
    public static void main(String[] args) {

        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        String[] tests = new String[]{
                "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )",
                "( ( ( 5 + ( 7 * ( 1 + 1 ) ) ) * 3 ) + ( 2 * ( 1 + 1 ) ) )",
                "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"
        };
        for (String test : tests) {
            String[] tokens = test.split(" ");
            for (String token : tokens) {
                if (token.equals("(")) ;
                else if (token.equals("+")) ops.push(token);
                else if (token.equals("*")) ops.push(token);
                else if (token.equals(")")) {
                    String op = ops.pop();
                    if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                    else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
                } else vals.push(Double.parseDouble(token));
            }
            StdOut.print(test + ": ");
            StdOut.println(vals.pop());
        }

    }
}
