package graph;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 1192, hard, tags: dfs, graph, bi-connected component.
 * <p>
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network
 * where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other
 * servers directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * <p>
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2019/09/03/1537_ex1_2.png
 * <p>
 * <p>
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * Example 2:
 * <p>
 * Input: n = 2, connections = [[0,1]]
 * Output: [[0,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 10^5
 * n - 1 <= connections.length <= 10^5
 * 0 <= ai, bi <= n - 1
 * a_i != b_i
 * There are no repeated connections.
 * <p>
 * Hint 1
 * Use Tarjan's algorithm.
 */
public class CriticalConnection {

    List<List<Integer>> res;
    List<List<Integer>> adj;
    int[] ranks;

    public static void main(String[] args) {
        List<List<Integer>> edges = ImmutableList.of(
                ImmutableList.of(0, 1), ImmutableList.of(1, 2),
                ImmutableList.of(0, 2), ImmutableList.of(3, 1));
        System.out.println(new CriticalConnection().criticalConnections(4, edges));
    }

    // solution 1, dfs, O(v+e) time and space.
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        adj = new ArrayList<>();
        res = new ArrayList<>();
        ranks = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> e : connections) {
            adj.get(e.get(0)).add(e.get(1));
            adj.get(e.get(1)).add(e.get(0));
        }
        int[] ranks = new int[n];
        dfs(0, 0, 1);
        return res;
    }

    private int dfs(int cur, int parent, int rank) {
        ranks[cur] = rank;
        for (int next : adj.get(cur)) {
            if (next == parent) continue;
            if (ranks[next] == 0) ranks[next] = dfs(next, cur, rank + 1);
            ranks[cur] = Math.min(ranks[cur], ranks[next]);
            if (rank < ranks[next]) res.add(Arrays.asList(cur, next));
        }
        return ranks[cur];
    }
}
