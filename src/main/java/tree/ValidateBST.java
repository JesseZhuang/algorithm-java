package tree;

import struct.TreeNode;

import java.util.ArrayDeque;

/**
 * LeetCode 98, medium, tags: binary tree, bst, dfs, binary search.
 * <p>
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <ul>
 * <li>The left subtree of a node contains only nodes with keys strictly less than the node's key.</li>
 * <li>The right subtree of a node contains only nodes with keys strictly greater than the node's key.</li>
 * <li>Both the left and right subtrees must also be binary search trees.</li>
 * </ul>
 * <p>
 * Example 1:
 * <pre>
 *     2
 *    / \
 *   1   3
 * </pre>
 * Input: root = [2,1,3]
 * Output: true
 * <p>
 * Example 2:
 * <pre>
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * </pre>
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * <p>
 * Constraints:
 * <ul>
 * <li>The number of nodes in the tree is in the range [1, 10^4].</li>
 * <li>-2^31 <= Node.val <= 2^31 - 1</li>
 * </ul>
 */
public final class ValidateBST {
    private ValidateBST() {
    }

    /**
     * Recursive DFS with long min/max bounds. O(n) time, O(h) space.
     */
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true; // O(1) base case
        if (node.val <= min || node.val >= max) return false; // long bounds avoid int overflow
        return validate(node.left, min, node.val) // O(h) recursion depth
                && validate(node.right, node.val, max);
    }

    /**
     * Iterative inorder traversal with stack, track prev value as long. O(n) time, O(h) space.
     */
    public static boolean isValidBST2(TreeNode root) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>(); // O(h) space for stack
        TreeNode cur = root;
        long prev = Long.MIN_VALUE; // long to handle Integer.MIN_VALUE node values
        while (cur != null || !stack.isEmpty()) { // O(n) visits each node once
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (cur.val <= prev) return false; // inorder must be strictly increasing
            prev = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
