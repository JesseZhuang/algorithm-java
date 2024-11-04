package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 103, medium, tags: tree, bfs, binary tree.
 * <p>
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
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
 * -100 <= Node.val <= 100
 */
@SuppressWarnings("unused")
public class BTZigZag {

    // 0ms, 41.5Mb. O(N) time and space (result and recursive stack).
    static class Solution1 {
        public List<List<Integer>> zigzagLevelOrderDfs(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, res, 0);
            return res;
        }

        private void dfs(TreeNode cur, List<List<Integer>> res, int level) {
            if (cur == null) return;
            if (res.size() <= level) res.add(new LinkedList<>()); // important: linked list
            List<Integer> curLevel = res.get(level);
            if (level % 2 == 0) curLevel.add(cur.val);
            else curLevel.addFirst(cur.val);
            dfs(cur.left, res, level + 1);
            dfs(cur.right, res, level + 1);
        }
    }

    // 1ms, 41.5Mb. BFS. O(N) time and O(maxCount of one level, do not consider res) space.
    static class Solution2 {
        public List<List<Integer>> zigzagLevelOrderBfs(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Queue<TreeNode> q = new ArrayDeque<>(5);
            if (root != null) q.add(root);
            boolean leftToRight = true;
            while (!q.isEmpty()) {
                int size = q.size();
                LinkedList<Integer> curLevel = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.remove();
                    if (leftToRight) curLevel.addLast(cur.val);
                    else curLevel.addFirst(cur.val);
                    if (cur.left != null) q.add(cur.left);
                    if (cur.right != null) q.add(cur.right);
                }
                leftToRight = !leftToRight;
                res.add(curLevel);
            }
            return res;
        }
    }

}
