package tree;

import struct.TreeNode;
import util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode 314, LintCode 651, medium, tags: tree, bfs, dfs, hash table.
 * Companies: facebook.
 * <p>
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values.
 * (i.e., from top to bottom, column by column).
 * <p>
 * If two nodes are in the same row and column, the order should be from left to right.
 * <p>
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1)
 * and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * <p>
 * Example 1:
 * <pre>
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 * </pre>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * Example 3:
 * <p>
 * <pre>
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 * </pre>
 * <p>
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
@SuppressWarnings("unused")
public class BTVerticalOrder {

    // solution 1, hashmap, n time, n space. LintCode 233ms, 20.17Mb.
    // another, treemap, nlgn time, n space.
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<Pair<TreeNode, Integer>> q = new ArrayDeque<>(); // bfs
        q.offer(new Pair<>(root, 0));
        HashMap<Integer, List<Integer>> colNodes = new HashMap<>(); // column -> nodes
        int minC = 0, maxC = 0;
        while (!q.isEmpty()) {
            Pair<TreeNode, Integer> p = q.remove();
            root = p.getKey();
            int col = p.getValue();
            colNodes.computeIfAbsent(col, c -> new ArrayList<>()).add(root.val);
            if (root.left != null) q.add(new Pair<>(root.left, col - 1));
            if (root.right != null) q.offer(new Pair<>(root.right, col + 1));
            if (minC > col) minC = col;
            if (maxC < col) maxC = col;
        }
        for (int c = minC; c <= maxC; c++) res.add(colNodes.get(c));
        return res;
    }
}
