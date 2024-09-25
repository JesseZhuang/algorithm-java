package tree;

import java.util.*;

/**
 * LeetCode 582, LintCode 872, medium, tags: tree, dfs, bfs, array, hash-table.
 * <p>
 * You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where
 * pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 * <p>
 * Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0,
 * which means this process has no parent process (the root of the tree).
 * <p>
 * When a process is killed, all of its children processes will also be killed.
 * <p>
 * Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes
 * that will be killed. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * Output: [5,10]
 * Explanation: The processes colored in red are the processes that should be killed.
 * Example 2:
 * <p>
 * Input: pid = [1], ppid = [0], kill = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == pid.length
 * n == ppid.length
 * 1 <= n <= 5 * 10^4
 * 1 <= pid[i] <= 5 * 10^4
 * 0 <= ppid[i] <= 5 * 10^4
 * Only one process has no parent.
 * All the values of pid are unique.
 * kill is guaranteed to be in pid.
 */
@SuppressWarnings("unused")
public class KillProcess {
    // O(n) time and space. LintCode 610ms, 26.40mb.
    static class Solution {
        Map<Integer, List<Integer>> g = new HashMap<>(); //directed graph parent->[children]
        List<Integer> res = new ArrayList<>();

        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            int n = pid.size();
            for (int i = 0; i < n; ++i)
                g.computeIfAbsent(ppid.get(i), k -> new ArrayList<>()).add(pid.get(i));
            dfs(kill);
            return res;
        }

        void dfs(int i) {
            res.add(i);
            for (int j : g.getOrDefault(i, Collections.emptyList())) dfs(j);
        }
    }
}
