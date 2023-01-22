package tree;

import struct.TreeNode;

import java.util.HashMap;
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
 * <b>Summary</b>
 * <ul>
 * <li>recursive, max of left and right, O(height) time, O(height) stack space.</li>
 * <li>iterative with a stack, O(height) time, O(height) space.</li>
 * </ul>
 */

public class MaxDepthBT {

    /**
     * alg1: recursively calculate the height of left and right sub tree.
     * possibly to modify this to be tail recursive.
     */
    public int maxDepthRecursive(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepthRecursive(root.left), maxDepthRecursive(root.right));
    }

    /**
     * iterative version, probably not possible without using a stack. recursive
     * version is using the stack implicitly. can use Apache commons LinkedMap
     * to avoid using a stack (fundamentally still a stack).
     * 3ms, 42.1 Mb.
     */
    public int maxDepthIter(TreeNode root) {
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

}
