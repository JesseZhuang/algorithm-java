package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1514, medium, tags: graph, heap, shortest path, bellman-ford.
 * <p>
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where
 * edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success
 * of traversing that edge succProb[i].
 * <p>
 * Given two nodes start and end, find the path with the maximum probability of success to go from start
 * to end and return its success probability.
 * <p>
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the
 * correct answer by at most 1e-5.
 * <p>
 * Constraints:
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 */
public final class PathWithMaxProbability {
    private PathWithMaxProbability() {}

    /**
     * Modified Dijkstra with max-heap. Maximizes probability instead of minimizing distance.
     * Time O((V+E) log V), Space O(V+E).
     */
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<double[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>()); // O(V) init
        for (int i = 0; i < edges.length; i++) { // O(E) build adjacency list
            int a = edges[i][0], b = edges[i][1];
            double p = succProb[i];
            graph.get(a).add(new double[]{b, p});
            graph.get(b).add(new double[]{a, p});
        }

        double[] prob = new double[n]; // max probability from start to each node
        prob[start] = 1.0;
        // max-heap: [probability, node]
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        pq.offer(new double[]{1.0, start});

        while (!pq.isEmpty()) { // O((V+E) log V) total
            double[] cur = pq.poll();
            double curProb = cur[0];
            int node = (int) cur[1];
            if (node == end) return curProb;
            if (curProb < prob[node]) continue; // already found better path
            for (double[] next : graph.get(node)) { // O(degree) neighbors
                int neighbor = (int) next[0];
                double edgeProb = next[1];
                double newProb = curProb * edgeProb;
                if (newProb > prob[neighbor]) {
                    prob[neighbor] = newProb;
                    pq.offer(new double[]{newProb, neighbor});
                }
            }
        }
        return 0.0;
    }

    /**
     * Bellman-Ford relaxation. Relaxes all edges up to V-1 times.
     * Time O(V*E), Space O(V).
     */
    public static double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] prob = new double[n]; // max probability from start to each node
        prob[start] = 1.0;

        for (int i = 0; i < n - 1; i++) { // O(V-1) rounds
            boolean updated = false;
            for (int j = 0; j < edges.length; j++) { // O(E) edges per round
                int a = edges[j][0], b = edges[j][1];
                double p = succProb[j];
                if (prob[a] * p > prob[b]) {
                    prob[b] = prob[a] * p;
                    updated = true;
                }
                if (prob[b] * p > prob[a]) {
                    prob[a] = prob[b] * p;
                    updated = true;
                }
            }
            if (!updated) break; // early termination
        }
        return prob[end];
    }
}
