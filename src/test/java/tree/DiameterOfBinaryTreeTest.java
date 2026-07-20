package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiameterOfBinaryTreeTest {

    @Test
    void testExample1() {
        // root = [1,2,3,4,5], diameter path [4,2,1,3] or [5,2,1,3] = 3 edges
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,5");
        assertEquals(3, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    @Test
    void testExample2() {
        // root = [1,2], diameter = 1
        TreeNode root = TreeNode.readFromLevelOrderString("1,2");
        assertEquals(1, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    @Test
    void testSingleNode() {
        // single node, no edges, diameter = 0
        TreeNode root = new TreeNode(1);
        assertEquals(0, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    @Test
    void testLinearTree() {
        // linear tree: 1-2-3-4 (left skewed), diameter = 3
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,#,3,#,4");
        assertEquals(3, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    @Test
    void testDiameterNotThroughRoot() {
        // diameter does not pass through root
        //        1
        //       /
        //      2
        //     / \
        //    3   4
        //   /     \
        //  5       6
        // longest path: 5-3-2-4-6 = 4 edges
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,#,3,4,5,#,#,6");
        assertEquals(4, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    @Test
    void testBalancedTree() {
        // complete binary tree with 3 levels: [1,2,3,4,5,6,7]
        // diameter = 4 (e.g., 4-2-1-3-7)
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,5,6,7");
        assertEquals(4, DiameterOfBinaryTree.diameterOfBinaryTree(root));
    }
}
