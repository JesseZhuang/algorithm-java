package graph;

import princeton.jsl.DirectedCycle;
import princeton.jsl.Digraph;

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
    public boolean canFinishCycle(int numCourses, Integer[][] prerequisites) {
        Digraph digraph = new Digraph(numCourses);
        range(0, prerequisites.length).forEach(i -> digraph.addEdge(prerequisites[i][0], prerequisites[i][1]));
        DirectedCycle cycle = new DirectedCycle(digraph);
        return !cycle.hasCycle();
    }

    public boolean canFinishBFS(int numCourses, Integer[][] prerequisites) {
        List<Integer>[] adj = new LinkedList[numCourses]; // vertexes going into
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        int count = 0;
        for (int i = 0; i < numCourses; i++) adj[i] = new LinkedList<>();
        for (Integer[] pair : prerequisites) {
            adj[pair[1]].add(pair[0]);
            inDegree[pair[0]]++;
        }
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0) queue.offer(i);
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int w : adj[course])
                if (--inDegree[w] == 0) queue.offer(w);
        }
        return count == numCourses;
    }
}
