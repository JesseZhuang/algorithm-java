package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 226, easy, tags: tree, binary tree, dfs, bfs.
 * Given the root of a binary tree, invert the tree, and return its root.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg
 * <p>
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * Example 2: https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg
 * <p>
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
@SuppressWarnings("unused")
public class InvertBT {
    // 0ms, 39.6 MB. recursive. O(n) time and space (O(height), worst case O(n)).
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left); // must use temp variables
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }

    // bfs, 0ms, 39.6 Mb. O(n) time, O(leaves, worst case n/2), so O(n) space.
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return root;
    }
}
