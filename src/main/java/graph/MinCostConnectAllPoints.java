package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1584 - Min Cost to Connect All Points.
 * Given n points on a 2D plane, return the minimum cost to make all points connected.
 * The cost of connecting two points is the Manhattan distance |xi - xj| + |yi - yj|.
 * This is a Minimum Spanning Tree problem on a complete graph.
 */
public final class MinCostConnectAllPoints {

    private MinCostConnectAllPoints() {}

    /**
     * Prim's algorithm using a min-heap.
     * Time: O(n^2 log n) — up to n^2 edges pushed onto the heap, each operation O(log(n^2)) = O(log n).
     * Space: O(n^2) — priority queue can hold up to n^2 edges in the worst case.
     */
    public static int minCostConnectPointsPrim(int[][] points) {
        int n = points.length;
        if (n <= 1) return 0;

        boolean[] visited = new boolean[n];
        // PQ entries: [cost, nodeIndex]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});
        int totalCost = 0;
        int edgesUsed = 0;

        // O(n^2 log n): each of n nodes is visited once; for each we push up to n-1 edges
        while (edgesUsed < n) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int node = curr[1];

            if (visited[node]) continue;
            visited[node] = true;
            totalCost += cost;
            edgesUsed++;

            // Add all edges from this node to unvisited nodes
            for (int next = 0; next < n; next++) {
                if (!visited[next]) {
                    int dist = Math.abs(points[node][0] - points[next][0])
                             + Math.abs(points[node][1] - points[next][1]);
                    pq.offer(new int[]{dist, next});
                }
            }
        }
        return totalCost;
    }

    /**
     * Kruskal's algorithm using Union-Find with rank and path halving.
     * Time: O(n^2 log n) — sorting n*(n-1)/2 edges dominates.
     * Space: O(n^2) — storing all edges.
     */
    public static int minCostConnectPointsKruskal(int[][] points) {
        int n = points.length;
        if (n <= 1) return 0;

        // Build all edges: O(n^2) edges total
        List<int[]> edges = new ArrayList<>(n * (n - 1) / 2);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0])
                         + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{dist, i, j});
            }
        }

        // Sort edges by cost: O(n^2 log n)
        edges.sort(Comparator.comparingInt(a -> a[0]));

        // Union-Find with rank + path halving
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int totalCost = 0;
        int edgesUsed = 0;

        // Process edges in order until MST has n-1 edges
        for (int[] edge : edges) {
            if (edgesUsed == n - 1) break;
            int cost = edge[0], u = edge[1], v = edge[2];
            int rootU = find(parent, u);
            int rootV = find(parent, v);
            if (rootU != rootV) {
                union(parent, rank, rootU, rootV);
                totalCost += cost;
                edgesUsed++;
            }
        }
        return totalCost;
    }

    /** Find with path halving: O(alpha(n)) amortized. */
    private static int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // path halving
            x = parent[x];
        }
        return x;
    }

    /** Union by rank. */
    private static void union(int[] parent, int[] rank, int x, int y) {
        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }
}
