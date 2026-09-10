package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 802. Medium. Tags: DFS, BFS, Graph, Topological Sort.
 * <p>
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a
 * 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there
 * is an edge from node i to each node in graph[i].
 * <p>
 * A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting
 * from that node leads to a terminal node (or another safe node).
 * <p>
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 * <p>
 * Example 1:
 * <pre>
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * </pre>
 * <p>
 * Constraints:
 * <p>
 * n == graph.length
 * 1 <= n <= 10^4
 * 0 <= graph[i].length <= n
 * 0 <= graph[i][j] <= n - 1
 * graph[i] is sorted in a strictly increasing order.
 * The graph may contain self-loops.
 * The number of edges in the graph will be in the range [1, 4 * 10^4].
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>DFS 3-coloring, O(V+E) time, O(V) space.
 * <li>Reverse graph + topological sort BFS, O(V+E) time, O(V+E) space.
 * </ul>
 */
public final class EventualSafeStates {

    private EventualSafeStates() {
    }

    // solution 1, DFS 3-coloring. O(V+E) time, O(V) space.
    // color: 0=unvisited, 1=visiting (on current path), 2=safe
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // O(V) space
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (dfs(graph, color, i)) result.add(i);
        return result;
    }

    // returns true if node is safe (no cycle reachable)
    private static boolean dfs(int[][] graph, int[] color, int node) {
        if (color[node] != 0) return color[node] == 2; // already classified
        color[node] = 1; // mark visiting
        for (int next : graph[node]) // O(V+E) total across all calls
            if (!dfs(graph, color, next)) return false; // cycle found
        color[node] = 2; // mark safe
        return true;
    }

    // solution 2, reverse graph + topological sort BFS. O(V+E) time, O(V+E) space.
    public static List<Integer> eventualSafeNodesBFS(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> reverseAdj = new ArrayList<>(); // O(V+E) space for reverse graph
        int[] outDegree = new int[n];
        for (int i = 0; i < n; i++) reverseAdj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) { // build reverse graph, O(V+E)
            outDegree[i] = graph[i].length;
            for (int next : graph[i]) reverseAdj.get(next).add(i);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            if (outDegree[i] == 0) queue.offer(i); // terminal nodes seed the BFS
        boolean[] safe = new boolean[n];
        while (!queue.isEmpty()) { // O(V+E) total for BFS loop
            int node = queue.poll();
            safe[node] = true;
            for (int prev : reverseAdj.get(node)) // propagate safety backwards
                if (--outDegree[prev] == 0) queue.offer(prev);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (safe[i]) result.add(i); // collect in sorted order
        return result;
    }
}
