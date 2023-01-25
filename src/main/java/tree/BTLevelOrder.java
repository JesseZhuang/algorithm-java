package tree;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 102, medium, tags: tree, binary tree, bfs.
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class BTLevelOrder {
    // 1ms, 42.6 Mb. BFS. O(n) time, O(max-leaves, worst case n/2) space.
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return result;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.remove();
                level.add(n.val);
                if (n.left != null) queue.add(n.left);
                if (n.right != null) queue.add(n.right);
            }
            result.add(level);
        }
        return result;
    }

    // 0ms, 42.1 Mb. O(n) time, O(height, worst case n) space.
    public List<List<Integer>> levelOrderR(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelHelper(result, root, 0);
        return result;
    }

    private void levelHelper(List<List<Integer>> levelOrder, TreeNode n, int level) {
        if (n == null) return;
        if (level == levelOrder.size()) levelOrder.add(new ArrayList<>());
        levelOrder.get(level).add(n.val);
        levelHelper(levelOrder, n.left, level + 1);
        levelHelper(levelOrder, n.right, level + 1);
    }
}
