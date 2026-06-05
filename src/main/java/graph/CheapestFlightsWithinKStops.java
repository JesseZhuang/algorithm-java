package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 787, medium, tags: dynamic programming, DFS, BFS, graph, heap, shortest path.
 * <p>
 * There are n cities connected by some number of flights. You are given an array flights where
 * flights[i] = [from_i, to_i, price_i] indicates that there is a flight from city from_i to city to_i
 * with cost price_i.
 * <p>
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at
 * most k stops. If there is no such route, return -1.
 * <p>
 * Constraints:
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= from_i, to_i < n
 * from_i != to_i
 * 1 <= price_i <= 10^4
 * 0 <= src, dst, k < n
 * src != dst
 */
public final class CheapestFlightsWithinKStops {
    private CheapestFlightsWithinKStops() {}

    /**
     * Bellman-Ford variant: relax all edges up to k+1 times.
     * Time O((k+1) * E), Space O(n).
     */
    public static int findCheapestPriceBF(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        for (int i = 0; i <= k; i++) { // O(k+1) rounds
            int[] tmp = prices.clone();
            for (int[] f : flights) { // O(E) edges per round
                if (prices[f[0]] != Integer.MAX_VALUE && prices[f[0]] + f[2] < tmp[f[1]]) {
                    tmp[f[1]] = prices[f[0]] + f[2];
                }
            }
            prices = tmp;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    /**
     * BFS with pruning: level-order BFS where level = number of stops.
     * Time O((k+1) * E), Space O(n + E).
     */
    public static int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] f : flights) graph.get(f[0]).add(new int[]{f[1], f[2]});

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{src, 0});
        int stops = 0;

        while (!q.isEmpty() && stops <= k) {
            int size = q.size();
            for (int i = 0; i < size; i++) { // O(level width)
                int[] cur = q.poll();
                for (int[] nei : graph.get(cur[0])) { // O(edges from node)
                    int newCost = cur[1] + nei[1];
                    if (newCost < dist[nei[0]]) {
                        dist[nei[0]] = newCost;
                        q.offer(new int[]{nei[0], newCost});
                    }
                }
            }
            stops++;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
