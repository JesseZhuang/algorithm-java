package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode 399. Medium. Tags: Graph, BFS, Union Find.
 * <p>
 * Given equations like a/b=2.0, b/c=3.0, answer queries like a/c=?.
 * Return -1.0 if no path exists between the two variables.
 */
public final class EvaluateDivision {

    private EvaluateDivision() {
    }

    /**
     * BFS on weighted directed graph.
     * <p>
     * Build adjacency list where edge a->b has weight values[i] and b->a has weight 1/values[i].
     * For each query, BFS from src to dst multiplying weights along the path.
     * <p>
     * Time O(Q * (V + E)), Space O(V + E).
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values,
                                        List<List<String>> queries) {
        // Build adjacency list: node -> list of (neighbor, weight)
        Map<String, List<double[]>> graph = new HashMap<>(); // value stored as index into neighbors list
        Map<String, List<String>> adj = new HashMap<>();

        // O(E) — build graph from equations
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            adj.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new double[]{i, val});

            adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new double[]{i, 1.0 / val});
        }

        double[] result = new double[queries.size()];

        // O(Q * (V + E)) — BFS for each query
        for (int q = 0; q < queries.size(); q++) {
            String src = queries.get(q).get(0);
            String dst = queries.get(q).get(1);

            if (!adj.containsKey(src) || !adj.containsKey(dst)) {
                result[q] = -1.0;
            } else if (src.equals(dst)) {
                result[q] = 1.0;
            } else {
                result[q] = bfs(src, dst, adj, graph);
            }
        }
        return result;
    }

    private static double bfs(String src, String dst, Map<String, List<String>> adj,
                              Map<String, List<double[]>> graph) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Double> dist = new HashMap<>();
        queue.offer(src);
        dist.put(src, 1.0);

        // BFS traversal — O(V + E) per query
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> neighbors = adj.get(curr);
            List<double[]> weights = graph.get(curr);

            for (int i = 0; i < neighbors.size(); i++) {
                String next = neighbors.get(i);
                if (dist.containsKey(next)) continue;

                double w = dist.get(curr) * weights.get(i)[1];
                if (next.equals(dst)) return w;

                dist.put(next, w);
                queue.offer(next);
            }
        }
        return -1.0;
    }

    /**
     * Union-Find with weighted edges.
     * <p>
     * weight[x] = x / root(x). Path compression updates weights so that after find,
     * weight[x] directly reflects x / root(x).
     * <p>
     * Query: a/b = weight[a] / weight[b] if same root, else -1.0.
     * <p>
     * Union: when union(a, b, val = a/b), find roots ra=find(a), rb=find(b).
     * w = val * weight[b] / weight[a] gives ra/rb. Attach lower rank root to higher rank root.
     * <p>
     * Time O((E + Q) * alpha(n)), Space O(V).
     */
    public static double[] calcEquation2(List<List<String>> equations, double[] values,
                                         List<List<String>> queries) {
        Map<String, String> parent = new HashMap<>();
        Map<String, Double> weight = new HashMap<>(); // weight[x] = x / parent[x]
        Map<String, Integer> rank = new HashMap<>();

        // O(E * alpha(n)) — process all equations
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            if (!parent.containsKey(a)) {
                parent.put(a, a);
                weight.put(a, 1.0);
                rank.put(a, 0);
            }
            if (!parent.containsKey(b)) {
                parent.put(b, b);
                weight.put(b, 1.0);
                rank.put(b, 0);
            }

            union(a, b, values[i], parent, weight, rank);
        }

        double[] result = new double[queries.size()];

        // O(Q * alpha(n)) — answer each query
        for (int q = 0; q < queries.size(); q++) {
            String a = queries.get(q).get(0);
            String b = queries.get(q).get(1);

            if (!parent.containsKey(a) || !parent.containsKey(b)) {
                result[q] = -1.0;
            } else {
                String ra = find(a, parent, weight);
                String rb = find(b, parent, weight);
                if (!ra.equals(rb)) {
                    result[q] = -1.0;
                } else {
                    // a/root = weight[a], b/root = weight[b] => a/b = weight[a]/weight[b]
                    result[q] = weight.get(a) / weight.get(b);
                }
            }
        }
        return result;
    }

    /**
     * Find with path compression. After find(x), parent[x] = root and weight[x] = x / root.
     * O(alpha(n)) amortized.
     */
    private static String find(String x, Map<String, String> parent, Map<String, Double> weight) {
        if (!parent.get(x).equals(x)) {
            String root = find(parent.get(x), parent, weight);
            // x / root = (x / parent[x]) * (parent[x] / root)
            weight.put(x, weight.get(x) * weight.get(parent.get(x)));
            parent.put(x, root);
        }
        return parent.get(x);
    }

    /**
     * Union by rank. val = a / b.
     * After find: weight[a] = a/ra, weight[b] = b/rb.
     * We need ra/rb = (a/ra)^-1 * val * (b/rb) = val * weight[b] / weight[a].
     */
    private static void union(String a, String b, double val,
                              Map<String, String> parent, Map<String, Double> weight,
                              Map<String, Integer> rank) {
        String ra = find(a, parent, weight);
        String rb = find(b, parent, weight);
        if (ra.equals(rb)) return;

        // w = ra / rb
        double w = val * weight.get(b) / weight.get(a);

        int rankA = rank.get(ra);
        int rankB = rank.get(rb);

        if (rankA < rankB) {
            // Attach ra under rb: parent[ra] = rb, weight[ra] = ra/rb = w
            parent.put(ra, rb);
            weight.put(ra, w);
        } else if (rankA > rankB) {
            // Attach rb under ra: parent[rb] = ra, weight[rb] = rb/ra = 1/w
            parent.put(rb, ra);
            weight.put(rb, 1.0 / w);
        } else {
            parent.put(rb, ra);
            weight.put(rb, 1.0 / w);
            rank.put(ra, rankA + 1);
        }
    }
}
