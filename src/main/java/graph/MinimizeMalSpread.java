package graph;

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
    // connected components V+E time and V space, find the min id vertex in the largest component
    public int minMalwareSpread(int[][] graph, int[] initial) {
        return 0;
    }
}

class Graph {
    int V;
    int E;
    List<List<Integer>> adj;

    // adj matrix -> adj list O(n^2) time and space (graph object, worst case densely connected)
    Graph(int V, int[][] matrix) {
        this.V = V;
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
