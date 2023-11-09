package tree;

import struct.TreeNode;

/**
 * LeetCode 285, LintCode 448, medium, tags: binary tree, bst, dfs.
 * <p>
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * <p>
 * The successor of a node p is the node with the smallest key greater than p.val.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [2,1,3], p = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], p = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 * <p>
 * Note:
 * <p>
 * If the given node has no in-order successor in the tree, return null.
 * It's guaranteed that the values of the tree are unique.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 * All Nodes will have unique values.
 */
public class InorderSuccessorBST {
    // solution 1, iterative. O(h) time, O(1) space. lint code, 2161 ms, 15.79 Mb.
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        TreeNode c = root, res = null;
        while (c != null) {
            if (c.val <= p.val) c = c.right; // search in right
            else {
                res = c; // remember c
                c = c.left; // search in left
            }
        }
        return res;
    }

    // solution 2, recursive. O(h) time and space. lint code 1944ms, 15.93MB.
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val <= p.val) return inorderSuccessor2(root.right, p);
        else {
            TreeNode left = inorderSuccessor2(root.left, p);
            return left == null ? root : left;
        }
    }
}
