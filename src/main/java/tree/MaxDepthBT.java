package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 104. Easy. tags: tree, dfs, bfs, binary tree.
 * <p>
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/11/26/tmp-tree.jpg
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 * <p>
 * Input: root = [1,null,2]
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */

public class MaxDepthBT {

    /**
     * alg1: recursively calculate the height of left and right subtree.
     * possibly to modify this to be tail recursive.
     * 0ms, 43.6Mb.
     * O(n) time and O(height:n) stack space (refer to fibonacci recursive space). Look at each tree node once.
     */
    public int maxDepthRecursive(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepthRecursive(root.left), maxDepthRecursive(root.right));
    }

    /**
     * iterative version, probably not possible without using a stack. recursive
     * version is using the stack implicitly. can use Apache commons LinkedMap
     * to avoid using a stack (fundamentally still a stack).
     * 3ms, 42.1 Mb. O(n) time and O(height) stack space.
     */
    public int maxDepthDFS1(TreeNode root) {
        if (root == null)
            return 0;
        int curLevel = 1, maxLevel = 1;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        HashMap<TreeNode, Integer> height = new HashMap<>();
        height.put(root, curLevel);
        TreeNode cur = root.left;
        while (!st.isEmpty() || cur != null) {
            if (cur != null) {
                st.push(cur);
                height.put(cur, ++curLevel);
                maxLevel = Math.max(maxLevel, curLevel);
                cur = cur.left;
            } else {
                cur = st.pop();
                curLevel = height.get(cur);
                cur = cur.right;
            }
        }
        return maxLevel;
    }

    // 15ms, 44.3 Mb. O(n) time and O(height) stack space.
    public int maxDepthDFS2(TreeNode root) {
        if (root == null) return 0;
        int curLevel = 0, maxLevel = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Map<TreeNode, Integer> visited = new HashMap<>();
        visited.put(root, 0);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (visited.get(cur) == 2) {
                stack.pop();
                curLevel--;
            } else if (visited.get(cur) == 0) {
                curLevel++;
                if (cur.left != null) {
                    stack.push(cur.left);
                    visited.put(cur.left, 0);
                }
                visited.put(cur, 1);
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                    visited.put(cur.right, 0);
                }
                visited.put(cur, 2);
            }
            maxLevel = Math.max(curLevel, maxLevel);
        }
        return maxLevel;
    }

    // 1ms, 42.2Mb. O(n) time and O(max_nodes_one_level:n) space.
    public int maxDepthBFS1(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            for (int i = 0, n = queue.size(); i < n; i++) { // cannot i < queue.size()
                TreeNode cur = queue.remove();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return level;
    }
}
