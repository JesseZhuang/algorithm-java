package tree;

import struct.TreeNode;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode 662, medium, tags: binary tree, dfs, bfs,
 * Given the root of a binary tree, return the maximum width of the given tree.
 * <p>
 * The maximum width of a tree is the maximum width among all levels.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
 * <p>
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/05/03/width1-tree.jpg
 * <p>
 * <p>
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 * Example 2: https://assets.leetcode.com/uploads/2022/03/14/maximum-width-of-binary-tree-v3.jpg
 * <p>
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 * Example 3: https://assets.leetcode.com/uploads/2021/05/03/width3-tree.jpg
 * <p>
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3000].
 * -100 <= Node.val <= 100
 */
public class MaxWidthBT {
    int res;
    Map<Integer, Integer> lLeft; // level->left most node position

    // solution 1, bfs, O(n) time and space, 2ms, 43.01Mb. One calculation of res per layer.
    public int widthOfBinaryTreeBfs(TreeNode root) {
        int res = 0;
        Queue<Map.Entry<TreeNode, Integer>> q = new ArrayDeque<>(); // map: node->position
        q.add(new AbstractMap.SimpleEntry<>(root, 0));
        while (!q.isEmpty()) {
            int l = q.peek().getValue(), r = l, s = q.size();
            for (int i = 0; i < s; i++) { // important to get size before the for loop, since q size is changing
                Map.Entry<TreeNode, Integer> e = q.remove();
                TreeNode n = e.getKey();
                r = e.getValue();
                if (n.left != null) q.add(new AbstractMap.SimpleEntry<>(n.left, 2 * r));
                if (n.right != null) q.add(new AbstractMap.SimpleEntry<>(n.right, 2 * r + 1));
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    // solution 2, dfs, O(n) time and space. 3ms, 44.36Mb.
    public int widthOfBinaryTreeDfs(TreeNode root) {
        res = 0;
        lLeft = new HashMap<>();
        dfs(root, 0, 0);
        return res;
    }

    void dfs(TreeNode n, int d, int p) { // depth, position
        if (n == null) return;
        lLeft.putIfAbsent(d, p);
        res = Math.max(res, p - lLeft.get(d) + 1);
        dfs(n.left, d + 1, 2 * p);
        dfs(n.right, d + 1, 2 * p + 1);
    }

}
