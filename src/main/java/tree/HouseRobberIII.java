package tree;

import struct.TreeNode;

/**
 * LeetCode 337, medium, tags: tree, binary tree, dfs, dynamic programming.
 * <p>
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
 * called root. Besides the root, each house has one and only one parent house. After a tour, the smart thief
 * realized that all houses in this place form a binary tree. It will automatically contact the police if two
 * directly-linked houses were broken into on the same night.
 * <p>
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting
 * the police.
 * <p>
 * Example 1:
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * <p>
 * Example 2:
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^4
 */
public final class HouseRobberIII {

    private HouseRobberIII() {
    }

    /**
     * Post-order DFS returning pair (rob_this, skip_this). O(n) time, O(h) space.
     */
    public static int rob(TreeNode root) {
        int[] result = dfs(root); // result[0] = rob root, result[1] = skip root
        return Math.max(result[0], result[1]);
    }

    /**
     * Returns int[2]: [rob_this_node, skip_this_node]. O(n) time visiting each node once.
     */
    private static int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0}; // base case, O(1)
        int[] left = dfs(node.left);   // O(left subtree) time
        int[] right = dfs(node.right); // O(right subtree) time
        int robThis = node.val + left[1] + right[1]; // rob this node + skip both children
        int skipThis = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // skip this, best of each child
        return new int[]{robThis, skipThis}; // O(1) space per frame
    }
}
