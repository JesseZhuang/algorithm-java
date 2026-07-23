package tree;

import struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 437 - Path Sum III.
 * <p>
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the
 * sum of the values along the path equals targetSum. The path does not need to start or end at
 * the root or a leaf, but it must go downwards (traveling only from parent to child nodes).
 */
public final class PathSumIII {

    private PathSumIII() {
    }

    /**
     * Solution 1: DFS + prefix sum HashMap.
     * Time: O(n), Space: O(n)
     *
     * @param root      root of the binary tree
     * @param targetSum target path sum
     * @return number of paths that sum to targetSum
     */
    public static int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // empty prefix
        return dfs(root, 0L, targetSum, prefixSumCount);
    }

    private static int dfs(TreeNode node, long currentSum, int target, Map<Long, Integer> prefixSumCount) {
        if (node == null) return 0;

        currentSum += node.val;
        // Number of paths ending at this node with sum == target
        int count = prefixSumCount.getOrDefault(currentSum - target, 0);

        // Add current prefix sum to map
        prefixSumCount.merge(currentSum, 1, Integer::sum);

        // Recurse into children
        count += dfs(node.left, currentSum, target, prefixSumCount);
        count += dfs(node.right, currentSum, target, prefixSumCount);

        // Backtrack: remove current prefix sum
        prefixSumCount.merge(currentSum, -1, Integer::sum);

        return count;
    }

    /**
     * Solution 2: Double DFS brute force.
     * Time: O(n^2), Space: O(n) (recursion stack)
     *
     * @param root      root of the binary tree
     * @param targetSum target path sum
     * @return number of paths that sum to targetSum
     */
    public static int pathSum2(TreeNode root, int targetSum) {
        if (root == null) return 0;
        // Count paths starting from root + paths in left/right subtrees
        return countFrom(root, targetSum, 0L)
                + pathSum2(root.left, targetSum)
                + pathSum2(root.right, targetSum);
    }

    /**
     * Count paths starting from the given node that sum to target.
     */
    private static int countFrom(TreeNode node, int target, long currentSum) {
        if (node == null) return 0;
        currentSum += node.val;
        int count = (currentSum == target) ? 1 : 0;
        count += countFrom(node.left, target, currentSum);
        count += countFrom(node.right, target, currentSum);
        return count;
    }
}
