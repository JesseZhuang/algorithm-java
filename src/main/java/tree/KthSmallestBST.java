package tree;

import struct.TreeNode;

import java.util.ArrayDeque;

/**
 * LeetCode 230, medium, tags: binary tree, bst, dfs, binary search.
 * <p>
 * Given the root of a binary search tree, and an integer k, return the kth smallest value
 * (1-indexed) of all the values of the nodes in the tree.
 * <p>
 * Example 1:
 * <pre>
 *     3
 *    / \
 *   1   4
 *    \
 *     2
 * </pre>
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * <p>
 * Example 2:
 * <pre>
 *         5
 *        / \
 *       3   6
 *      / \
 *     2   4
 *    /
 *   1
 * </pre>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * <p>
 * Constraints:
 * <ul>
 * <li>The number of nodes in the tree is n.</li>
 * <li>1 <= k <= n <= 10^4</li>
 * <li>0 <= Node.val <= 10^4</li>
 * </ul>
 */
public final class KthSmallestBST {
    private KthSmallestBST() {
    }

    /**
     * Iterative inorder traversal with early termination. O(H+k) time, O(H) space.
     */
    public static int kthSmallest(TreeNode root, int k) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (--k == 0) return cur.val;
            cur = cur.right;
        }
        throw new IllegalArgumentException("k is larger than the number of nodes");
    }
}
