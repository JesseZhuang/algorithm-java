package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 230, medium, tags: tree, dfs, bfs, binary tree.
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the
 * values of the nodes in the tree.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/01/28/kthtree1.jpg
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Example 2: https://assets.leetcode.com/uploads/2021/01/28/kthtree2.jpg
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is n. tree height is h, worst case n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the
 * kth smallest frequently, how would you optimize?
 * <p>
 * Similar to LRU Cache question 146, doubly linked list. O(h) time for insert, O(1, add map?) or O(h) delete,
 * O(k) for searching the kth smallest O(1) if use another arraylist as an copy but insert and delete would be slow.
 * Hints:
 * <p>
 * Try to utilize the property of a BST.
 * Try in-order traversal. (Credits to @chan13)
 * What if you could modify the BST node's structure?
 * The optimal runtime complexity is O(height of BST).
 */
public class KSmallBST {

    // 0ms, 42.4 Mb. Iterative. O(h+k) time, O(h) recursion stack space.
    public int kthSmallestI1(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (true) { // condition is important to compile
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
}
