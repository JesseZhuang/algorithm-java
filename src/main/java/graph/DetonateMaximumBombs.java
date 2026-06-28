package graph;

import java.util.*;

/**
 * LeetCode 2101 Medium. Tags: Array, Math, DFS, BFS, Graph.
 * <p>
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * Each bomb's range is represented as a circle with center (xi, yi) and radius ri.
 * <p>
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all the bombs that lie in
 * its range. These bombs will further detonate other bombs that lie in their ranges.
 * <p>
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate
 * only one bomb.
 * <p>
 * Example 1: Input: bombs = [[2,1,3],[6,1,4]], Output: 2
 * Example 2: Input: bombs = [[1,1,5],[10,10,5]], Output: 1
 * Example 3: Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]], Output: 5
 * <p>
 * Constraints:
 * 1 <= bombs.length <= 100
 * bombs[i].length == 3
 * 1 <= xi, yi, ri <= 10^5
 */
@SuppressWarnings("unused")
public final class DetonateMaximumBombs {

    private DetonateMaximumBombs() {
    }

    /**
     * BFS approach. Build directed graph, then BFS from each node to count reachable.
     * Time O(n^3): O(n^2) to build graph + O(n) starts * O(n+n^2) BFS = O(n^3).
     * Space O(n^2) for adjacency list.
     */
    public static int maximumDetonationBFS(int[][] bombs) {
        int n = bombs.length;
        // O(n^2) build directed graph: edge i->j if bomb i can reach bomb j
        List<List<Integer>> graph = buildGraph(bombs);
        int max = 0;
        // O(n) starts, each BFS is O(n + edges) = O(n^2) worst case
        for (int i = 0; i < n; i++) {
            max = Math.max(max, bfs(graph, i, n));
        }
        return max;
    }

    /**
     * DFS approach. Build directed graph, then DFS from each node to count reachable.
     * Time O(n^3): O(n^2) to build graph + O(n) starts * O(n+n^2) DFS = O(n^3).
     * Space O(n^2) for adjacency list.
     */
    public static int maximumDetonationDFS(int[][] bombs) {
        int n = bombs.length;
        // O(n^2) build directed graph
        List<List<Integer>> graph = buildGraph(bombs);
        int max = 0;
        // O(n) starts, each DFS is O(n + edges) = O(n^2) worst case
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            max = Math.max(max, dfs(graph, i, visited));
        }
        return max;
    }

    /**
     * Build directed graph. Edge i->j exists if Euclidean distance from i to j <= ri.
     * Uses long arithmetic to avoid overflow when comparing squared distances.
     * O(n^2) time and space.
     */
    private static List<List<Integer>> buildGraph(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        // O(n^2) pairwise distance check
        for (int i = 0; i < n; i++) {
            long ri = bombs[i][2];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long dx = bombs[i][0] - bombs[j][0];
                long dy = bombs[i][1] - bombs[j][1];
                // compare squared distances to avoid floating point
                if (dx * dx + dy * dy <= ri * ri) {
                    graph.get(i).add(j);
                }
            }
        }
        return graph;
    }

    private static int bfs(List<List<Integer>> graph, int start, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            for (int neighbor : graph.get(cur)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return count;
    }

    private static int dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        int count = 1;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                count += dfs(graph, neighbor, visited);
            }
        }
        return count;
    }
}
