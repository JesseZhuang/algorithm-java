package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22, medium, tags: string, dynamic programming, backtracking.
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 8
 */
public class GenParentheses {
    // f(n) = (f(0))f(n-1) + (f(1))f(n-2) + ... + (f(n-2))f(1) + (f(n-1))f(0), prove by induction
    // O(4^n/sqrt(n)) time and space. 9ms, 42.6Mb.
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) res.add("");
        else {
            for (int i = 0; i < n; i++)
                for (String left : generateParenthesis(i))
                    for (String right : generateParenthesis(n - 1 - i))
                        res.add("(" + left + ")" + right);
        }
        return res;
    }

    // 0ms, 41.8Mb. backtracking. n-th Catalan number 1/(n+1)*(2n choose n) O(4^n/sqrt(n)) time and space.
    public List<String> generateParenthesisBT(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> res, StringBuilder cur, int left, int right, int n) {
        if (cur.length() == 2 * n) {
            res.add(cur.toString());
            return;
        }
        if (left < n) {
            cur.append('(');
            backtrack(res, cur, left + 1, right, n);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (right < left) {
            cur.append(')');
            backtrack(res, cur, left, right + 1, n);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
