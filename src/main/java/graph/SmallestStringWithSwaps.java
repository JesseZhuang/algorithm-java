package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1202. Medium. Tags: Union Find, DFS, Graph, String, Sorting.
 * <p>
 * Given a string {@code s} and a list of pairs of indices where characters can be swapped,
 * return the lexicographically smallest string achievable by performing any number of swaps.
 */
public final class SmallestStringWithSwaps {

    private SmallestStringWithSwaps() {
    }

    // ---- Solution 1: Union-Find with rank + path compression ----

    /**
     * Group indices into connected components via Union-Find, sort characters within each group,
     * and assign them back in index order.
     * <p>
     * Time O(n·α(n) + n·log(n)), space O(n).
     */
    public static String smallestStringUF(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i; // O(n) init

        for (List<Integer> pair : pairs) { // O(E·α(n)) unions
            union(parent, rank, pair.get(0), pair.get(1));
        }

        // group indices by root — O(n·α(n))
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(find(parent, i), k -> new ArrayList<>()).add(i);
        }

        char[] res = new char[n];
        for (List<Integer> indices : groups.values()) {
            // collect chars for this component
            List<Character> chars = new ArrayList<>(indices.size());
            for (int idx : indices) chars.add(s.charAt(idx));
            Collections.sort(chars); // O(k·log(k)) where k = component size
            // assign sorted chars back to sorted indices (indices already in order)
            for (int i = 0; i < indices.size(); i++) {
                res[indices.get(i)] = chars.get(i);
            }
        }
        return new String(res);
    }

    private static int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // path compression (halving)
            x = parent[x];
        }
        return x;
    }

    private static void union(int[] parent, int[] rank, int a, int b) {
        int ra = find(parent, a);
        int rb = find(parent, b);
        if (ra == rb) return;
        if (rank[ra] < rank[rb]) {
            parent[ra] = rb;
        } else if (rank[ra] > rank[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank[ra]++;
        }
    }

    // ---- Solution 2: DFS connected components ----

    /**
     * Build adjacency list, DFS to find connected components, sort characters within each,
     * and assign back.
     * <p>
     * Time O(n·log(n) + E), space O(n + E).
     */
    public static String smallestStringDFS(String s, List<List<Integer>> pairs) {
        int n = s.length();

        // build adjacency list — O(E)
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> pair : pairs) {
            int u = pair.get(0), v = pair.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        char[] res = new char[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            // DFS to collect component indices — O(V_k + E_k)
            List<Integer> component = new ArrayList<>();
            dfs(adj, visited, i, component);
            Collections.sort(component); // indices in ascending order
            // collect and sort chars — O(k·log(k))
            List<Character> chars = new ArrayList<>(component.size());
            for (int idx : component) chars.add(s.charAt(idx));
            Collections.sort(chars);
            for (int j = 0; j < component.size(); j++) {
                res[component.get(j)] = chars.get(j);
            }
        }
        return new String(res);
    }

    private static void dfs(List<List<Integer>> adj, boolean[] visited, int node, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int nei : adj.get(node)) {
            if (!visited[nei]) {
                dfs(adj, visited, nei, component);
            }
        }
    }
}
