package tree;

import struct.TreeNode;

/**
 * LeetCode 235, medium, tags: tree, dfs, binary search tree, binary tree.
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * <pre>
 *        _______6______
 *       /              \
 *   ___2__          ___8__
 *  /      \        /      \
 *  0      4       7       9
 *        / \
 *       3  5
 * </pre>
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2: https://assets.leetcode.com/uploads/2018/12/14/binarysearchtree_improved.png
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA
 * definition.
 * Example 3:
 * <p>
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */
public class LCAofBST {

    // solution 1, 4ms, 43.3Mb. essentially same as solution 2.
    public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (true) {
            if (p.val < cur.val && q.val < cur.val) cur = cur.left;
            else if (p.val > cur.val && q.val > cur.val) cur = cur.right;
            else break;
        }
        return cur;
    }

    // solution 2, 5ms, 42.9Mb. O(h, worst case n) time, O(1) space.
    public TreeNode lowestCommonAncestorI2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while ((cur.val - (long) p.val) * (cur.val - (long) q.val) > 0) // note value ranges, overflow
            cur = p.val < cur.val ? cur.left : cur.right;
        return cur;
    }

    // 5ms, 43.1Mb. O(h) time and space. recursive.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        else return root;
    }
}
