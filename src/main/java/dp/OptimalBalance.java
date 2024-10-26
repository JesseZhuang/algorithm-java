package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 465, hard, LintCode 707, tags: bit manipulation, array, dynamic programming, backtracking, bit mask.
 * Companies: pinterest.
 * <p>
 * You are given an array of transactions where transactions[i] = [from_i, toi, amount_i] indicates that
 * the person with ID = from_i gave amount_i $ to the person with ID = toi.
 * <p>
 * Return the minimum number of transactions required to settle the debt.
 * <p>
 * Example 1:
 * <p>
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 * <p>
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= transactions.length <= 8, n
 * transactions[i].length == 3
 * 0 <= from_i, to_i < 12
 * from_i != to_i
 * 1 <= amount_i <= 100
 * <p>
 * LintCode
 * <p>
 * Given a directed graph where each edge is represented by a tuple, such as [u, v, w] represents an edge with a
 * weight w from u to v.
 * You need to calculate at least the need to add the number of edges to ensure that each point of the weight are
 * balancing. That is, the sum of weight of the edge pointing to this point is equal to the sum of weight of the
 * edge of the point that points to the other point.
 * <p>
 * 1 Note that u ≠ v and w > 0
 * 2 Index may not be linear, e.g. we could have the points 0, 1, 2 or we could also have the points 0, 2, 6
 * 3 A directed graph is a pair of directional graphs that is composed of a set of vertices and a set of directed
 * edges, each of which is connected to an ordered pair of vertices. A graph composed entirely of undirected edges
 * is called an undirected graph
 * <p>
 * Example 1
 * <p>
 * Input: [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Two edges are need to added. There are [1,0,5] and [1,2,5]
 * Example 2
 * <p>
 * Input: [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Only one edge need to added. There is [1,0,4]
 */
@SuppressWarnings("unused")
public class OptimalBalance {

    // f[i] dp, min transactions to settle debt to 0 for subset i, i in [1, 1<<m)
    // subset i 1 (one person: 0), 11 (two people, 0 and 1)
    // f[i] = {0, when i==0
    //         inf, when i!=0, s!=0
    //         min(|i|-1, min(f[j]+f[i-j])) where i!=0,s==0 i,j non empty, j is subset of i }
    static class Solution1 {
        // O(n) + O(m * 2^m), m worst case n, so O(m*2^m). O(2^m) space.
        public int minTransfers(int[][] transactions) { // [[0,1,10],[2,0,5]]
            int[] g = new int[12];
            for (int[] t : transactions) { // O(n)
                g[t[0]] -= t[2];
                g[t[1]] += t[2];
            } // g: [-5,10,-5,0,0...] 0:+5-10==-5; 1:10==10;2:-5==-5
            List<Integer> nums = new ArrayList<>();
            for (int x : g)
                if (x != 0) nums.add(x); // {-5,10,-5}
            int m = nums.size();
            int[] f = new int[1 << m]; // index: subset of people, 2^m
            // subsets: 0:no one, 1: only person 0, 10(2): only person 1, 11(3): person 1 and 2, .etc
            Arrays.fill(f, 1 << 29);
            f[0] = 0;
            for (int i = 1; i < 1 << m; i++) { // starting from 1: non empty
                int sum = 0;
                for (int j = 0; j < m; ++j)
                    if ((i >> j & 1) == 1) sum += nums.get(j); // is jth person in subset i?
                if (sum == 0) { // when i==7, 111 -5+10+-5==0, iterate j in [6,1] f[6]+f[1],f[5]+f[2],...
                    f[i] = Integer.bitCount(i) - 1; // init: number of people - 1
                    for (int j = (i - 1) & i; j > 0; j = (j - 1) & i)
                        f[i] = Math.min(f[i], f[j] + f[i ^ j]); // split subset into two parts and minimize
                }
            }
            return f[(1 << m) - 1]; // transactions for set including all non-zero balances
        }
    }
}
