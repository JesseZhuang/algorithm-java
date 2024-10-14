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
 * Read more about how a graph is represented. You may assume that there are no duplicate edges in the
 * input prerequisites.
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 2000, n
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1), e
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 * <p>
 * Hint 1
 * This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological
 * ordering exists and therefore it will be impossible to take all courses.
 * Hint 2
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
 * Topological Sort. https://www.youtube.com/watch?v=ozso3xxkVGU
 * Hint 3
 * Topological sort could also be done via BFS.
 */
@SuppressWarnings("unused")
public class CourseScheduleII {

    // credit @lx223, n+e, n+e.
    static class Solution {
        List<List<Integer>> adj;
        BitSet hasCycle;
        BitSet marked;
        BitSet onStack;
        List<Integer> order;

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] indeg = new int[numCourses];
            adj = new ArrayList<>(numCourses);
            int n = numCourses;
            while (n-- > 0) adj.add(new ArrayList<>());
            for (int[] edge : prerequisites) {
                indeg[edge[0]]++;
                adj.get(edge[1]).add(edge[0]);
            }
            //return byBFS(indeg);
            return byDfs();
        }

        private int[] byDfs() {
            hasCycle = new BitSet(1);
            marked = new BitSet(adj.size());
            onStack = new BitSet(adj.size());
            order = new ArrayList<>();
            for (int i = adj.size() - 1; i >= 0; i--)
                if (!hasCycle.get(0)) dfs(i);
                else return new int[0];
            Collections.reverse(order);
            return order.stream().mapToInt(i -> i).toArray(); // unbox
        }

        private void dfs(int v) {
            if (marked.get(v)) return;
            marked.set(v);
            onStack.set(v);
            for (int w : adj.get(v)) {
                if (hasCycle.get(0)) return;
                else if (onStack.get(w)) hasCycle.set(0);
                else dfs(w);
            }
            onStack.clear(v);
            order.add(v);
        }

        private int[] byBFS(int[] indeg) {
            int[] order = new int[indeg.length];
            ArrayDeque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < indeg.length; i++) if (indeg[i] == 0) q.add(i);
            int cnt = 0;
            while (!q.isEmpty()) {
                int v = q.poll();
                order[cnt++] = v;
                for (int w : adj.get(v)) {
                    indeg[w]--;
                    if (indeg[w] == 0) q.offer(w);
                    else if (indeg[w] < 0) return new int[0];
                }
            }
            return cnt == indeg.length ? order : new int[0];
        }
    }

    // algs4, dfs reverse post order with stack onStack[] check cycle
    // bfs q indegree 0, count==V check cycle
    static class SolutionAlgs4 {
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
            return result.toArray(Integer[]::new);
        }
    }
}
