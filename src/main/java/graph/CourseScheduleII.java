package graph;

import princeton.jsl.Digraph;
import princeton.jsl.Topological;
import util.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

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
 * <li>directed cycle, O(V+E) linear time, O(V+E) linear space. 39 ms 19.66%, 44.8 MB 87.83%.
 * <li>BFS, O(V+E) linear time, O(V+E) linear space.
 * </ul>
 */
public class CourseScheduleII {
    public Integer[] findOrder(int numCourses, Integer[][] prerequisites) {
        List<Integer> result = new ArrayList<>();
        Digraph dg = new Digraph(numCourses);
        edu.princeton.cs.algs4.Digraph dg2 = new edu.princeton.cs.algs4.Digraph(numCourses);
        for (Integer[] edge : prerequisites) {
            dg.addEdge(edge[1], edge[0]);
            dg2.addEdge(edge[1], edge[0]);
        }
        Topological t = new Topological(dg);
        edu.princeton.cs.algs4.Topological t2 = new edu.princeton.cs.algs4.Topological(dg2);
        if (t.hasOrder()) result = CollectionUtil.toList(t.order());
        return result.toArray(new Integer[result.size()]);
    }
}
