package tree;

import struct.TreeNode;

/**
 * LeetCode 2415, medium, tags: tree, dfs, bfs, binary tree.
 * <p>
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
 * <p>
 * Return the root of the reversed tree.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [2,3,5,8,13,21,34]
 * Output: [2,5,3,8,13,21,34]
 * <p>
 * Example 2:
 * <p>
 * Input: root = [7,13,11]
 * Output: [7,11,13]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 2^14].
 * 0 <= Node.val <= 10^5
 * root is a perfect binary tree.
 */
@SuppressWarnings("unused")
public final class ReverseOddLevelsBT {
    private ReverseOddLevelsBT() {
    }

    // DFS. O(n) time, O(log n) space.
    static class Solution {
        public TreeNode reverseOddLevels(TreeNode root) {
            dfs(root.left, root.right, 1);
            return root;
        }

        private void dfs(TreeNode l, TreeNode r, int d) {
            if (l == null || r == null) return;
            if (d % 2 == 1) {
                int temp = l.val;
                l.val = r.val;
                r.val = temp;
            }
            dfs(l.left, r.right, d + 1);
            dfs(l.right, r.left, d + 1);
        }
    }
}
