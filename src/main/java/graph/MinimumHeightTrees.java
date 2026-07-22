package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 310. Medium. Tags: Graph, Topological Sort, BFS.
 * <p>
 * Given a tree of n nodes labeled 0 to n-1 and an array of n-1 undirected edges,
 * find all roots that minimize the tree height. A tree has at most 2 such roots (the center(s)).
 */
public final class MinimumHeightTrees {

    private MinimumHeightTrees() {
    }

    /**
     * Topological sort — peel leaves layer by layer until at most 2 nodes remain.
     * <p>
     * Time O(n), space O(n).
     */
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return List.of(0); // O(1) base case
        if (n == 2) return List.of(0, 1);

        // Build adjacency set for each node — O(n) space
        List<List<Integer>> adj = new ArrayList<>(n);
        int[] degree = new int[n]; // O(n) space
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) { // O(n) time — n-1 edges
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }

        // Initialize queue with all leaves (degree == 1) — O(n) time
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) leaves.offer(i);
        }

        int remaining = n;
        // Peel leaves layer by layer — each node removed exactly once → O(n) total
        while (remaining > 2) {
            int size = leaves.size();
            remaining -= size;
            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();
                for (int neighbor : adj.get(leaf)) {
                    if (--degree[neighbor] == 1) { // neighbor becomes a leaf
                        leaves.offer(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>(leaves); // 1 or 2 center nodes
    }
}
