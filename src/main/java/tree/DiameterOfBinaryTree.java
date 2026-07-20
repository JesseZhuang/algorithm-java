package tree;

import struct.TreeNode;

/**
 * LeetCode 543, easy, tags: tree, binary tree, dfs.
 * <p>
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes
 * in a tree. This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -100 <= Node.val <= 100
 */
public final class DiameterOfBinaryTree {

    private DiameterOfBinaryTree() {
    }

    /**
     * DFS post-order traversal. O(n) time, O(h) space where h is tree height.
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter = new int[1]; // O(1) space for global max
        depth(root, maxDiameter);
        return maxDiameter[0];
    }

    /**
     * Returns depth of subtree rooted at node. O(n) time visiting each node once.
     */
    private static int depth(TreeNode node, int[] maxDiameter) {
        if (node == null) return 0; // base case
        int left = depth(node.left, maxDiameter); // O(left subtree) time
        int right = depth(node.right, maxDiameter); // O(right subtree) time
        maxDiameter[0] = Math.max(maxDiameter[0], left + right); // diameter at this node
        return 1 + Math.max(left, right); // depth to parent, O(1)
    }
}
