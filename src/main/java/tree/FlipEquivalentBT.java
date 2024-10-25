package tree;

import struct.TreeNode;

import java.util.Stack;

/**
 * LeetCode 951, medium, tags: binary tree, dfs, tree.
 * <p>
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right
 * child subtrees.
 * <p>
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number
 * of flip operations.
 * <p>
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false
 * otherwise.
 * <p>
 * Example 1:
 * <p>
 * Flipped Trees Diagram
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * Example 2:
 * <p>
 * Input: root1 = [], root2 = []
 * Output: true
 * Example 3:
 * <p>
 * Input: root1 = [], root2 = [1]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each tree is in the range [0, 100].
 * Each tree will have unique node values in the range [0, 99].
 */
@SuppressWarnings("unused")
public class FlipEquivalentBT {

    // recursive, n,n.
    static class Solution1 {
        public boolean flipEquiv(TreeNode r1, TreeNode r2) {
            if (r1 == null || r2 == null) return r1 == r2;
            return r1.val == r2.val &&
                    ((flipEquiv(r1.left, r2.left) && flipEquiv(r1.right, r2.right)) ||
                            (flipEquiv(r1.left, r2.right) && flipEquiv(r1.right, r2.left)));
        }
    }

    // iterative dfs, n, n
    static class Solution3 {
        // Checks whether the pair of nodes should be examined
        public boolean check(TreeNode node1, TreeNode node2) {
            if (node1 == null && node2 == null) return true;
            return node1 != null && node2 != null && node1.val == node2.val;
        }

        public boolean flipEquiv(TreeNode root1, TreeNode root2) {
            Stack<TreeNode[]> st = new Stack<>();
            st.push(new TreeNode[]{root1, root2});
            while (!st.isEmpty()) {
                TreeNode[] current = st.pop();
                TreeNode node1 = current[0], node2 = current[1];
                if (node1 == null && node2 == null) continue;
                if (node1 == null || node2 == null) return false;
                if (node1.val != node2.val) return false;

                // Check both configurations: no swap and swap
                if (check(node1.left, node2.left) && check(node1.right, node2.right)) {
                    st.push(new TreeNode[]{node1.left, node2.left});
                    st.push(new TreeNode[]{node1.right, node2.right});
                } else if (check(node1.left, node2.right) && check(node1.right, node2.left)) {
                    st.push(new TreeNode[]{node1.left, node2.right});
                    st.push(new TreeNode[]{node1.right, node2.left});
                } else return false;
            }
            return true;
        }
    }

    // canonical form, n. n
    static class Solution4 {
        public void findCanonicalForm(TreeNode root) {
            if (root == null) return;
            // post-order traversal: first bring subtrees into their canonical form
            findCanonicalForm(root.left);
            findCanonicalForm(root.right);
            if (root.right == null) return;
            // Swap subtrees, so that left is non-empty
            if (root.left == null) {
                root.left = root.right;
                root.right = null;
                return;
            }
            TreeNode left = root.left;
            TreeNode right = root.right;
            // make left val <= right val
            if (left.val > right.val) {
                root.left = right;
                root.right = left;
            }
        }

        public boolean equivalent(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null) return true;
            if (root1 == null || root2 == null) return false;
            if (root1.val != root2.val) return false;
            return (equivalent(root1.left, root2.left) && equivalent(root1.right, root2.right));
        }

        public boolean flipEquiv(TreeNode root1, TreeNode root2) {
            findCanonicalForm(root1);
            findCanonicalForm(root2);
            return equivalent(root1, root2);
        }
    }

}
