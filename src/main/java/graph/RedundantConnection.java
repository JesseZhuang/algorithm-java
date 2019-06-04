package graph;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import princeton.jsl.Cycle;
import princeton.jsl.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 684. Medium. Tags: Tree, Union Find, Graph.
 * <p>
 * In this problem, a tree is an undirected graph that is connected and has no cycles. The given input is a graph
 * that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 * <p>
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 * that represents an undirected edge connecting nodes u and v.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers,
 * return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 * <pre>
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * </pre>
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 * <p>
 * Update (2017-09-26):
 * We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph.
 * For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>graph, O(V+E) linear time, O(V+E) linear space. 3ms 40.09%, 36 MB 99.59%.
 * <li>union find, O(V + E*Ackermann(V)) time, O(V) space. 1ms 90.03%, 40.7 MB 64.61%.
 * </ul>
 */
public class RedundantConnection {
    public int[] redundantGraph(Integer[][] edges) {
        int[] result = null;
        Cycle cycle = new Cycle(new Graph(edges));
        Set<Integer> cycleSet = new HashSet<>();
        for (int v : cycle.cycle()) cycleSet.add(v);
        for (int i = edges.length - 1; i >= 0; i--) {
            if (cycleSet.contains(edges[i][1]) && cycleSet.contains(edges[i][0])) {
                result = new int[]{edges[i][0], edges[i][1]};
                break;
            }
        }
        return result;
    }

    public int[] redundantUF(Integer[][] edges) {
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(edges.length + 1);
        for (Integer[] e : edges) {
            if (uf.connected(e[0], e[1])) return new int[]{e[0], e[1]};
            else uf.union(e[0], e[1]);
        }
        return null;
    }

    @SuppressWarnings("unused")
    public int[] redundantArray(Integer[][] edges) {
        boolean[] marked = new boolean[edges.length + 2];
        int[] result = null;
        for (Integer[] e : edges) {
            if (marked[e[0]] && marked[e[1]]) result = new int[]{e[0], e[1]};
            else {
                marked[e[0]] = true;
                marked[e[1]] = true;
            }
        }
        return result;
    }
}
