package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BTFromPOIOTest {

    BTFromPOIO.Solution s1;
    BTFromPOIO.Solution2 s2;

    @BeforeEach
    void setUp() {
        s1 = new BTFromPOIO.Solution();
        s2 = new BTFromPOIO.Solution2();
    }

    @Test
    void testExample1() {
        int[] preorder = {3, 9, 20, 15, 7}, inorder = {9, 3, 15, 20, 7};
        String expected = "3,9,20,#,#,15,7,#,#,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }

    @Test
    void testExample2() {
        int[] preorder = {-1}, inorder = {-1};
        String expected = "-1,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }

    @Test
    void testLeftSkewed() {
        int[] preorder = {1, 2, 3}, inorder = {3, 2, 1};
        String expected = "1,2,#,3,#,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }

    @Test
    void testRightSkewed() {
        int[] preorder = {1, 2, 3}, inorder = {1, 2, 3};
        String expected = "1,#,2,#,3,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }

    @Test
    void testSingleNode() {
        int[] preorder = {42}, inorder = {42};
        String expected = "42,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }

    @Test
    void testTwoNodesLeft() {
        int[] preorder = {1, 2}, inorder = {2, 1};
        String expected = "1,2,#,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }

    @Test
    void testTwoNodesRight() {
        int[] preorder = {1, 2}, inorder = {1, 2};
        String expected = "1,#,2,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }

    @Test
    void testNegativeValues() {
        int[] preorder = {-10, -20, -30, -5}, inorder = {-30, -20, -10, -5};
        String expected = "-10,-20,-5,-30,#,#,#,#,#";
        assertEquals(expected, s1.buildTree(preorder, inorder).toString());
        assertEquals(expected, s2.buildTree(preorder, inorder).toString());
    }
}
