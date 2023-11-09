package struct;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * Confused what "{1,#,2,3}" means? > read more on how binary tree is serialized
 * on OJ (LeetCode online judge).
 * <p>
 * OJ's Binary (search) Tree Serialization: The serialization of a binary tree follows a
 * level order traversal, where '#' signifies a path terminator where no node
 * exists below.
 * <p>
 * Here's an example:
 *
 * <pre>
 *       1
 *      / \
 *     2  3
 *    /
 *   4
 *  / \
 * #   5
 * </pre>
 *
 * <p>
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 * <p>
 * My pre-order construction string for this tree is "1,2,#,#,3,4,#,5" shorter
 * than level order traversal.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    private static final String NULL_NODE = "#";
    private static final String COMMA = ",";

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    public int depth() {
        return depth(this);
    }

    public static int depth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    /*
      <pre>
          1                    1
         / \                  /
        2   4                2
       /                    /
      3                    3
                          /
                         4
      </pre>

      The above two trees will both be "1,2,3,4" (pre-order) if we omit right null nodes and
      two null children.
     */

    /**
     * @return pre-order traversal string: root, left, right (NLR).
     */
    public String preOrderString() {
        // no need to consider this == null cannot call null.toString()
        return String.valueOf(this.val) + COMMA +
                (this.left == null ? NULL_NODE : this.left.preOrderString()) + COMMA +
                (this.right == null ? NULL_NODE : this.right.preOrderString());
    }

    /**
     * @return in-order traversal: left, root, right (LNR).
     */
    public String inOrderString() {
        String ans = (this.left == null ? NULL_NODE : this.left.inOrderString()) +
                COMMA + this.val + COMMA +
                (this.right == null ? NULL_NODE : this.right.inOrderString());
        return ans;
    }

    /**
     * @return post-order traversal: left, right, root
     */
    public String postOrderString() {
        return (this.left == null ? NULL_NODE : this.left.postOrderString()) + COMMA +
                (this.right == null ? NULL_NODE : this.right.postOrderString()) + COMMA +
                this.val;
    }

    public String levelOrderString() {
        StringBuilder ans = new StringBuilder();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(this);
        ans.append(this.val);
        while (!q.isEmpty()) {
            int numNodes = q.size();
            while (numNodes > 0) {
                TreeNode c = q.remove();
                String left = NULL_NODE, right = NULL_NODE;
                if (c.left != null) {
                    q.add(c.left);
                    left = String.valueOf(c.left.val);
                }
                if (c.right != null) {
                    q.add(c.right);
                    right = String.valueOf(c.right.val);
                }
                numNodes--;
                ans.append(COMMA).append(left).append(COMMA).append(right);
            }
        }
        return ans.toString();
    }

    /**
     * Default level order String.
     *
     * @return level-order traversal string representation.
     */
    public String toString() {
        return levelOrderString();
    }

    /**
     * No need to get height first, see {@link tree.BTLevelOrder}.
     *
     * @return level order string representation of the tree with recursive printing.
     */
    @SuppressWarnings("unused")
    public String levelOrderString2() {
        int height = this.depth();
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= height; i++) {
            res.append(printLevel(this, i));
            res.append(System.lineSeparator());
        }
        return res.toString();
    }

    /**
     * Recursive helper method to print all levels of the tree. Not efficient.
     *
     * @param root  root node to print.
     * @param level number of levels down (tree height) from the root node.
     * @return the string representation of the tree.
     */
    private String printLevel(TreeNode root, int level) {
        StringBuilder res = new StringBuilder();
        String val;
        if (root == null) val = "#,";
        else val = root.val + ",";
        if (root == this) val += ":root";
        if (level == 1) res.append(val);
        else if (level > 1 && root != null) {
            res.append(printLevel(root.left, level - 1));
            res.append(printLevel(root.right, level - 1));
        }
        return res.toString();
    }


    /**
     * Construct tree with natural increasing numbers, e.g., 2 layer tree level order 0,1,2,#,#,#,#.
     *
     * @param numLayers number of layers to construct.
     * @return constructed tree root node.
     */
    public static TreeNode constructTreeNaturalNumbers(int numLayers) {
        if (numLayers == 0) return null;
        TreeNode root = new TreeNode(0);
        int curLayer = 1;
        // number of nodes in each layer is geometric series
        while (curLayer++ < numLayers) root = add1Layer(root);
        return root;
    }

    /**
     * Construct tree from pre-order (NLR node, left subtree, right subtree) string, e.g., 0,1,2,#,#
     * <pre>
     *     0
     *    / \
     *   1  #
     *  / \
     * 2  #
     * </pre>
     *
     * @param tree pre-order string delimited by space or comma.
     * @return root node of the constructed tree.
     */
    public static TreeNode readFromPreOrderString(String tree) {
        String[] nums = tree.split("[\\s,]");
        if (nums.length == 0) return null;
        TreeNode root = readOneNode(nums[0]);
        if (root == null) return null;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>(); // stack
        stack.push(root);
        TreeNode cur = root;
        boolean leftFlag = true;
        for (int i = 1; i < nums.length; i++) {
            TreeNode node = readOneNode(nums[i]);
            if (node == null) {
                if (stack.isEmpty()) break;
                if (leftFlag) leftFlag = false;
                cur = stack.pop();
            } else {
                if (leftFlag) {
                    cur.left = node;
                    cur = cur.left;
                } else {
                    cur.right = node;
                    cur = cur.right;
                }
                stack.push(cur);
                if (!leftFlag) leftFlag = true;
            }
        }
        return root;
    }


    /**
     * Reads a tree form LeetCode OJ format level order string.
     *
     * @param tree a level ordered string representation of a binary tree
     * @return the root TreeNode of the binary tree
     */
    public static TreeNode readFromLevelOrderString(String tree) {
        String[] nums = tree.split("[\\s,]");
        if (nums.length == 0) return null;
        TreeNode root = readOneNode(nums[0]);
        if (root == null) return null;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < nums.length) {
            TreeNode p = q.remove();
            p.left = readOneNode(nums[i++]);
            if (p.left != null) q.add(p.left);
            if (i < nums.length) p.right = readOneNode(nums[i++]);
            if (p.right != null) q.add(p.right);
        }
        return root;
    }

    private static TreeNode readOneNode(String s) {
        if (s == null) throw new IllegalArgumentException(
                "Please use \"null\" " + "or \"#\" to represent a null TreeNode.");
        if (s.equals("#") || s.equals("null")) return null;
        else return new TreeNode(Integer.parseInt(s));
    }

    private static TreeNode add1Layer(TreeNode node) {
        if (node.left == null) {
            node.left = new TreeNode(2 * node.val + 1);
            node.right = new TreeNode(2 * node.val + 2);
        } else {
            add1Layer(node.left);
            add1Layer(node.right);
        }
        return node;
    }

}
