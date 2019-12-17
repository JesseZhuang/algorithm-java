package tree;

import struct.TreeNode;

/**
 * LeetCode 124. Hard. Tags: Tree, Depth-first Search.
 * <p>
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
 * along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * <pre>
 *        1
 *       / \
 *      2   3
 * </pre>
 * Output: 6
 * <p>
 * Example 2:
 * <p>
 * Input: [-10,9,20,null,null,15,7]
 * <pre>
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * Output: 42
 * <p>
 * <b>Summary</b>
 * <ul>
 * <li>recursive, max of left and right, O(height) time, O(height) stack space. 1 ms, 99.73%, 40 Mb, 98.81%.</li>
 * </ul>
 */
public class MaxPathSumBT {
    int maxValue;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        maxValue = Integer.MIN_VALUE;
        System.out.println("tree:\n" + root);
        System.out.println("max including root node: " + maxPathDown(root));
        System.out.println("max sum: " + maxValue);
        System.out.println();
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
