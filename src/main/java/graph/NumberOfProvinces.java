package graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 547 - Number of Provinces.
 * <p>
 * Given an n x n adjacency matrix {@code isConnected} where {@code isConnected[i][j] = 1} means
 * city i and city j are directly connected, return the total number of provinces
 * (connected components).
 *
 * @see <a href="https://leetcode.com/problems/number-of-provinces/">LeetCode 547</a>
 */
public final class NumberOfProvinces {

    private NumberOfProvinces() {
    }

    /**
     * DFS approach (iterative with explicit stack): for each unvisited city, increment count
     * and explore all reachable cities.
     * <p>
     * Time: O(n²) — each cell in the matrix visited at most once.
     * Space: O(n) — visited array + stack hold at most n cities.
     *
     * @param isConnected adjacency matrix
     * @return number of provinces
     */
    public static int findCircleNumDFS(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) return 0;
        int n = isConnected.length;
        boolean[] visited = new boolean[n];             // O(n) space
        int provinces = 0;

        for (int i = 0; i < n; i++) {                   // O(n) cities to check
            if (!visited[i]) {
                provinces++;                            // found a new province
                Deque<Integer> stack = new ArrayDeque<>();
                stack.push(i);                          // seed the DFS
                while (!stack.isEmpty()) {              // iterative DFS
                    int city = stack.pop();
                    if (visited[city]) continue;
                    visited[city] = true;               // mark visited
                    for (int j = 0; j < n; j++) {       // O(n) neighbors per city
                        if (isConnected[city][j] == 1 && !visited[j]) {
                            stack.push(j);              // push unvisited neighbor
                        }
                    }
                }
            }
        }
        return provinces;
    }

    /**
     * Union-Find approach with path compression and union by rank.
     * <p>
     * Time: O(n² · α(n)) — nearly linear per union/find due to path compression + rank.
     * Space: O(n) — parent and rank arrays.
     *
     * @param isConnected adjacency matrix
     * @return number of provinces
     */
    public static int findCircleNumUnionFind(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) return 0;
        int n = isConnected.length;
        int[] parent = new int[n];                      // O(n) space
        int[] rank = new int[n];                        // O(n) space
        int provinces = n;                              // start with n isolated components

        for (int i = 0; i < n; i++) {
            parent[i] = i;                              // each city is its own root
        }

        for (int i = 0; i < n; i++) {                   // O(n²) pairs to check
            for (int j = i + 1; j < n; j++) {           // upper triangle only
                if (isConnected[i][j] == 1) {
                    int ri = find(parent, i);           // O(α(n)) amortized
                    int rj = find(parent, j);
                    if (ri != rj) {
                        union(parent, rank, ri, rj);    // merge components
                        provinces--;                    // one fewer province
                    }
                }
            }
        }
        return provinces;
    }

    /**
     * Find with path compression — flattens tree on each lookup.
     */
    private static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);        // path compression
        }
        return parent[x];
    }

    /**
     * Union by rank — attaches shorter tree under taller tree's root.
     */
    private static void union(int[] parent, int[] rank, int x, int y) {
        if (rank[x] < rank[y]) {
            parent[x] = y;                              // attach x under y
        } else if (rank[x] > rank[y]) {
            parent[y] = x;                              // attach y under x
        } else {
            parent[y] = x;                              // tie: attach y under x
            rank[x]++;                                  // increase rank of new root
        }
    }
}
