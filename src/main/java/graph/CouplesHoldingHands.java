package graph;

import princeton.jsl.WeightedQUSizePCUF;

/**
 * LeetCode 765. Hard. Tags: Greedy, Union Find, Graph.
 * <p>
 * N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps
 * so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up
 * and switch seats.
 * <p>
 * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order,
 * the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
 * <p>
 * The couples' initial seating is given by row[i] being the value of the person who is initially sitting
 * in the i-th seat.
 * <pre>
 * Example 1:
 *
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 * </pre>
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 * <pre>
 * Example 2:
 *
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 * </pre>
 * Explanation: All couples are already seated side by side.
 * Note:
 * <p>
 * len(row) is even and in the range of [4, 60].
 * row is guaranteed to be a permutation of 0...len(row)-1.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>union find, O(n lg*n) time, O(n) space, 0ms 100%, 34.5 MB, 100%.
 */
public class CouplesHoldingHands {
    public int minSwapsCouplesUF(int[] row) {
        int N = row.length / 2;
        WeightedQUSizePCUF uf = new WeightedQUSizePCUF(N);
        for (int i = 0; i < N; i++) uf.union(row[2 * i] / 2, row[2 * i + 1] / 2);
        return N - uf.count();
    }
}
