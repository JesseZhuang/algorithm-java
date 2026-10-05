package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 1129 - Shortest Path with Alternating Colors.
 */
public final class ShortestPathAlternatingColors {
    private static final int RED = 0;
    private static final int BLUE = 1;
    private static final int NONE = -1;

    private ShortestPathAlternatingColors() {}

    /**
     * BFS over (node, previous edge color) states from node 0.
     * Time O(n + m), Space O(n + m), where m is redEdges.length + blueEdges.length.
     */
    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : redEdges) graph.get(edge[0]).add(new int[]{edge[1], RED});
        for (int[] edge : blueEdges) graph.get(edge[0]).add(new int[]{edge[1], BLUE});

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, NONE});
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int node = current[0];
                int previousColor = current[1];
                if (answer[node] == -1) answer[node] = distance;

                for (int[] next : graph.get(node)) {
                    int nextNode = next[0];
                    int nextColor = next[1];
                    if (nextColor != previousColor && !visited[nextNode][nextColor]) {
                        visited[nextNode][nextColor] = true;
                        queue.offer(new int[]{nextNode, nextColor});
                    }
                }
            }
            distance++;
        }
        return answer;
    }
}
