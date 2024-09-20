package tree;

import string.KMP1D;
import struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 572, easy, tags: tree, dfs, string matching, binary tree, hash function.
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same
 * structure and node values of subRoot and false otherwise.
 * <p>
 * A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants. The
 * tree could also be considered as a subtree of itself.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/04/28/subtree1-tree.jpg
 * <p>
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 * Example 2: https://assets.leetcode.com/uploads/2021/04/28/subtree2-tree.jpg
 * <p>
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the root tree is in the range [1, 2000]. n
 * The number of nodes in the subRoot tree is in the range [1, 1000]. m
 * -10^4 <= root.val <= 10^4
 * -10^4 <= subRoot.val <= 10^4
 * <p>
 * Hints:
 * <ul>
 * <li>Which approach is better here-recursive or iterative?
 * <li>If recursive approach is better, can you write recursive function with its parameters?
 * <li>Two trees s and t are said to be identical if their root values are same and their left and right subtrees
 * are identical. Can you write this in form of recursive formulae?
 * <li>Recursive formulae can be: isIdentical(s,t)= s.val==t.val AND isIdentical(s.left,t.left)
 * AND isIdentical(s.right,t.right)
 * </ul>
 */
@SuppressWarnings("unused")
public class Subtree {
    // solution 1, 2ms, 42 Mb. recursive. O(m+n) time and stack space.
    static class Solution1 {

        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (root == null) return false; // avoid NPE, clarification q.
            return identical(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        private boolean identical(TreeNode a, TreeNode b) {
            if (a == null || b == null) return a == b;
            return a.val == b.val && identical(a.left, b.left) && identical(a.right, b.right);
        }
    }

    // solution 2, 5ms, 42.3Mb. O(m+n) time and space.
    static class Solution2 {
        static final String COMMA = ",";
        static final String NULL_NODE = "#";

        public boolean isSubtreeString(TreeNode root, TreeNode subRoot) {
            StringBuilder sb = new StringBuilder(), sb2 = new StringBuilder();
            preOrderHelper(root, sb);
            preOrderHelper(subRoot, sb2);
            KMP1D kmp = new KMP1D(sb2.toString());
            return kmp.inHaystack(sb.toString());
        }

        private void preOrderHelper(TreeNode node, StringBuilder sb) {
            if (node == null) sb.append(NULL_NODE);
            else {
                sb.append(COMMA).append(node.val).append(COMMA); // without first comma fails for [12], [2]
                preOrderHelper(node.left, sb);
                sb.append(COMMA);
                preOrderHelper(node.right, sb);
            }
        }
    }

    static class Solution3 {
        final int MOD_1 = 1000000007; //BigInteger.probablePrime();
        final int MOD_2 = 2147483647;
        List<long[]> memo = new ArrayList<>();

        long[] hashSubtreeAtNode(TreeNode node, boolean needToAdd) {
            if (node == null) return new long[]{3, 7};
            long[] left = hashSubtreeAtNode(node.left, needToAdd);
            long[] right = hashSubtreeAtNode(node.right, needToAdd);
            long left1 = (left[0] << 5) % MOD_1; // avoid overflow
            long right1 = (right[0] << 1) % MOD_1;
            long left2 = (left[1] << 7) % MOD_2;
            long right2 = (right[1] << 1) % MOD_2;
            long[] hashPair = {(left1 + right1 + node.val) % MOD_1,
                    (left2 + right2 + node.val) % MOD_2};
            if (needToAdd) memo.add(hashPair);
            return hashPair;
        }

        // 7ms, 49.4 Mb. hashing. can add check collision with identical() for O(m)
        public boolean isSubtreeHash(TreeNode root, TreeNode subRoot) {
            hashSubtreeAtNode(root, true); // hashing and memorize hash of all nodes in tree
            long[] s = hashSubtreeAtNode(subRoot, false);
            for (long[] m : memo) if (m[0] == s[0] && m[1] == s[1]) return true;
            return false;
        }
    }
}
