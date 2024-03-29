package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 261, LintCode 178, medium, tags: union find, bfs.
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 * <p>
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1]
 * is the same as [1, 0] and thus will not appear together in edges.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 * Output: true.
 * Example 2:
 * <p>
 * Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 * Output: false.
 */
public class GraphValidTree {

    boolean hasCycle;
    boolean[] visited;
    List<List<Integer>> adj;

    // solution 1, dfs, O(N) time, O(N) space. 479ms, 25.26Mb.
    // non-valid worst case N^2 edges, would find cycle; valid V+E == N + N-1
    // 1, all connected, 2, no cycle, 3, edges = n-1; check 2,3
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        visited = new boolean[n];
        adj = buildGraph(n, edges);
        for (int i = 0; i < n; i++) if (!visited[i] && !hasCycle) dfs(-1, i);
        return !hasCycle;
    }

    private void dfs(int u, int v) { // note different from directed graph, not using onStack boolean array
        visited[v] = true;
        for (int w : adj.get(v)) {
            if (hasCycle) return;
            if (!visited[w]) dfs(v, w);
            else if (w != u) hasCycle = true;
        }
    }

    public static List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }

    // solution 2, BFS, check conditions 1,3. 493ms, 27.76MB.
    public boolean validTreeBFS(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();
        bfs(0, visited);
        return visited.size() == n;
    }

    private void bfs(int s, Set<Integer> visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        visited.add(s);
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : adj.get(v)) {
                if (visited.contains(w)) continue;
                q.add(w);
                visited.add(w);
            }
        }
    }
}
