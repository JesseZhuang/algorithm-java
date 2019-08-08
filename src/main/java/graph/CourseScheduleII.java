package graph;

import princeton.jsl.Digraph;
import princeton.jsl.Topological;
import princeton.jsl.TopologicalX;
import util.CollectionUtil;

import java.util.*;

/**
 * LeetCode 210 Medium. Tags: DFS, BFS, Graph, Topological Sort.
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1] .
 * Example 2:
 * <p>
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 * courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * Read more about how a graph is represented. You may assume that there are no duplicate edges in the input prerequisites.
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>DFS reverse post, O(V+E) linear time, O(V+E) linear space. 6 ms 62.42%, 46 MB 64.63%.
 * <li>BFS, O(V+E) linear time, O(V+E) linear space.
 * </ul>
 */
public class CourseScheduleII {
    public Integer[] findOrderDFS(int numCourses, Integer[][] prerequisites) {
        return helper(numCourses, prerequisites, true);
    }

    public Integer[] findOrderBFS(int numCourses, Integer[][] prerequisites) {
        return helper(numCourses, prerequisites, false);
    }

    private Integer[] helper(int numCourses, Integer[][] prerequisites, boolean useDFS) {
        List<Integer> result;
        Digraph dg = new Digraph(numCourses);
        for (Integer[] edge : prerequisites) dg.addEdge(edge[1], edge[0]);
        Optional<Iterable<Integer>> order;
        if (useDFS) order = Optional.ofNullable(new Topological(dg).order());
        else order = Optional.ofNullable(new TopologicalX(dg).order());
        result = CollectionUtil.toList(order.orElse(new ArrayList<>()));
        return result.toArray(new Integer[result.size()]);
    }

    public Integer[] findOrderBFS2(int numCourses, Integer[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegree = new int[numCourses];
        Integer[] topologicalOrder = new Integer[numCourses];

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            adjList.put(src, lst);

            // Record in-degree of each vertex
            indegree[dest] += 1;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) q.add(i);

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    indegree[neighbor]--;

                    // If in-degree of a neighbor becomes 0, add it to the Q
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        // Check to see if topological sort is possible or not.
        if (i == numCourses) return topologicalOrder;
        return new Integer[0];
    }

    // DFS2
    static int WHITE = 1;
    static int GRAY = 2;
    static int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> color;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;
        this.color = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();

        // By default all vertices are WHITE
        for (int i = 0; i < numCourses; i++) {
            this.color.put(i, WHITE);
        }
    }

    private void dfs(int node) {
        // Don't recurse further if we found a cycle already
        if (!this.isPossible) {
            return;
        }

        // Start the recursion
        this.color.put(node, GRAY);

        // Traverse on neighboring vertices
        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<>())) {
            if (this.color.get(neighbor) == WHITE) this.dfs(neighbor);
            else if (this.color.get(neighbor) == GRAY)
                // An edge to a GRAY vertex represents a cycle
                this.isPossible = false;
        }

        // Recursion ends. We mark it as black
        this.color.put(node, BLACK);
        this.topologicalOrder.add(node);
    }

    public Integer[] findOrderDFS2(int numCourses, Integer[][] prerequisites) {
        this.init(numCourses);

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) if (this.color.get(i) == WHITE) this.dfs(i);

        Integer[] order;
        if (this.isPossible) {
            order = new Integer[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = this.topologicalOrder.get(numCourses - i - 1);
            }
        } else {
            order = new Integer[0];
        }

        return order;
    }
}
