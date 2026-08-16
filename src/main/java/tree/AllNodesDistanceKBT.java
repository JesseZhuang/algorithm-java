package tree;

import struct.TreeNode;

import java.util.*;

/**
 * LeetCode 863, medium, tags: tree, binary tree, bfs, dfs.
 * <p>
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the
 * values of all nodes that have a distance k from the target node.
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) are the nodes with values 7, 4,
 * and 1.
 * <p>
 * Example 2:
 * Input: root = [1], target = 1, k = 3
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 500].
 * 0 <= Node.val <= 500
 * All the values Node.val are unique.
 * target is the value of one of the nodes in the tree.
 * 0 <= k <= 1000
 */
public final class AllNodesDistanceKBT {

    private AllNodesDistanceKBT() {
    }

    /**
     * BFS with parent map. DFS to build parent pointers, then BFS from target for k levels.
     * O(n) time, O(n) space.
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);
        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);
        int dist = 0;
        while (!queue.isEmpty()) {
            if (dist == k) {
                List<Integer> result = new ArrayList<>();
                for (TreeNode node : queue) result.add(node.val);
                return result;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                for (TreeNode neighbor : new TreeNode[]{node.left, node.right, parentMap.get(node)}) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            dist++;
        }
        return new ArrayList<>();
    }

    private static void buildParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) return;
        parentMap.put(node, parent);
        buildParentMap(node.left, node, parentMap);
        buildParentMap(node.right, node, parentMap);
    }

    /**
     * Pure DFS approach. Find target recursively, collect nodes at remaining distance in subtrees.
     * O(n) time, O(n) space.
     */
    public static List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();
        dfs(root, target, k, result);
        return result;
    }

    /**
     * Returns distance from node to target if target is in this subtree, -1 otherwise.
     */
    private static int dfs(TreeNode node, TreeNode target, int k, List<Integer> result) {
        if (node == null) return -1;
        if (node == target) {
            collectSubtree(node, k, result);
            return 0;
        }
        int left = dfs(node.left, target, k, result);
        if (left >= 0) {
            int dist = left + 1;
            if (dist == k) result.add(node.val);
            else if (dist < k) collectSubtree(node.right, k - dist - 1, result);
            return dist;
        }
        int right = dfs(node.right, target, k, result);
        if (right >= 0) {
            int dist = right + 1;
            if (dist == k) result.add(node.val);
            else if (dist < k) collectSubtree(node.left, k - dist - 1, result);
            return dist;
        }
        return -1;
    }

    private static void collectSubtree(TreeNode node, int dist, List<Integer> result) {
        if (node == null || dist < 0) return;
        if (dist == 0) {
            result.add(node.val);
            return;
        }
        collectSubtree(node.left, dist - 1, result);
        collectSubtree(node.right, dist - 1, result);
    }
}
