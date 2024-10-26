package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 3331, medium, tags: tree, dfs.
 * <p>
 * You are given a tree rooted at node 0 that consists of n nodes numbered from 0 to n - 1. The tree is represented
 * by an array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 * <p>
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 * <p>
 * We make the following changes on the tree one time simultaneously for all nodes x from 1 to n - 1:
 * <p>
 * Find the closest node y to node x such that y is an ancestor of x, and s[x] == s[y].
 * If node y does not exist, do nothing.
 * Otherwise, remove the edge between x and its current parent and make node y the new parent of x by adding an
 * edge between them.
 * Return an array answer of size n where answer[i] is the size of the subtree rooted at node i in the final tree.
 * <p>
 * A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: parent = [-1,0,0,1,1,1], s = "abaabc"
 * <p>
 * Output: [6,3,1,1,1,1]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * The parent of node 3 will change from node 1 to node 0.
 * <p>
 * Example 2:
 * <p>
 * Input: parent = [-1,0,4,0,1], s = "abbba"
 * <p>
 * Output: [5,2,1,1,1]
 * <p>
 * Explanation:
 * <p>
 * <p>
 * The following changes will happen at the same time:
 * <p>
 * The parent of node 4 will change from node 1 to node 0.
 * The parent of node 2 will change from node 4 to node 1.
 * <p>
 * Constraints:
 * <p>
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 0 <= parent[i] <= n - 1 for all i >= 1.
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists only of lowercase English letters.
 * <p>
 * Hint 1
 * Perform a depth-first search on the tree, starting from the root.
 * Hint 2
 * During the DFS, keep track of the most recent node where each character from 'a' to 'z' has been seen.
 */
@SuppressWarnings("unused")
public class SubtreeSizesAC {
    // tree+dfs, n, n.
    static class Solution {
        Map<Integer, List<Integer>> G; // parent->[children]
        Map<Character, Integer> lastSeen;
        int[] newP;
        String s;
        Map<Integer, List<Integer>> newG;
        int[] res;

        public int[] findSubtreeSizes(int[] p, String s) {
            int n = p.length;
            this.s = s;
            G = new HashMap<>();
            for (int i = 1; i < n; i++)
                G.computeIfAbsent(p[i], k -> new ArrayList<>()).add(i);
            newP = p.clone();
            lastSeen = new HashMap<>();
            change(0);
            newG = new HashMap<>();
            for (int i = 1; i < n; i++)
                newG.computeIfAbsent(newP[i], k -> new ArrayList<>()).add(i);
            res = new int[n];
            dfs(0);
            return res;
        }

        // recursively change the parents, store in newP[]
        public void change(int node) {
            char ch = s.charAt(node);
            if (lastSeen.containsKey(ch) && lastSeen.get(ch) != newP[node])
                newP[node] = lastSeen.get(ch);
            Integer op = lastSeen.put(ch, node); // op: original parent
            if (G.containsKey(node))
                for (int c : G.get(node)) change(c);
            if (op == null) lastSeen.remove(ch);
            else lastSeen.put(ch, op);
        }

        void dfs(int node) {
            if (res[node] != 0) return;
            int size = 1;
            if (newG.containsKey(node))
                for (int c : newG.get(node)) {
                    dfs(c);
                    size += res[c];
                }
            res[node] = size;
        }
    }
}
