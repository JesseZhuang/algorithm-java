package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 2497, Medium. Tags: graph, heap.
 * There is an undirected graph consisting of n nodes numbered from 0 to n - 1.
 * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.
 * <p>
 * You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists
 * an undirected edge connecting nodes ai and bi.
 * <p>
 * A star graph is a subgraph of the given graph having a center node containing 0 or more neighbors.
 * In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.
 * <p>
 * The star sum is the sum of the values of all the nodes present in the star graph.
 * <p>
 * Given an integer k, return the maximum star sum of a star graph containing at most k edges.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
 * Output: 16
 * Explanation: The above diagram represents the input graph.
 * The star graph with the maximum star sum is denoted by blue. It is centered at 3 and includes its neighbors 1 and 4.
 * It can be shown it is not possible to get a star graph with a sum greater than 16.
 * Example 2:
 * <p>
 * Input: vals = [-5], edges = [], k = 0
 * Output: -5
 * Explanation: There is only one possible star graph, which is node 0 itself.
 * Hence, we return -5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == vals.length
 * 1 <= n <= 105
 * -104 <= vals[i] <= 104
 * 0 <= edges.length <= min(n * (n - 1) / 2, 105)
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 0 <= k <= n - 1
 */
public class MaxStarSumOfGraph {
    // 199 ms, 167.4 Mb. O(V+E) time, O(V*E) space.
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {// O(E)
            map.computeIfAbsent(edge[0], t -> new PriorityQueue<>()).offer(-vals[edge[1]]);
            map.computeIfAbsent(edge[1], t -> new PriorityQueue<>()).offer(-vals[edge[0]]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < vals.length; i++) { // O(V)
            for (int j = 0; j < k && map.containsKey(i) && !map.get(i).isEmpty() && map.get(i).peek() < 0; j++) {
                vals[i] -= map.get(i).poll();
            }
            max = Math.max(max, vals[i]);
        }
        return max;
    }
}
