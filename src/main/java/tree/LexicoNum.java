package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 386, medium, tags: dfs, trie.
 * <p>
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * <p>
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 13
 * Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 5 * 10^4
 */
@SuppressWarnings("unused")
public class LexicoNum {
    // solution 1, iterative, n, 1. 5ms, 47.73mb.
    public List<Integer> lexicalOrder(int n) { // 103
        List<Integer> res = new ArrayList<>();
        int cur = 1;
        for (int i = 0; i < n; i++) { // n numbers in total
            res.add(cur);
            if (cur * 10 <= n) cur *= 10; // 1->10->100
            else { // 103->10,then 11,12,...,19->1,then 2,20,21,...29,3,30,...9,90,...99
                while (cur % 10 == 9 || cur >= n) cur /= 10; // Remove the last digit
                cur += 1; // Increment the number
            }
        }
        return res;
    }

    // solution 2, recursive, n, lg_5n.
    static class Solution {
        int n;
        List<Integer> res;

        public List<Integer> lexicalOrderR(int n) {
            res = new ArrayList<>();
            this.n = n;
            // Start generating numbers from 1 to 9
            for (int start = 1; start <= 9; start++) generate(start);
            return res;
        }

        private void generate(int start) {
            if (start > n) return;
            res.add(start);
            // append next digit
            for (int nDigit = 0; nDigit <= 9; nDigit++) {
                int next = start * 10 + nDigit;
                // If the next number is within the n, continue recursion
                if (next <= n) generate(next);
                else break; // No need to continue if next exceeds n
            }
        }
    }
}
