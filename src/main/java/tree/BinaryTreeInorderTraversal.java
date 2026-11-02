package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 94, easy, tags: tree, depth-first search, stack, binary tree.
 */
public final class BinaryTreeInorderTraversal {

    private BinaryTreeInorderTraversal() {
    }

    /**
     * Iterative inorder traversal (left -> root -> right) using an explicit stack.
     * O(n) time and O(h) space, where h is the tree height.
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            values.add(current.val);
            current = current.right;
        }
        return values;
    }
}
