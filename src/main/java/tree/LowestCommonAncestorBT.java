package tree;

import struct.BTNode;

/**
 * LeetCode 1650, LintCode 474, medium, tags: binary tree, hash table.
 * <p>
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * <p>
 * Each node will have a reference to its parent node. The definition for Node is below:
 * <p>
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor of two nodes p and q in a tree T is
 * the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Example 1:
 * <p>
 * Image text
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * <p>
 * Output: 3
 * <p>
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * <p>
 * Example 2:
 * <p>
 * Image text
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * <p>
 * Output: 5
 * <p>
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * <p>
 * Output: Ï1
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 */
@SuppressWarnings("unused")
public class LowestCommonAncestorBT {
    // solution 1, two pointer, O(h) time, O(1) space. h:tree height, worst case O(n). LintCode 2550ms, 21.95Mb.
    static class Solution {
        public BTNode lowestCommonAncestor(BTNode p, BTNode q) {
            BTNode a = p, b = q;
            while (a != b) {
                a = a.parent == null ? q : a.parent;
                b = b.parent == null ? p : b.parent;
            }
            return a;
        }
    }
}
