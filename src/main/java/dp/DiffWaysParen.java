package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 241, medium, tags: math, dp, string, recursion, memoization.
 * <p>
 * Given a string expression of numbers and operators, return all possible results from computing all the different
 * possible ways to group numbers and operators. You may return the answer in any order.
 * <p>
 * The test cases are generated such that the output values fit in a 32-bit integer and the number of different
 * results does not exceed 10^4.
 * <p>
 * Example 1:
 * <p>
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 * <p>
 * Input: expression = "2*3-4*5"
 * Output: [-34,-14,-10,-10,10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= expression.length <= 20
 * expression consists of digits and the operator '+', '-', and '*'.
 * All the integer values in the input expression are in the range [0, 99].
 * The integer values in the input expression do not have a leading '-' or '+' denoting the sign.
 */
@SuppressWarnings("unused")
public class DiffWaysParen {
    // solution 1, tabulation, O(n2^n) time and O(2^n) space. No recursive stack space. 1ms, 42.10mb.
    // time: catalan number T(n)=sum(T(1)*T(n-2)+T(2)*T(n-2)...) T(0)==0, T(1)==constant 4^n/(n^1.5*sqrt(pi))
    static class Solution1 {
        String e;
        List<Integer>[][] dp;

        public List<Integer> diffWaysToCompute(String expression) {
            int n = expression.length();
            this.e = expression;
            dp = new ArrayList[n][n]; // dp[i][j]: possible res for expression[i,j]
            initBaseCases();
            for (int length = 3; length <= n; length++)// Fill the dp table for all possible subexpressions
                for (int start = 0; start + length - 1 < n; start++)
                    processSubexpression(start, start + length - 1);
            return dp[0][n - 1];
        }

        // base cases: single digits and two-digit numbers
        private void initBaseCases() {
            int n = e.length();
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dp[i][j] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (Character.isDigit(e.charAt(i))) {
                    int dig1 = e.charAt(i) - '0';
                    if (i + 1 < n && Character.isDigit(e.charAt(i + 1))) {
                        int dig2 = e.charAt(i + 1) - '0';
                        int number = dig1 * 10 + dig2;
                        dp[i][i + 1].add(number);
                    }
                    dp[i][i].add(dig1);
                }
            }
        }

        // Try all possible positions to split the e
        private void processSubexpression(int start, int end) {
            for (int split = start; split <= end; split++) {
                if (Character.isDigit(e.charAt(split))) continue; // only process when split is an op
                List<Integer> leftResults = dp[start][split - 1];
                List<Integer> rightResults = dp[split + 1][end];
                computeResults(e.charAt(split), leftResults,
                        rightResults, dp[start][end]);
            }
        }

        // Compute res based on the operator at position 'split'
        private void computeResults(char op, List<Integer> leftRes,
                                    List<Integer> rightRes, List<Integer> res) {
            for (int lv : leftRes)
                for (int rv : rightRes)
                    switch (op) {
                        case '+' -> res.add(lv + rv);
                        case '-' -> res.add(lv - rv);
                        case '*' -> res.add(lv * rv);
                    }
        }
    }

    // solution 2, dp/memo, same complexity with recursive space. 1ms, 41.80mb.
    static class Solution2 {
        String e;
        List<Integer>[][] memo;

        public List<Integer> diffWaysToCompute(String expression) {
            this.e = expression;
            memo = new ArrayList[expression.length()][expression.length()];
            return compute(0, expression.length() - 1);
        }

        private List<Integer> compute(int start, int end) {
            if (memo[start][end] != null) return memo[start][end];
            List<Integer> res = new ArrayList<>();
            if (start == end) { // Base case: single digit
                res.add(e.charAt(start) - '0');
                return res;
            }
            if (end - start == 1 && Character.isDigit(e.charAt(start))) { // Base case: two-digit number
                int tens = e.charAt(start) - '0';
                int ones = e.charAt(end) - '0';
                res.add(10 * tens + ones);
                return res;
            }
            // Recursive case: split the e at each operator
            for (int i = start; i <= end; i++) {
                char c = e.charAt(i);
                if (Character.isDigit(c)) continue;
                List<Integer> leftRes = compute(start, i - 1);
                List<Integer> rightRes = compute(i + 1, end);
                // Combine res from left and right subexpressions
                for (int lv : leftRes) {
                    for (int rv : rightRes) {
                        switch (c) {
                            case '+' -> res.add(lv + rv);
                            case '-' -> res.add(lv - rv);
                            case '*' -> res.add(lv * rv);
                        }
                    }
                }
            }
            memo[start][end] = res;
            return res;
        }
    }
}
