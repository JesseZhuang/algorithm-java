package tree;

import struct.TreeNode;

/**
 * LeetCode 701, medium, tags: tree, bst, binary tree. LintCode 85.
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion. It is guaranteed that the new value does
 * not exist in the original BST.
 * <p>
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains
 * a BST after insertion. You can return any of them.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg
 * https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg
 * <p>
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 * <p>
 * Example 2: https://assets.leetcode.com/uploads/2020/10/05/bst.jpg
 * <p>
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 * Example 3:
 * <p>
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree will be in the range [0, 104].
 * -108 <= Node.val <= 108
 * All the values Node.val are unique.
 * -108 <= val <= 108
 * It's guaranteed that val does not exist in the original BST.
 */
public class InsertBST {
    // 0ms, 43 Mb. recursive, O(H) time and tree height stack space, worst case H == N.
    public TreeNode insertIntoBSTR(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) root.left = insertIntoBSTR(root.left, val);
        else root.right = insertIntoBSTR(root.right, val);
        return root;
    }

    // 0ms, 42.8 Mb. Iterative. O(H) time, O(1) space.
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode cur = root, parent;
        int cmp;
        do {
            parent = cur;
            cmp = Integer.compare(val, cur.val); // note not to compare with root!
            if (cmp < 0) cur = cur.left;
            else cur = cur.right;
        } while (cur != null);
        if (cmp < 0) parent.left = new TreeNode(val);
        else parent.right = new TreeNode(val);
        return root;
    }
}
