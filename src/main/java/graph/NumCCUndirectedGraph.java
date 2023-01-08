package graph;

/**
 * LeetCode 323, medium, tags: dfs, union find. Lint code 431.
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * <pre>
 * 0          3
 * |          |
 * 1 --- 2    4
 * </pre>
 * Output: 2
 * Example 2:
 * <p>
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * <pre>
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * </pre>
 * Output:  1
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same
 * as [1, 0] and thus will not appear together in edges.
 */
public class NumCCUndirectedGraph {
}
