package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InvertBTTest {

    final InvertBT tbt = new InvertBT();

    @Test
    void testExample1() {
        TreeNode expected = TreeNode.readFromLevelOrderString("4,7,2,9,6,3,1");
        TreeNode root1 = TreeNode.readFromLevelOrderString("4,2,7,1,3,6,9");
        assertEquals(expected, tbt.invertTree(root1));
        TreeNode root2 = TreeNode.readFromLevelOrderString("4,2,7,1,3,6,9");
        assertEquals(expected, tbt.invertTree2(root2));
    }

    @Test
    void testExample2() {
        TreeNode expected = TreeNode.readFromLevelOrderString("2,3,1");
        TreeNode root1 = TreeNode.readFromLevelOrderString("2,1,3");
        assertEquals(expected, tbt.invertTree(root1));
        TreeNode root2 = TreeNode.readFromLevelOrderString("2,1,3");
        assertEquals(expected, tbt.invertTree2(root2));
    }

    @Test
    void testEmpty() {
        assertNull(tbt.invertTree(null));
        assertNull(tbt.invertTree2(null));
    }

    @Test
    void testSingleNode() {
        TreeNode expected = new TreeNode(1);
        TreeNode root1 = new TreeNode(1);
        assertEquals(expected, tbt.invertTree(root1));
        TreeNode root2 = new TreeNode(1);
        assertEquals(expected, tbt.invertTree2(root2));
    }

    @Test
    void testLeftOnly() {
        TreeNode expected = TreeNode.readFromLevelOrderString("1,#,2,#,3");
        TreeNode root1 = TreeNode.readFromLevelOrderString("1,2,#,3");
        assertEquals(expected, tbt.invertTree(root1));
        TreeNode root2 = TreeNode.readFromLevelOrderString("1,2,#,3");
        assertEquals(expected, tbt.invertTree2(root2));
    }

    @Test
    void testAsymmetric() {
        TreeNode expected = TreeNode.readFromLevelOrderString("1,3,2,#,#,#,4");
        TreeNode root1 = TreeNode.readFromLevelOrderString("1,2,3,4");
        assertEquals(expected, tbt.invertTree(root1));
        TreeNode root2 = TreeNode.readFromLevelOrderString("1,2,3,4");
        assertEquals(expected, tbt.invertTree2(root2));
    }
}
