package string;

/**
 * LeetCode 2232, medium, tags: string, enumeration.
 * Companies: pinterest.
 * <p>
 * You are given a 0-indexed string expression of the form "<num1>+<num2>" where <num1> and <num2>
 * represent positive integers.
 * <p>
 * Add a pair of parentheses to expression such that after the addition of parentheses, expression
 * is a valid mathematical expression and evaluates to the smallest possible value. The left parenthesis must
 * be added to the left of '+' and the right parenthesis must be added to the right of '+'.
 * <p>
 * Return expression after adding a pair of parentheses such that expression evaluates to the smallest possible value.
 * If there are multiple answers that yield the same result, return any of them.
 * <p>
 * The input has been generated such that the original value of expression, and the value of expression after adding
 * any pair of parentheses that meets the requirements fits within a signed 32-bit integer.
 * <p>
 * Example 1:
 * <p>
 * Input: expression = "247+38"
 * Output: "2(47+38)"
 * Explanation: The expression evaluates to 2 * (47 + 38) = 2 * 85 = 170.
 * Note that "2(4)7+38" is invalid because the right parenthesis must be to the right of the '+'.
 * It can be shown that 170 is the smallest possible value.
 * Example 2:
 * <p>
 * Input: expression = "12+34"
 * Output: "1(2+3)4"
 * Explanation: The expression evaluates to 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20.
 * Example 3:
 * <p>
 * Input: expression = "999+999"
 * Output: "(999+999)"
 * Explanation: The expression evaluates to 999 + 999 = 1998.
 * <p>
 * Constraints:
 * <p>
 * 3 <= expression.length <= 10, string lengths: n+m+1
 * expression consists of digits from '1' to '9' and '+'.
 * expression starts and ends with digits.
 * expression contains exactly one '+'.
 * The original value of expression, and the value of expression after adding any pair of parentheses that meets
 * the requirements fits within a signed 32-bit integer.
 * <p>
 * Hint 1
 * The maximum length of expression is very low. We can try every possible spot to place the parentheses.
 * Hint 2
 * Every possibility of expression is of the form a * (b + c) * d where a, b, c, d represent integers. Note the edge
 * cases where a and/or d do not exist, in which case use 1 instead of them.
 */
public class MRParenExp {
    public static void main(String[] args) {
        MRParenExp tbt = new MRParenExp();
        System.out.println(tbt.minimizeResult("237+48")); // 2(37+48)
    }

    // O(mn) time, O(m+n) or O(1) (considering space for result string, can optimize with StringBuilder) space
    public String minimizeResult(String expression) {
        String[] sp = expression.split("\\+"); // + can be used as quantifier, needs escaping
        String left = sp[0], right = sp[1];
        // l, r Index at which we add ( and ), r=1 at least one digit before )
        int l = 0, r = 1, min = Integer.MAX_VALUE;
        for (int i = 0; i < left.length(); i++) { // note <, at least one digit left of +
            int a = i == 0 ? 1 : Integer.parseInt(left.substring(0, i));
            int b = Integer.parseInt(left.substring(i));
            for (int j = 1; j <= right.length(); j++) { // nested for loop must init r inside, <=, ')' can be at end
                int c = Integer.parseInt(right.substring(0, j));
                int d = j == right.length() ? 1 : Integer.parseInt(right.substring(j)); // note not r.length-1
                int res = a * (b + c) * d;
                if (res < min) {
                    min = res;
                    l = i;
                    r = j;
                }
            }
        }
        return left.substring(0, l) + "(" + left.substring(l) + "+"
                + right.substring(0, r) + ")" + right.substring(r);
    }
}
