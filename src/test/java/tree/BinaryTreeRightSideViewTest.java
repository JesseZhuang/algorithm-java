package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeRightSideViewTest {

    @Test
    void testExample1() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,#,5,#,4");
        assertEquals(List.of(1, 3, 4), BinaryTreeRightSideView.rightSideViewBFS(root));
        assertEquals(List.of(1, 3, 4), BinaryTreeRightSideView.rightSideViewDFS(root));
    }

    @Test
    void testExample2() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,#,3");
        assertEquals(List.of(1, 3), BinaryTreeRightSideView.rightSideViewBFS(root));
        assertEquals(List.of(1, 3), BinaryTreeRightSideView.rightSideViewDFS(root));
    }

    @Test
    void testEmpty() {
        assertEquals(List.of(), BinaryTreeRightSideView.rightSideViewBFS(null));
        assertEquals(List.of(), BinaryTreeRightSideView.rightSideViewDFS(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(List.of(1), BinaryTreeRightSideView.rightSideViewBFS(root));
        assertEquals(List.of(1), BinaryTreeRightSideView.rightSideViewDFS(root));
    }

    @Test
    void testLeftDeeper() {
        // Left subtree is deeper, so bottom-most visible node is from left side
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,#,#,#");
        assertEquals(List.of(1, 3, 4), BinaryTreeRightSideView.rightSideViewBFS(root));
        assertEquals(List.of(1, 3, 4), BinaryTreeRightSideView.rightSideViewDFS(root));
    }
}
