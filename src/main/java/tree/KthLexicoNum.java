package tree;

/**
 * LeetCode 440, hard, tags: trie.
 * <p>
 * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
 * <p>
 * Example 1:
 * <p>
 * Input: n = 13, k = 2
 * Output: 10
 * Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the
 * second-smallest number is 10.
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= n <= 10^9
 */
@SuppressWarnings("unused")
public class KthLexicoNum {
    static class Solution1 {
        public int findKthNumber(int n, int k) {
            int cur = 1;
            k--;
            while (k > 0) {
                int step = countSteps(n, cur, cur + 1);
                if (step <= k) { // can skip to next node
                    cur++;
                    k -= step;
                } else { // look in this tree node
                    cur *= 10;
                    k--;
                }
            }
            return cur;
        }

        /**
         * p1:prefix1:cur, p2:prefix2:cur+1. p2 is always the next right node beside p1's right most node, .e.g,
         * 2 next to 1, 20 next to 19, 200 next to 199, .etc.
         * if p2<=n, add number of nodes from p1 to p2 to steps
         * else if p2>n, add n+1-p1 to steps. n+1 because need to include n.
         * so min(n+1,p2)-p1
         *
         * @param n  limit numbers are in [1,n]
         * @param p1 node 1
         * @param p2 node 2
         * @return steps between the two tree nodes
         */
        private int countSteps(int n, long p1, long p2) { // 13,1,2
            int steps = 0;
            while (p1 <= n) {
                steps += (int) (Math.min(n + 1, p2) - p1); //steps += 1,4
                p1 *= 10;
                p2 *= 10;
            }
            return steps;
        }
    }
}
