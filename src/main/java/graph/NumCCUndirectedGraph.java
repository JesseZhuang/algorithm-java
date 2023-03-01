package graph;

import struct.graph.Node;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 323, medium, tags: dfs, union find, bfs.
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
 * <p>
 * LintCode 431, medium.
 * Example 1:
 * <p>
 * Input: {1,2,4#2,1,4#3,5#4,1,2#5,3}
 * Output: [[1,2,4],[3,5]]
 * Explanation:
 * <pre>
 * 1------2  3
 * \     |  |
 * \    |  |
 * \   |  |
 * \  |  |
 *  4   5
 * </pre>
 * Example 2:
 * <p>
 * Input: {1,2#2,1}
 * Output: [[1,2]]
 * Explanation:
 * <p>
 * 1--2
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

    int[] parent;
    byte[] height;
    int ufcc; // connected components in union find

    public int ccUF(int n, int[][] edges) {
        parent = new int[n];
        height = new byte[n];
        ufcc = n;
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] edge : edges)
            union(edge[0], edge[1]);
        return ufcc;
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        ufcc--;
        if (height[rootP] < height[rootQ]) parent[rootP] = rootQ;
        else if (height[rootQ] < height[rootP]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            height[rootP]++;
        }
    }

    private int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]]; // reduce height by half
            p = parent[p];
        }
        return p;
    }

    public List<List<Integer>> connectedSet(List<Node> nodes) {
        return null;
    }
}
