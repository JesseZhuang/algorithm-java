package tree;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
public class BTZigZag {
    // 0ms, 41.5Mb. O(N) time and space (result and recursive stack).
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode cur, List<List<Integer>> res, int level) {
        if (cur == null) return;
        if (res.size() <= level) res.add(new LinkedList<>()); // important: linked list
        List<Integer> curLevel = res.get(level);
        if (level % 2 == 0) curLevel.add(cur.val);
        else curLevel.add(0, cur.val);
        dfs(cur.left, res, level + 1);
        dfs(cur.right, res, level + 1);
    }

}
