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

    // 0ms, 39.86mb. prefix tree or trie, lgn^2, lgn.
    static class Solution1 {
        int n;

        public int findKthNumber(int n, int k) {
            this.n = n;
            int cur = 1;
            k--;
            while (k > 0) {
                int steps = countSteps(cur);
                if (steps <= k) { // can skip to next node
                    cur++;
                    k -= steps;
                } else { // look in this tree node
                    cur *= 10;
                    k--;
                }
            }
            return cur;
        }

        /**
         * cur:prefix1, prefix2:cur+1. p2 is always the next right node beside cur's right most node, .e.g,
         * 2 next to 1, 20 next to 19, 200 next to 199, .etc.
         * if p2<=n, add number of nodes from cur to p2 to steps
         * else if p2>n, add n+1-cur to steps. n+1 because need to include n.
         * so min(n+1,p2)-cur
         *
         * @param cur node 1
         * @return steps between the two tree nodes [cur, cur+1)
         */
        private int countSteps(long cur) { // n=13, cur=1
            int res = 0;
            long next = cur + 1;
            while (cur <= n) {
                res += (int) (Math.min(n + 1, next) - cur); // res += 1,4
                cur *= 10;
                next *= 10;
            }
            return res;
        }
    }
}
