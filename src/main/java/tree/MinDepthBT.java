package tree;

import struct.TreeNode;

import java.util.ArrayDeque;

/**
 * LeetCode 111. Easy.
 * <p>
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * return its minimum depth = 2.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>recursive, height = lgN, O(height) time, O(height) stack space.</b>
 * <li>use a queue, O(height) time, O(height) space.
 * </ul>
 */
public class MinDepthBT {

    /**
     * wrong for
     * <pre>
     1:root
     2,3
     4,#,#,5
     #,#,#,#
     </pre>
     */
    @Deprecated
    private int minDepthRecWrong(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.min(minDepthRecWrong(root.left), minDepthRecWrong(root.right));
    }

    public static int minDepthQueue(TreeNode root) {
        if (root == null) return 0;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            while (n-- > 0) {
                TreeNode cur = queue.remove();
                // without the line below, will process till reaching long end
                if (cur.left == null && cur.right == null) return level;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            if (queue.size() != 0) level++;
        }
        return level;
    }

    public static int minDepthRec1(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepthRec1(root.right) + 1;
        if (root.right == null) return minDepthRec1(root.left) + 1;
        return Math.min(minDepthRec1(root.left), minDepthRec1(root.right)) + 1;
    }

    public static int minDepthRec2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null) return minDepthRec2(root.right) + 1;
        if (root.right == null) return minDepthRec2(root.left) + 1;
        return Math.min(minDepthRec2(root.left), minDepthRec2(root.right)) + 1;
    }

}
