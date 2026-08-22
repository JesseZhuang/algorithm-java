package graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 785, medium, tags: graph, bfs, union find, dfs.
 * <p>
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph where graph[u] is an array of nodes that node u is adjacent to.
 * <p>
 * The graph is bipartite if and only if it is 2-colorable: we can partition all nodes into two
 * independent sets such that every edge connects a node in one set to a node in the other set.
 * <p>
 * Return true if and only if the graph is bipartite.
 * <p>
 * Constraints:
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] does not contain u.
 * All values in graph[u] are unique.
 * If graph[u] contains v, then graph[v] contains u.
 */
@SuppressWarnings("unused")
public final class IsGraphBipartite {
    private IsGraphBipartite() {}

    /**
     * BFS coloring approach. O(V+E) time, O(V) space.
     * Color each node with 0 or 1; if a neighbor has the same color, not bipartite.
     */
    public static boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // O(V) space
        java.util.Arrays.fill(color, -1); // -1 means uncolored
        for (int i = 0; i < n; i++) { // handle disconnected components
            if (color[i] != -1) continue;
            Queue<Integer> queue = new ArrayDeque<>(); // O(V+E) BFS
            queue.add(i);
            color[i] = 0;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : graph[u]) { // O(E) total across all nodes
                    if (color[v] == -1) {
                        color[v] = 1 - color[u]; // assign opposite color
                        queue.add(v);
                    } else if (color[v] == color[u]) {
                        return false; // same color conflict
                    }
                }
            }
        }
        return true;
    }

    /**
     * Union-Find approach. O(V*alpha(V)+E) time, O(V) space.
     * For each node u, union all neighbors of u together. If u and any neighbor share a set,
     * the graph is not bipartite.
     */
    public static boolean isBipartiteUF(int[][] graph) {
        int n = graph.length;
        int[] parent = new int[n]; // O(V) space
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int u = 0; u < n; u++) {
            for (int v : graph[u]) {
                if (find(parent, u) == find(parent, v)) return false; // u and neighbor in same set
                union(parent, rank, graph[u][0], v); // O(alpha(V)) amortized union of all neighbors
            }
        }
        return true;
    }

    private static int find(int[] parent, int x) { // O(alpha(V)) amortized with path compression
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private static void union(int[] parent, int[] rank, int x, int y) { // union by rank
        int px = find(parent, x), py = find(parent, y);
        if (px == py) return;
        if (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else {
            parent[py] = px;
            rank[px]++;
        }
    }
}
