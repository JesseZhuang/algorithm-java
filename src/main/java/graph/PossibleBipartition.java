package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 886, medium, tags: dfs, bfs, graph, union find.
 * <p>
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 * <p>
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai
 * does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: The first group has [1,4], and the second group has [2,3].
 * Example 2:
 * <p>
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 10^4
 * dislikes[i].length == 2
 * 1 <= ai < bi <= n
 * All the pairs of dislikes are unique.
 */
public class PossibleBipartition {
    // dfs, v+e time and space. 14ms, 51.67Mb.
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) adj.add(new ArrayList<>());
        for (int[] d : dislikes) {
            adj.get(d[0]).add(d[1]); // note vertices in dislikes starting with 1
            adj.get(d[1]).add(d[0]);
        }
        Boolean[] colors = new Boolean[n + 1]; // also as visited[], important not boolean, default null:not visited
        for (int v = 1; v <= n; v++)
            // important to check visited before calling dfs, this dfs does not know starting vertex
            if (colors[v] == null && dfs(adj, v, colors, true)) return false;
        return true;
    }

    // return true if bipartition not possible, odd length cycle exists
    boolean dfs(List<List<Integer>> adj, int v, Boolean[] colors, boolean color) {
        colors[v] = color;
        for (int w : adj.get(v)) {
            if (colors[w] == null) {
                if (dfs(adj, w, colors, !color)) return true;
            } else if (colors[w] == colors[v]) return true; // already colored with same color, not possible
        }
        return false;
    }
}
