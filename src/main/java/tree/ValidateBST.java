package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 98, medium, tags: tree, binary tree, dfs, bfs.
 * <p>
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg
 * <p>
 * <p>
 * Input: root = [2,1,3]
 * Output: true
 * Example 2: https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg
 * <p>
 * <p>
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */
public class ValidateBST {
    // O(n) time and space. build in order list. 1ms, 41.5 Mb.
    public boolean isValidBSTR2(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        buildInOrder(root, inOrder);
        for (int i = 0; i < inOrder.size() - 1; i++)
            if (inOrder.get(i) >= inOrder.get(i + 1)) return false;
        return true;
    }

    private void buildInOrder(TreeNode n, List<Integer> inOrder) {
        if (n == null) return;
        if (n.left != null) buildInOrder(n.left, inOrder);
        inOrder.add(n.val);
        if (n.right != null) buildInOrder(n.right, inOrder);
    }

    // O(n) time, O(height, worst case n) recursion space, 0ms, 41.6MB.
    public boolean isValidBSTR1(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode n, Integer low, Integer high) {
        if (n == null) return true;
        if ((low != null && n.val <= low) || (high != null && n.val >= high)) return false;
        return validate(n.left, low, n.val) && validate(n.right, n.val, high);
    }

    Deque<TreeNode> nodes = new LinkedList<>();
    Deque<Integer> lows = new LinkedList<>();
    Deque<Integer> highs = new LinkedList<>();

    // 5ms, 42.7MB.
    public boolean isValidBSTI2(TreeNode root) {
        Integer low = null, high = null;
        update(root, low, high);
        while (!nodes.isEmpty()) {
            TreeNode n = nodes.remove();
            low = lows.remove();
            high = highs.remove();
            if (n == null) continue;
            if (low != null && n.val <= low) return false;
            if (high != null && n.val >= high) return false;
            update(n.left, low, n.val);
            update(n.right, n.val, high);
        }
        return true;
    }

    private void update(TreeNode n, Integer low, Integer high) {
        nodes.add(n);
        lows.add(low);
        highs.add(high);
    }

    // 2ms, 41.8 Mb.
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && root.val <= prev) return false;
            prev = root.val;
            root = root.right;
        }
        return true;
    }

}
