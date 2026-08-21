package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 323 - Number of Connected Components in an Undirected Graph.
 *
 * Given n nodes labeled 0..n-1 and a list of undirected edges,
 * return the number of connected components.
 */
public final class NumberOfConnectedComponents {

    private NumberOfConnectedComponents() {
    }

    /**
     * Union-Find approach.
     * Time: O(n + e * alpha(n)), Space: O(n)
     */
    public static int countComponentsUF(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) { // O(n) init
            parent[i] = i;
        }

        int components = n;
        for (int[] edge : edges) { // O(e) edges
            int rootA = find(parent, edge[0]); // O(alpha(n)) path compression
            int rootB = find(parent, edge[1]); // O(alpha(n)) path compression
            if (rootA != rootB) {
                // Union by rank
                if (rank[rootA] < rank[rootB]) {
                    parent[rootA] = rootB;
                } else if (rank[rootA] > rank[rootB]) {
                    parent[rootB] = rootA;
                } else {
                    parent[rootB] = rootA;
                    rank[rootA]++;
                }
                components--;
            }
        }
        return components;
    }

    private static int find(int[] parent, int x) {
        while (parent[x] != x) { // O(alpha(n)) path compression
            parent[x] = parent[parent[x]]; // path halving
            x = parent[x];
        }
        return x;
    }

    /**
     * DFS approach.
     * Time: O(n + e), Space: O(n + e)
     */
    public static int countComponentsDFS(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) { // O(n) init
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) { // O(e) build adjacency list
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;
        for (int i = 0; i < n; i++) { // O(n) iterate nodes
            if (!visited[i]) {
                components++;
                dfs(adj, visited, i);
            }
        }
        return components;
    }

    private static void dfs(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) { // O(degree) per node, O(e) total
            if (!visited[neighbor]) {
                dfs(adj, visited, neighbor);
            }
        }
    }
}
