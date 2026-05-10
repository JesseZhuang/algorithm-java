package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * LeetCode 105, medium, tags: array, hash table, divide and conquer, tree, binary tree.
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
 * inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/02/19/tree.jpg
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of preorder also appears in inorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
@SuppressWarnings("unused")
public class BTFromPOIO {

    // O(n) time, O(n) space. Recursive DFS with HashMap.
    static class Solution {
        HashMap<Integer, Integer> memo = new HashMap<>(); // val->index for inorder
        int[] pre;
        int crip; // cur root index in pre

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0; i < inorder.length; i++) memo.put(inorder[i], i);
            pre = preorder;
            crip = 0;
            return dfs(0, inorder.length - 1);
        }

        private TreeNode dfs(int l, int r) {
            if (l > r) return null;
            int root = pre[crip++];
            TreeNode n = new TreeNode(root);
            int mid = memo.get(root);
            n.left = dfs(l, mid - 1);
            n.right = dfs(mid + 1, r);
            return n;
        }
    }

    // O(n) time, O(n) space. Iterative with stack.
    static class Solution2 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) return null;
            ArrayDeque<TreeNode> stack = new ArrayDeque<>();
            TreeNode root = new TreeNode(preorder[0]);
            stack.push(root);
            int inIdx = 0;
            for (int i = 1; i < preorder.length; i++) {
                TreeNode node = new TreeNode(preorder[i]);
                if (stack.peek().val != inorder[inIdx]) {
                    stack.peek().left = node;
                } else {
                    TreeNode parent = null;
                    while (!stack.isEmpty() && stack.peek().val == inorder[inIdx]) {
                        parent = stack.pop();
                        inIdx++;
                    }
                    parent.right = node;
                }
                stack.push(node);
            }
            return root;
        }
    }
}
