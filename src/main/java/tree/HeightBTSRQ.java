package tree;

import struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2458, hard, medium: array, tree, dfs, bfs, binary tree.
 * <p>
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are
 * also given an array queries of size m.
 * <p>
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 * <p>
 * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i]
 * will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
 * <p>
 * Note:
 * <p>
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
 * Output: [2]
 * Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
 * The height of the tree is 2 (The path 1 -> 3 -> 2).
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
 * Output: [3,2,3,2]
 * Explanation: We have the following queries:
 * - Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
 * - Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
 * - Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
 * - Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is n.
 * 2 <= n <= 10^5
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * m == queries.length
 * 1 <= m <= min(n, 10^4)
 * 1 <= queries[i] <= n
 * queries[i] != root.val
 * <p>
 * Hint 1
 * Try pre-computing the answer for each node from 1 to n, and answer each query in O(1).
 * Hint 2
 * The answers can be pre-computed in a single tree traversal after computing the height of each subtree.
 */
@SuppressWarnings("unused")
public class HeightBTSRQ {
    static class Solution {
        Map<Integer, Integer> resMap; // i(removal)->height
        Map<TreeNode, Integer> nHeight; // tree node->height

        public int[] treeQueries(TreeNode root, int[] queries) {
            resMap = new HashMap<>();
            nHeight = new HashMap<>();
            dfs(root, 0, 0);
            int[] res = new int[queries.length];
            for (int i = 0; i < queries.length; i++) res[i] = resMap.get(queries[i]);
            return res;
        }

        // Function to calculate the height of the tree
        private int height(TreeNode node) {
            if (node == null) return -1;
            if (nHeight.containsKey(node)) return nHeight.get(node);
            int h = 1 + Math.max(height(node.left), height(node.right));
            nHeight.put(node, h);
            return h;
        }

        // DFS to precompute the maximum values after removing the subtree
        private void dfs(TreeNode node, int depth, int maxVal) {
            if (node == null) return;
            resMap.put(node.val, maxVal);
            // Traverse left and right subtrees while updating max values
            dfs(node.left, depth + 1, Math.max(maxVal, depth + 1 + height(node.right)));
            dfs(node.right, depth + 1, Math.max(maxVal, depth + 1 + height(node.left)));
        }
    }
}
