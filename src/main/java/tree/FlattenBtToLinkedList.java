package tree;

import struct.TreeNode;

/**
 * <a href="https://leetcode.com/problems/flatten-binary-tree-to-linked-list/">114. Flatten Binary Tree to Linked List</a>
 * Given the root of a binary tree, flatten the tree into a "linked list" using right pointers (pre-order).
 */
public final class FlattenBtToLinkedList {

    private FlattenBtToLinkedList() {
    }

    /**
     * Solution 1: Iterative. For each node with a left child, find the rightmost node of the left subtree,
     * link current right subtree to it, then move left to right. O(n) time, O(1) space.
     */
    public static void flattenIterative(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode rightmost = cur.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                rightmost.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    /**
     * Solution 2: Recursive reverse postorder (right, left, root) with a prev pointer.
     * O(n) time, O(h) space.
     */
    public static void flattenRecursive(TreeNode root) {
        flatten(root, new TreeNode[]{null});
    }

    private static void flatten(TreeNode node, TreeNode[] prev) {
        if (node == null) return;
        flatten(node.right, prev);
        flatten(node.left, prev);
        node.right = prev[0];
        node.left = null;
        prev[0] = node;
    }
}
