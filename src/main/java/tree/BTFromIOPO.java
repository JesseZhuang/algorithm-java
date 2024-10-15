package tree;

import struct.TreeNode;

import java.util.HashMap;

/**
 * LeetCode 106, medium, tags: array, hash table, divide and conquer, tree, binary tree.
 * <p>
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and
 * postorder is the postorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/02/19/tree.jpg
 * <p>
 * <p>
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 */
@SuppressWarnings("unused")
public class BTFromIOPO {
    static class Solution {
        HashMap<Integer, Integer> memo = new HashMap<>(); // // val->index for inorder
        int[] post;
        int crip; // cur root index in post

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            // io 9,3,15,20,7; po 9,15,7,20,3
            for (int i = 0; i < inorder.length; i++) memo.put(inorder[i], i);
            post = postorder;
            crip = post.length - 1;
            return dfs(0, inorder.length - 1);
        }

        public TreeNode dfs(int l, int r) {
            if (l > r) return null;
            int root = post[crip--]; // crip r3 4; 3.r r20 3; 20.r r7 2; r15 1; r9 0
            TreeNode n = new TreeNode(root);
            int mid = memo.get(root); // r3 1; r20 3; r7 4; r15 2, r9 0
            n.right = dfs(mid + 1, r); // r3 2,4; r20 4,4; r7 5,4; . . r15 3,2; r9 1,0;
            n.left = dfs(l, mid - 1);  // r7 4,3; r20 2,2; . r15 2,1; r9 0,-1
            return n;
        }

    }
}
