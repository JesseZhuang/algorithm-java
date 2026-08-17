package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountOfTimeInfectedTest {

    @Test
    void testExample() {
        // Tree:       1
        //            / \
        //           5   3
        //          /   / \
        //         4   10   6
        //        / \
        //       9   2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(9);
        root.left.left.right = new TreeNode(2);

        assertEquals(4, AmountOfTimeInfected.amountOfTime(root, 3));
        assertEquals(4, AmountOfTimeInfected.amountOfTimeDfs(root, 3));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(0, AmountOfTimeInfected.amountOfTime(root, 1));
        assertEquals(0, AmountOfTimeInfected.amountOfTimeDfs(root, 1));
    }

    @Test
    void testStartAtRoot() {
        // Tree: 1->2,3; 2->4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        assertEquals(2, AmountOfTimeInfected.amountOfTime(root, 1));
        assertEquals(2, AmountOfTimeInfected.amountOfTimeDfs(root, 1));
    }

    @Test
    void testStartAtLeaf() {
        // Tree: 1->2,3; 2->4,5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        assertEquals(3, AmountOfTimeInfected.amountOfTime(root, 4));
        assertEquals(3, AmountOfTimeInfected.amountOfTimeDfs(root, 4));
    }

    @Test
    void testLinearTree() {
        // 1->2->3->4 (all left children)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        assertEquals(2, AmountOfTimeInfected.amountOfTime(root, 2));
        assertEquals(2, AmountOfTimeInfected.amountOfTimeDfs(root, 2));
    }

    @Test
    void testDeepRightBranch() {
        // Tree: 1->2,3; 3->null,4; 4->null,5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);

        assertEquals(3, AmountOfTimeInfected.amountOfTime(root, 1));
        assertEquals(3, AmountOfTimeInfected.amountOfTimeDfs(root, 1));
    }
}
