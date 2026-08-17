package tree;

import struct.TreeNode;

import java.util.*;

/**
 * LeetCode 2385. Amount of Time for Binary Tree to Be Infected.
 * <p>
 * Given the root of a binary tree with unique node values and an integer start,
 * return the number of minutes needed for the entire tree to be infected.
 * Each minute, uninfected nodes adjacent (parent or child) to an infected node become infected.
 */
public final class AmountOfTimeInfected {

    private AmountOfTimeInfected() {
    }

    /**
     * BFS approach: build a parent map via DFS, then BFS from the start node.
     * Time: O(n), Space: O(n).
     */
    public static int amountOfTime(TreeNode root, int start) {
        // Build parent map and locate start node
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // O(n) DFS to build parent pointers and node lookup
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            nodeMap.put(node.val, node);
            if (node.right != null) {
                parentMap.put(node.right.val, node);
                stack.push(node.right);
            }
            if (node.left != null) {
                parentMap.put(node.left.val, node);
                stack.push(node.left);
            }
        }

        // BFS from start node; O(n) time, O(n) space for visited set and queue
        TreeNode startNode = nodeMap.get(start);
        Set<Integer> visited = new HashSet<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(startNode);
        visited.add(start);
        int minutes = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            // Process all nodes at current distance
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null && visited.add(cur.left.val)) {
                    queue.add(cur.left);
                }
                if (cur.right != null && visited.add(cur.right.val)) {
                    queue.add(cur.right);
                }
                TreeNode parent = parentMap.get(cur.val);
                if (parent != null && visited.add(parent.val)) {
                    queue.add(parent);
                }
            }
        }
        return minutes;
    }

    /**
     * Pure DFS approach: encode distance as negative return value.
     * Time: O(n), Space: O(h) where h is tree height (recursion stack).
     * <p>
     * depth(node) returns:
     * - positive: normal height of the subtree
     * - negative: -(distance from start to node + 1), indicating start is in this subtree
     */
    private static int ans;

    public static int amountOfTimeDfs(TreeNode root, int start) {
        ans = 0;
        depth(root, start);
        return ans;
    }

    private static int depth(TreeNode node, int start) {
        if (node == null) return 0;
        int left = depth(node.left, start);
        int right = depth(node.right, start);

        if (node.val == start) {
            // Downward distance is max of left/right subtree heights
            ans = Math.max(ans, Math.max(left, right));
            return -1; // distance from start to itself is 0, encode as -1
        }

        if (left < 0) {
            // Start is in left subtree; candidate answer is right height + distance up
            // O(1) update of global answer
            ans = Math.max(ans, right - left);
            return left - 1; // propagate distance upward
        }

        if (right < 0) {
            // Start is in right subtree; candidate answer is left height + distance up
            ans = Math.max(ans, left - right);
            return right - 1; // propagate distance upward
        }

        // Normal case: return height of subtree
        return 1 + Math.max(left, right);
    }
}
