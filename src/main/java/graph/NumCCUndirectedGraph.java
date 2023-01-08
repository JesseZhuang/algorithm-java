package graph;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

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
    List<List<Integer>> adj;
    boolean[] visited;
    Queue<Integer> queue;

    public int ccDFS(int n, int[][] edges) {
        int count = 0;
        visited = new boolean[n];
        adj = GraphValidTree.buildGraph(n, edges);
        for (int v = 0; v < n; v++)
            if (!visited[v]) {
                dfs(v);
                count++;
            }
        return count;
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int w : adj.get(v)) if (!visited[w]) dfs(w);
    }

    public int ccBFS(int n, int[][] edges) {
        int count = 0;
        visited = new boolean[n];
        adj = GraphValidTree.buildGraph(n, edges);
        queue = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                bfs(v);
                count++; // must be inside of if
            }
        }
        return count;
    }

    private void bfs(int v) {
        queue.add(v);
        visited[v] = true;
        while (!queue.isEmpty()) {
            int x = queue.remove();
            for (int w : adj.get(x))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true; // do not forget
                }
        }
    }
}
