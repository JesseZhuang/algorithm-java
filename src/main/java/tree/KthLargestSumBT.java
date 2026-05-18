package tree;

import struct.TreeNode;

import java.util.*;

/**
 * LeetCode 2583, medium, tags: tree, binary tree, bfs, sorting.
 * <p>
 * You are given the root of a binary tree and a positive integer k.
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 * Return the kth largest level sum in the tree (1-indexed). If there are fewer than k levels
 * in the tree, return -1.
 * <p>
 * Example 1:
 * Input: root = [5,8,9,2,1,3,7,4,6], k = 2
 * Output: 13
 * <p>
 * Example 2:
 * Input: root = [1,2,null,3], k = 1
 * Output: 3
 * <p>
 * Constraints:
 * n == number of nodes in the tree
 * 2 <= n <= 10^5
 * 1 <= Node.val <= 10^6
 * 1 <= k <= n
 */
public final class KthLargestSumBT {

    private KthLargestSumBT() {
    }

    /**
     * Solution 1: BFS level-order traversal + sort.
     * O(n) time for BFS, O(d log d) for sorting where d = tree depth.
     * O(n) space for the queue.
     */
    public static long kthLargestLevelSumSort(TreeNode root, int k) {
        if (root == null) return -1;
        List<Long> levelSums = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) { // O(n) total across all levels
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            levelSums.add(sum);
        }
        if (levelSums.size() < k) return -1;
        levelSums.sort(Collections.reverseOrder()); // O(d log d) where d = depth
        return levelSums.get(k - 1);
    }

    /**
     * Solution 2: BFS level-order traversal + min-heap of size k.
     * O(n) time for BFS, O(d log k) for heap operations where d = tree depth.
     * O(k) space for the heap (plus O(n) for the queue).
     */
    public static long kthLargestLevelSumHeap(TreeNode root, int k) {
        if (root == null) return -1;
        PriorityQueue<Long> minHeap = new PriorityQueue<>(); // O(k) space
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) { // O(n) total across all levels
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            minHeap.add(sum); // O(log k) per level
            if (minHeap.size() > k) minHeap.poll(); // maintain size k
            depth++;
        }
        if (depth < k) return -1;
        return minHeap.peek(); // top of min-heap is kth largest
    }
}
