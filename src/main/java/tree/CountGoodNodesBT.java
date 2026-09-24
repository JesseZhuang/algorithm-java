package tree;

import struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1448, medium, tags: tree, binary tree, dfs, bfs.
 * <p>
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X
 * there are no nodes with a value greater than X.
 * Return the number of good nodes in the binary tree.
 * <p>
 * Example 1:
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path.
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * <p>
 * Example 2:
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered good.
 * <p>
 * Constraints:
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 */
public final class CountGoodNodesBT {

    private CountGoodNodesBT() {
    }

    /**
     * Solution 1: DFS recursive tracking max from root. O(n) time, O(h) space.
     */
    public static int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private static int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        int count = node.val >= maxSoFar ? 1 : 0;
        int newMax = Math.max(maxSoFar, node.val);
        count += dfs(node.left, newMax);
        count += dfs(node.right, newMax);
        return count;
    }

    /**
     * Solution 2: BFS iterative with queue of (node, maxSoFar). O(n) time, O(w) space where w is max width.
     */
    public static int goodNodes2(TreeNode root) {
        if (root == null) return 0;
        int count = 0;
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, root.val});
        while (!queue.isEmpty()) {
            Object[] pair = queue.poll();
            TreeNode node = (TreeNode) pair[0];
            int maxSoFar = (int) pair[1];
            if (node.val >= maxSoFar) count++;
            int newMax = Math.max(maxSoFar, node.val);
            if (node.left != null) queue.offer(new Object[]{node.left, newMax});
            if (node.right != null) queue.offer(new Object[]{node.right, newMax});
        }
        return count;
    }
}
