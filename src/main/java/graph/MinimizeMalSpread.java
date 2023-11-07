package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 924, hard, tags: graph, dfs, bfs, union-find, hash table.
 * <p>
 * You are given a network of n nodes represented as an n x n adjacency matrix graph, where the ith node is directly
 * connected to the jth node if graph[i][j] == 1.
 * <p>
 * Some nodes initial are initially infected by malware. Whenever two nodes are directly connected, and at least
 * one of those two nodes is infected by malware, both nodes will be infected by malware. This spread of malware
 * will continue until no more nodes can be infected in this manner.
 * <p>
 * Suppose M(initial) is the final number of nodes infected with malware in the entire network after the spread
 * of malware stops. We will remove exactly one node from initial.
 * <p>
 * Return the node that, if removed, would minimize M(initial). If multiple nodes could be removed to minimize
 * M(initial), return such a node with the smallest index.
 * <p>
 * Note that if a node was removed from the initial list of infected nodes, it might still be infected
 * later due to the malware spread.
 * <p>
 * Example 1:
 * <p>
 * Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
 * Output: 0
 * Example 2:
 * <p>
 * Input: graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]
 * Output: 0
 * Example 3:
 * <p>
 * Input: graph = [[1,1,1],[1,1,1],[1,1,1]], initial = [1,2]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * n == graph.length
 * n == graph[i].length
 * 2 <= n <= 300
 * graph[i][j] is 0 or 1.
 * graph[i][j] == graph[j][i]
 * graph[i][i] == 1
 * 1 <= initial.length <= n
 * 0 <= initial[i] <= n - 1
 * All the integers in initial are unique.
 */
public class MinimizeMalSpread {
    int[] id;
    int[] size; // component size
    boolean[] marked;
    int count; // num of components
    Graph g;

    void init(int[][] graph, int[] initial) {
        int V = graph.length;
        id = new int[V];
        size = new int[V];
        marked = new boolean[V];
        g = new Graph(graph);
    }

    // connected components V+E time and space, 10ms, 58.6Mb. O(n^2) time and O(n) space.
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int V = graph.length;
        init(graph, initial);
        for (int v : initial) {
            if (!marked[v]) dfs(v);
            count++;
        }
        int[] sourceCount = new int[V];
        for (int v : initial) sourceCount[id[v]]++;
        int res = Integer.MAX_VALUE;
        for (int v : initial) {
            if (sourceCount[id[v]] == 1) { // this component contains only 1 source vertex
                if (res == Integer.MAX_VALUE // first seen
                        || size[id[v]] > size[id[res]] // v in a larger size component
                        || size[id[v]] == size[id[res]] && v < res) // same size component, pick smaller v
                    res = v;
            }
        }
        if (res == Integer.MAX_VALUE) {// no components source count == 1, just pick the min v
            for (int v : initial) if (v < res) res = v;
        }
        return res;
    }

    private void dfs(int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj.get(v)) {
            if (!marked[w]) dfs(w);
        }
    }
}

class Graph {
    int V;
    int E;
    List<List<Integer>> adj;

    // adj matrix -> adj list O(n^2) time and space (graph object, worst case densely connected)
    Graph(int[][] matrix) {
        this.V = matrix.length;
        adj = new ArrayList<>(V);
        for (int v = 0; v < V; v++) adj.add(new ArrayList<>());
        for (int v = 0; v < matrix.length; v++)
            for (int w = v + 1; w < matrix.length; w++)
                if (matrix[v][w] == 1) addEdge(v, w);
    }

    void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
        E++;
    }
}
