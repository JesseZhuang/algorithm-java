package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 743, LintCode 1057, medium, tags: dfs, bfs, graph, heap, shortest path.
 * <p>
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as
 * directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it
 * takes for a signal to travel from source to target.
 * <p>
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the
 * signal. If it is impossible for all the n nodes to receive the signal, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 * Example 2:
 * <p>
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 * Example 3:
 * <p>
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {

    // solution 1, dijkstra, elge (worst case e==n^2) time, e space. 22ms, 47.6Mb.
    // belleman-ford, ve time, v space.
    public int networkDelayTimeDijkstra(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});// source -> [destination, weight/dist]
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // sort by dist
        int[] distTo = new int[n + 1]; // shortest path from source k, nodes numbered 1-n
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[k] = 0; // k->k dist == 0
        pq.offer(new int[]{k, 0}); // [destination node v, dist(k->v)]
        int max = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.remove();
            int v = cur[1]; // node v
            int curDist = cur[0]; // k->v dist
            if (curDist > distTo[v]) continue; // already found shorter path
            max = curDist;
            n--;
            if (!graph.containsKey(v)) continue;
            for (int[] next : graph.get(v)) {
                int w = next[0], d = next[1]; // destination node w, d==weight for v->w
                if (curDist + d < distTo[w]) {
                    distTo[w] = curDist + d;
                    pq.add(new int[]{distTo[w], w});
                }
            }
        }
        return n == 0 ? max : -1;
    }
}
