package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeInorderTraversalTest {

    @Test
    void returnsEmptyListForEmptyTree() {
        assertEquals(List.of(), BinaryTreeInorderTraversal.inorderTraversal(null));
    }

    @Test
    void returnsOnlyValueForSingleNode() {
        assertEquals(List.of(42), BinaryTreeInorderTraversal.inorderTraversal(new TreeNode(42)));
    }

    @Test
    void visitsBalancedTreeLeftRootRight() {
        TreeNode root = TreeNode.readFromLevelOrderString("4,2,6,1,3,5,7");

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7),
                BinaryTreeInorderTraversal.inorderTraversal(root));
    }

    @Test
    void visitsUnbalancedTreeLeftRootRight() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,null,2,3");

        assertEquals(List.of(1, 3, 2), BinaryTreeInorderTraversal.inorderTraversal(root));
    }
}
