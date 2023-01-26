package tree;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 197, hard, tags: string, tree, binary tree, design, dfs, bfs.
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg
 * <p>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
public class SerializeBT {
    static final String COMMA = ",";
    static final String NULL_NODE = "#";

    // 8ms, 43.8Mb. preorder recursive. O(n) time and O(height) space.
    public String serializePOR(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderHelper(root, sb);
        return sb.toString();
    }

    private void preOrderHelper(TreeNode node, StringBuilder sb) {
        if (node == null) sb.append(NULL_NODE);
        else {
            sb.append(node.val).append(COMMA);
            preOrderHelper(node.left, sb);
            sb.append(COMMA);
            preOrderHelper(node.right, sb);
        }
    }

    public TreeNode deserializePOR(String data) {
        Deque<String> nodes = new ArrayDeque<>();
        nodes.addAll(Arrays.asList(data.split(COMMA)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NULL_NODE)) return null;
        TreeNode node = readNode(val);
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }

    // 19ms, 52.9 MB. level order Iterative. O(n) time, O(leaves, worst case n) space.
    public String serializeLOI(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
            sb.append(root.val);
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.remove();
                String left = NULL_NODE, right = NULL_NODE;
                if (n.left != null) {
                    q.add(n.left);
                    left = String.valueOf(n.left.val);
                }
                if (n.right != null) {
                    right = String.valueOf(n.right.val);
                    q.add(n.right);
                }
                sb.append(COMMA).append(left).append(COMMA).append(right);
            }
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserializeLOI(String data) {
        String[] nodes = data.split(COMMA);
        Deque<TreeNode> q = new LinkedList<>();
        TreeNode root = readNode(nodes[0]);
        if (root == null) return null; // need handle 0 node in the tree, empty string case
        int count = 0;
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.remove();
                n.left = readNode(nodes[count + 1]);
                n.right = readNode(nodes[count + 2]);
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
                count += 2;
            }
        }
        return root;
    }

    private TreeNode readNode(String s) {
        if ("#".equals(s) || "".equals(s)) return null;
        else return new TreeNode(Integer.parseInt(s));
    }

    public static void main(String[] args) {
        SerializeBT tbt = new SerializeBT();
        TreeNode[] roots = {TreeNode.constructTreeNaturalNumbers(3), null};
        for (TreeNode root : roots) {
            System.out.println(root);
            String level = tbt.serializeLOI(root);
            System.out.println(level);
            System.out.println(tbt.deserializeLOI(level));
            System.out.println("-----");
            System.out.println("preorder:");
            System.out.println(tbt.serializePOR(root));
            if (root != null) System.out.println(root.preOrderString());
            System.out.println("-----");
        }
        System.out.println("".split(COMMA).length); // 1 empty string
    }
}
