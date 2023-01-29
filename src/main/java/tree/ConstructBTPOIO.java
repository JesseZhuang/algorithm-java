package tree;

import struct.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 105, medium, tags: array, hash table, divide and conquer, tree, binary tree.
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/02/19/tree.jpg
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class ConstructBTPOIO {
    int rootId;
    Map<Integer, Integer> valIndex;

    // 2ms, 41.7Mb, recursive. O(n) time and space.
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        valIndex = new HashMap<>();
        rootId = 0;
        for (int i = 0; i < inorder.length; i++) valIndex.put(inorder[i], i);
        return buildTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int left, int right) {
        if (left > right) return null;
        int val = preorder[rootId++];
        TreeNode n = new TreeNode(val);
        n.left = buildTree(preorder, left, valIndex.get(val) - 1);
        n.right = buildTree(preorder, valIndex.get(val) + 1, right);
        return n;
    }

    // 3ms, 41.9Mb. iterative. O(n) time and space.
    public TreeNode buildTreeI(int[] preorder, int[] inorder) {// [3,9,1,2,20,15,7], [1,9,2,3,15,20,7]
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        Stack<TreeNode> stack = new Stack<>();
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            value = preorder[i];
            TreeNode node = new TreeNode(value);
            if (map.get(value) < map.get(stack.peek().val)) { // left child, e.g., 3.left = 9
                stack.peek().left = node;
            } else {// find parent, e.g., 2 -> 9.right (stack {3,9,1}, pop 1,9); 20 -> 3.right (pop 3)
                TreeNode parent = null;
                while (!stack.isEmpty() && map.get(value) > map.get(stack.peek().val)) parent = stack.pop();
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }

    int in, pre;

    public TreeNode buildTreeR(int[] preorder, int[] inorder) { // {3,9,1,2,20,15,7}, {1,9,2,3,15,20,7}
        in = 0;
        pre = 0;
        return build(preorder, inorder, null);
    }

    // 1ms, 41.7Mb
    public TreeNode build(int[] preorder, int[] inorder, Integer stop) {
        if (pre >= preorder.length) return null; // must include equal
        if (Integer.valueOf(inorder[in]).equals(stop)) { // build 3,9,1, inorder[0] == 1, will stop 1.left = null
            in++;
            return null;
        }
        TreeNode n = new TreeNode(preorder[pre++]);
        n.left = build(preorder, inorder, n.val);
        n.right = build(preorder, inorder, stop);
        return n;
    }

    public static void main(String[] args) {
        ConstructBTPOIO tbt = new ConstructBTPOIO();
        System.out.println(tbt.buildTreeR(new int[]{3, 9, 1, 2, 20, 15, 7}, new int[]{1, 9, 2, 3, 15, 20, 7}));
    }
}
