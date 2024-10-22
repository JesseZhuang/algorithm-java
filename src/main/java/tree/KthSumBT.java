package tree;

import struct.TreeNode;

import java.util.LinkedList;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 2583, medium, tags: tree, bfs, sorting, binary tree.
 * <p>
 * You are given the root of a binary tree and a positive integer k.
 * <p>
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 * <p>
 * Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
 * <p>
 * Note that two nodes are on the same level if they have the same distance from the root.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,8,9,2,1,3,7,4,6], k = 2
 * Output: 13
 * Explanation: The level sums are the following:
 * - Level 1: 5.
 * - Level 2: 8 + 9 = 17.
 * - Level 3: 2 + 1 + 3 + 7 = 13.
 * - Level 4: 4 + 6 = 10.
 * The 2nd largest level sum is 13.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,null,3], k = 1
 * Output: 3
 * Explanation: The largest level sum is 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is n.
 * 2 <= n <= 10^5
 * 1 <= Node.val <= 10^6
 * 1 <= k <= n
 * <p>
 * Hint 1
 * Find the sum of values of nodes on each level and return the kth largest one.
 * Hint 2
 * To find the sum of the values of nodes on each level, you can use a DFS or BFS algorithm to traverse the tree and
 * keep track of the level of each node.
 */
@SuppressWarnings("unused")
public class KthSumBT {
    // bfs+min heap, lgN*lgk+n, n.
    static class Solution {
        public long kthLargestLevelSum(TreeNode root, int k) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int size = q.size();
                long sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode n = q.remove();
                    sum += n.val;
                    if (n.left != null) q.add(n.left);
                    if (n.right != null) q.add(n.right);
                }
                pq.add(sum);
                if (pq.size() > k) pq.remove();
            }
            if (pq.size() < k) return -1;
            return Optional.ofNullable(pq.peek()).orElseThrow();
        }
    }

}
