package tree;

import struct.TreeNode;

import java.util.LinkedList;

/**
 * LeetCode 100, easy, tags: tree, dfs, bfs, binary tree.
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg
 * <p>
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2: https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg
 * <p>
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3: https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg
 * <p>
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
 */
@SuppressWarnings("unused")
public class SameTree {
    public static void main(String[] args) {
        TreeNode p = TreeNode.constructTreeNaturalNumbers(2);
        TreeNode q = TreeNode.constructTreeNaturalNumbers(2);
        System.out.println(new SameTree().isSameTreeBFS(p, q));
    }

    // 0ms, 41.3 Mb. O(N) time and O(h) space, recursive.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 1ms, 42 Mb. BFS, O(N) time and space.
    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> deqP = new LinkedList<>(); // ArrayDeque does not allow null
        LinkedList<TreeNode> deqQ = new LinkedList<>();
        deqP.add(p);
        deqQ.add(q);
        while (!deqP.isEmpty()) {
            TreeNode node1 = deqP.remove(), node2 = deqQ.remove();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            else {
                if (node1.val != node2.val) return false;
                deqP.add(node1.left);
                deqP.add(node1.right);
                deqQ.add(node2.left);
                deqQ.add(node2.right);
            }
        }
        return true;
    }
}
