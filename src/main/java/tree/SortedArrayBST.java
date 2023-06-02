package tree;

import struct.TreeNode;

/**
 * LeetCode 108, easy, tags: array, tree, binary search tree, binary tree.
 * <p>
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg
 * <p>
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * https://assets.leetcode.com/uploads/2021/02/18/btree2.jpg
 * <p>
 * Example 2: https://assets.leetcode.com/uploads/2021/02/18/btree.jpg
 * <p>
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in a strictly increasing order.
 */
public class SortedArrayBST {

    // 0ms, 43.2 Mb. O(n) time, O(n) result space, O(log n) stack space.
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, left, mid - 1);
        node.right = helper(nums, mid + 1, right);
        return node;
    }
}
