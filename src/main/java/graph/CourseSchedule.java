package graph;

import princeton.jsl.Digraph;
import princeton.jsl.DirectedCycle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.util.stream.IntStream.range;

/**
 * LeetCode 207. Medium. Tags: BFS, DFS, Graph, Topological Sort.
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <pre>
 * Input: 2, [[1,0]]
 * Output: true
 * </pre>
 * <p>
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <pre>
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * </pre>
 * <p>
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>directed cycle, O(V+E) linear time, O(V+E) linear space. 39 ms 19.66%, 44.8 MB 87.83%.
 * <li>BFS, O(V+E) linear time, O(V+E) linear space.
 * </ul>
 */
public class CourseSchedule {
    public boolean canFinishCycle(int numCourses, int[][] prerequisites) {
        Digraph digraph = new Digraph(numCourses);
        range(0, prerequisites.length).forEach(i -> digraph.addEdge(prerequisites[i][0], prerequisites[i][1]));
        DirectedCycle cycle = new DirectedCycle(digraph);
        return !cycle.hasCycle();
    }

    // 3ms 42.4Mb. O(V+E) linear time and space.
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new LinkedList[numCourses]; // vertexes going into
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) adj[i] = new LinkedList<>();
        for (int[] pair : prerequisites) {
            adj[pair[1]].add(pair[0]);
            inDegree[pair[0]]++;
        }
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0) queue.offer(i);
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int w : adj[course])
                if (--inDegree[w] == 0) queue.offer(w);
        }
        return count == numCourses;
    }

    // DFS O(V+E) time and space. 2ms, 42.3Mb.
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) adj[i] = new ArrayList<>();
        for (int[] vertex : prerequisites) adj[vertex[1]].add(vertex[0]);
        boolean[] visited = new boolean[numCourses], onStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++)
            if (!visited[i] && hasCycle(i, visited, onStack, adj)) return false;
        return true;
    }

    private boolean hasCycle(int source, boolean[] visited, boolean[] onStack, List<Integer>[] adj) {
        visited[source] = true;
        onStack[source] = true;
        for (int v : adj[source]) {
            if (onStack[v]) return true;
            if (!visited[v] && hasCycle(v, visited, onStack, adj)) return true;
        }
        onStack[source] = false;
        return false;
    }
}
