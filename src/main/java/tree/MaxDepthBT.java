package tree;

import struct.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * LeetCode 104. Easy.
 * <p>
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 * <p>
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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * iterative version, probably not possible without using a stack. recursive
     * version is using the stack implicitly. can use Apache commons LinkedMap
     * to avoid using a stack (fundamentally still a stack).
     */
    public int maxDepth2(TreeNode root) {
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

    // same as alg 1, no need to check left right null
    @Deprecated
    public int maxDepth3(TreeNode root) {
        int ans = 0;
        if (root != null) {
            int left = 0, right = 0;
            if (root.left != null)
                left = 1 + maxDepth(root.left);
            if (root.right != null)
                right = 1 + maxDepth(root.right);
            ans = Math.max(left, right);
        }
        return ans;
    }

}
