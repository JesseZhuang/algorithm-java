package graph;

import princeton.jsl.WeightedQuickUnionPathCompressionUF;

import java.util.Arrays;

/**
 * LeetCode 685, Hard. Tags: Tree, DFS, Union Find, Graph.
 * <p>
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all
 * other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which
 * has no parents.
 * <p>
 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional directed edge added. The added edge has two different vertices chosen from 1 to N,
 * and was not an edge that already existed.
 * <p>
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents
 * a directed edge connecting nodes u and v, where u is a parent of child v.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * <pre>
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *   1
 *  / \
 * v   v
 * 2-->3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 * </pre>
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>union find one pass, O(V + E*Ackermann(V)) time, O(V) space. 1ms 98.61%, 42.3 MB 84.21%.
 * <li>union find two pass, O(V + E*Ackermann(V)) time, O(V) space. 1ms 98.61%, 41.7 MB 86.84%.
 * </ul>
 */
public class RedundantConnectionII {
    public Integer[] redundantUFOnePass(Integer[][] edges) {
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(edges.length + 1);
        int[] edgeToIndex = new int[edges.length + 1]; // array index in edges array for edge leading to vertex i
        Arrays.fill(edgeToIndex, -1); // index can be 0, use -1 as sentinel
        int first = -1, second = -1, last = -1;
        for (int i = 0; i < edges.length; i++) {
            Integer[] e = edges[i];
            if (edgeToIndex[e[1]] == -1) edgeToIndex[e[1]] = i;
            else {
                first = edgeToIndex[e[1]];
                second = i;
                continue;
            }
            if (uf.connected(e[0], e[1])) last = i;
            else uf.union(e[0], e[1]);
        }
        if (last == -1) return edges[second]; // double parent
        if (second == -1) return edges[last]; // cycle
        return edges[first]; // cycle and double parent
    }

    public int[] redundantUFTwoPass(Integer[][] edges) {
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(edges.length + 1);
        int[] edgeToIndex = new int[edges.length + 1]; // array index in edges array for edge leading to vertex i
        int[] first = new int[] {-1, -1};
        int[] second = new int[] {-1, -1};
        for (Integer[] e: edges) {
            if (edgeToIndex[e[1]] != 0) {
                second = new int[] {e[0], e[1]};
                first = new int[] {edgeToIndex[e[1]], e[1]};
                e[1] = 0;
            } else edgeToIndex[e[1]] = e[0];
        }
        for (Integer[] e: edges) {
            if (e[1] == 0) continue;
            if (uf.connected(e[0], e[1])) {
              if (first[1] != -1) return first;
              else return new int[] {e[0], e[1]};
            } else {
                uf.union(e[0], e[1]);
            }
        }
        return second;
    }
}
