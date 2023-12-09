package tree;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 366, LintCode 650, medium, tags: tree, dfs; companies: google.
 * <p>
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 * <p>
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter
 * the order on which elements are returned.
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 */
public class FindLeavesBT {
    // solution 1, dfs, LintCode 3195ms, 15.94Mb. n time, height recursive stack space.
    public List<List<Integer>> findLeavesDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private int dfs(List<List<Integer>> res, TreeNode root) {
        // returns depth from the bottom
        if (root == null) return -1; // note: +1==0, mapping to list index
        int lLevel = dfs(res, root.left);
        int rLevel = dfs(res, root.right);
        int level = Math.max(lLevel, rLevel) + 1; // level from bottom, 0 means the lowest level
        if (res.size() <= level) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        return level;
    }

}
