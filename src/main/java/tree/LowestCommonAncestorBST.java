package tree;

import struct.TreeNode;

/**
 * LeetCode 237. Easy.
 * <p>
 * Given a binary search tree (BST), find the lowest common
 * ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes v and w as the lowest node in T that has both v
 * and w as descendants (where we allow a node to be a descendant of itself).”
 *
 * <pre>
 *        _______6______
 *       /              \
 *   ___2__          ___8__
 *  /      \        /      \
 *  0      4       7       9
 *        / \
 *       3  5
 * </pre>
 * <p>
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another
 * example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of
 * itself according to the LCA definition.
 * <b>Summary</b>
 * <ul>
 * <li>multiplication sign, let h = height from root for LCA, O(h) time, O(1) space.</li>
 * <li>recursive, O(h) time, O(1) space</li>
 * </ul>
 */
public class LowestCommonAncestorBST {
    /**
     * one pass, directly accepted.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode res = root;
        // assuming p, q not null, no duplicates in tree
        while ((p.val - res.val) * (q.val - res.val) > 0) {
            if (p.val < res.val) res = res.left;
            else res = res.right;
        }
        return res;
    }

    /**
     * recursive, one liner from forum.
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return (root.val - p.val) * (root.val - q.val) <= 0 ? root
                : lowestCommonAncestor2(p.val < root.val ? root.left : root.right, p, q);
    }
}
