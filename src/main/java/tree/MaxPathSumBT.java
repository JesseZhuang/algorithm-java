package tree;

import struct.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LeetCode 124. Hard. Tags: Tree, Depth-first Search, dynamic programming, binary tree.
 * <p>
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
 * along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/10/13/exx1.jpg
 * <p>
 * Input: [1,2,3]
 * <pre>
 *        1
 *       / \
 *      2   3
 * </pre>
 * Output: 6
 * <p>
 * Example 2: https://assets.leetcode.com/uploads/2020/10/13/exx2.jpg
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
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */
public class MaxPathSumBT {
    int maxSum; // max sum in any path while traversing the tree

    // 1ms, 48.3 Mb. O(n) time, O(height, worst case n) space.
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    /**
     * calculate the max gain from all paths in the tree under a node, so that node must be included.
     * the path including the node need to be simple path, so can only include either left or right branch.
     * while traversing, also update max sum, which is max of all simple paths during traversing, not limited
     * to under this input node.
     *
     * @param node the input tree node.
     * @return the max gain with all simple paths under this tree node.
     */
    private int maxGain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxGain(node.left));
        int right = Math.max(0, maxGain(node.right));
        maxSum = Math.max(maxSum, left + right + node.val); // sum including node, any path, so both branches
        return Math.max(left, right) + node.val;
    }

    // 18ms, 44.9Mb. calculate with post order. O(n) time and space.
    public int maxPathSumIter(TreeNode root) {
        int result = Integer.MIN_VALUE;
        Map<TreeNode, Integer> maxRootPath = new HashMap<>(); // cache
        maxRootPath.put(null, 0); // for simplicity, handle null nodes
        for (TreeNode node : topSort(root)) {
            // as we process nodes in post-order their children are already cached
            int left = Math.max(maxRootPath.get(node.left), 0);
            int right = Math.max(maxRootPath.get(node.right), 0);
            maxRootPath.put(node, Math.max(left, right) + node.val);
            result = Math.max(left + right + node.val, result);
        }
        return result;
    }

    public Iterable<TreeNode> topSort(TreeNode root) {
        Deque<TreeNode> result = new LinkedList<>();
        if (root != null) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                result.push(curr);
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
        return result;
    }
}
