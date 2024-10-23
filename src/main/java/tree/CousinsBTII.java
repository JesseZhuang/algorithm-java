package tree;

import struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 2641, medium, tags:
 * <p>
 * Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
 * <p>
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 * <p>
 * Return the root of the modified tree.
 * <p>
 * Note that the depth of a node is the number of edges in the path from the root node to it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,4,9,1,10,null,7]
 * Output: [0,0,0,7,7,null,11]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of
 * each node.
 * - Node with value 5 does not have any cousins so its sum is 0.
 * - Node with value 4 does not have any cousins so its sum is 0.
 * - Node with value 9 does not have any cousins so its sum is 0.
 * - Node with value 1 has a cousin with value 7 so its sum is 7.
 * - Node with value 10 has a cousin with value 7 so its sum is 7.
 * - Node with value 7 has cousins with values 1 and 10 so its sum is 11.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,1,2]
 * Output: [0,0,0]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of
 * each node.
 * - Node with value 3 does not have any cousins so its sum is 0.
 * - Node with value 1 does not have any cousins so its sum is 0.
 * - Node with value 2 does not have any cousins so its sum is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^4
 * <p>
 * Hint 1
 * Use DFS two times.
 * Hint 2
 * For the first time, find the sum of values of all the levels of the binary tree.
 * Hint 3
 * For the second time, update the value of the node with the sum of the values of the current level - sibling node’s values.
 */
@SuppressWarnings("unused")
public class CousinsBTII {
    // single pass BFS, n, n/2 (max number of nodes in a level).
    static class Solution {
        public TreeNode replaceValueInTree(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int pls = root.val; // previous level sum
            while (!q.isEmpty()) {
                int size = q.size();
                int cls = 0; // cur level sum
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.remove();
                    cur.val = pls - cur.val;
                    int childrenSum = (cur.left != null ? cur.left.val : 0) +
                            (cur.right != null ? cur.right.val : 0);
                    if (cur.left != null) {
                        cls += cur.left.val; // Accumulate current level sum
                        cur.left.val = childrenSum; // Update left child's value
                        q.offer(cur.left); // Add to queue for next level
                    }
                    if (cur.right != null) {
                        cls += cur.right.val;
                        cur.right.val = childrenSum;
                        q.offer(cur.right);
                    }
                }
                pls = cls; // Update previous level sum for next iteration
            }
            return root;
        }
    }

    // two pass dfs, n, h (max n, min lgn).
    static class Solution2 {
        private int[] levelSums = new int[100000];

        public TreeNode replaceValueInTree(TreeNode root) {
            levelSum(root, 0);
            updateSum(root, 0, 0);
            return root;
        }

        private void levelSum(TreeNode node, int level) {
            if (node == null) return;
            levelSums[level] += node.val;
            levelSum(node.left, level + 1);
            levelSum(node.right, level + 1);
        }

        private void updateSum(TreeNode node, int sibling, int level) {
            if (node == null) return;
            int left = (node.left == null) ? 0 : node.left.val;
            int right = (node.right == null) ? 0 : node.right.val;
            if (level == 0 || level == 1) node.val = 0;
            else node.val = levelSums[level] - node.val - sibling;
            updateSum(node.left, right, level + 1);
            updateSum(node.right, left, level + 1);
        }
    }
}
