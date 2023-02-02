package tree;

import struct.TreeNode;
import util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * LeetCode 236, medium, tags: tree, dfs, binary tree.
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2: https://assets.leetcode.com/uploads/2018/12/14/binarytree.png
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the
 * LCA definition.
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class LCAofBT {

    // 5ms, 43.3 Mb. O(n) time and O(h) space.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        else if (left != null) return left;
        else return right;
    }

    // 13m, 43 Mb. O(n) time and space.
    public TreeNode lowestCommonAncestorI1(TreeNode root, TreeNode p, TreeNode q) {
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>(); // node and how many children need to traverse
        stack.push(new Pair<>(root, 2));
        boolean oneNodeFound = false;
        TreeNode LCA = null, childNode;
        while (!stack.isEmpty()) { // post order traverse
            Pair<TreeNode, Integer> top = stack.peek();
            TreeNode parentNode = top.getKey();
            int parentState = top.getValue();
            if (parentState != 0) { // can't be popped off yet.
                if (parentState == 2) {
                    if (parentNode == p || parentNode == q) {
                        if (oneNodeFound) return LCA;
                        else {
                            oneNodeFound = true;
                            LCA = stack.peek().getKey();
                        }
                    }
                    childNode = parentNode.left; // If both pending, traverse the left child first
                } else childNode = parentNode.right;
                stack.pop();
                stack.push(new Pair<>(parentNode, parentState - 1));
                if (childNode != null) stack.push(new Pair<>(childNode, 2));
            } else { // both children traversed
                if (LCA == stack.pop().getKey() && oneNodeFound) LCA = stack.peek().getKey();
            }
        }
        return null;
    }

    // 12ms, 42.9 Mb. O(n) time and space.
    public TreeNode lowestCommonAncestorI2(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        stack.push(root);
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                parent.put(cur.left, cur);
                stack.push(cur.left);
            }
            if (cur.right != null) {
                parent.put(cur.right, cur);
                stack.push(cur.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q)) q = parent.get(q);
        return q;
    }
}
