package tree;

import struct.TreeNode;

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
public class InvertBT {
    // 0ms, 39.6 MB. recursive. O(n) time and space.
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode right = invertTree(root.right), left = invertTree(root.left); // must use temp variables
        root.left = right;
        root.right = left;
        return root;
    }
}
