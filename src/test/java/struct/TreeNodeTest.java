package struct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TreeNodeTest {
    private TreeNode toBeTested;

    @BeforeEach
    void setup() {
        toBeTested = TreeNode.constructTreeNaturalNumbers(3);
    }

    @Test
    void testConstructTreeNaturalNumbers() {
        assertEquals(toBeTested.val, 0);
        assertEquals(toBeTested.left.val, 1);
        assertEquals(toBeTested.right.val, 2);
        assertEquals(toBeTested.left.left.val, 3);
        assertEquals(toBeTested.left.right.val, 4);
        assertEquals(toBeTested.right.left.val, 5);
        assertEquals(toBeTested.right.right.val, 6);
        assertNull(toBeTested.left.left.left);
        assertNull(toBeTested.left.left.right);
    }

    @Test
    void testReadPreOrder() {
        assertEquals(toBeTested, TreeNode.readFromPreOrderString("0,1,3,#,#,4,#,#,2,5,#,#,6,#,#"));

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        assertEquals(root, TreeNode.readFromPreOrderString("0,1,#,#,#"));

        root.left = null;
        root.right = new TreeNode(1);
        assertEquals(root, TreeNode.readFromPreOrderString("0,#,1,#,#"));

        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(root, TreeNode.readFromPreOrderString("0,#,1,2,#,#,3,#,#"));
    }

    @Test
    void testReadLevelOrder() {
        assertEquals(toBeTested, TreeNode.readFromLevelOrderString("0,1,2,3,4,5,6,#,#,#,#,#,#,#,#"));
    }

    @Test
    void testPreOrderString() {
        assertEquals(toBeTested.preOrderString(), "0,1,3,#,#,4,#,#,2,5,#,#,6,#,#");
    }

    @Test
    void testInOrderString() {
        assertEquals(toBeTested.inOrderString(), "#,3,#,1,#,4,#,0,#,5,#,2,#,6,#");
    }

    @Test
    void testPostOrderString() {
        assertEquals(toBeTested.postOrderString(), "#,#,3,#,#,4,1,#,#,5,#,#,6,2,0");
    }

    @Test
    void testLevelOrderString() {
        assertEquals(toBeTested.levelOrderString(), "0,1,2,3,4,5,6,#,#,#,#,#,#,#,#");
    }

    @Test
    void testToString() {

        assertEquals(toBeTested.toString(), "0:root\n" + "1,2\n" + "3,4,5,6\n" + "#,#,#,#,#,#,#,#\n");
    }

    @Test
    void testDepth() {
        assertEquals(toBeTested.depth(), 3);
    }
}
