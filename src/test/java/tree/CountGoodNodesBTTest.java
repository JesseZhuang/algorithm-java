package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountGoodNodesBTTest {

    @Test
    void testExample1() {
        // root = [3,1,4,3,null,1,5], good: 3, 3, 4, 5
        TreeNode root = TreeNode.readFromLevelOrderString("3,1,4,3,#,1,5");
        assertEquals(4, CountGoodNodesBT.goodNodes(root));
        assertEquals(4, CountGoodNodesBT.goodNodes2(root));
    }

    @Test
    void testExample2() {
        // root = [3,3,null,4,2], good: 3, 3, 4
        TreeNode root = TreeNode.readFromLevelOrderString("3,3,#,4,2");
        assertEquals(3, CountGoodNodesBT.goodNodes(root));
        assertEquals(3, CountGoodNodesBT.goodNodes2(root));
    }

    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.readFromLevelOrderString("1");
        assertEquals(1, CountGoodNodesBT.goodNodes(root));
        assertEquals(1, CountGoodNodesBT.goodNodes2(root));
    }

    @Test
    void testAllSame() {
        // [5,5,5], all nodes are good
        TreeNode root = TreeNode.readFromLevelOrderString("5,5,5");
        assertEquals(3, CountGoodNodesBT.goodNodes(root));
        assertEquals(3, CountGoodNodesBT.goodNodes2(root));
    }

    @Test
    void testDecreasing() {
        // [10,5,#,3,#,1], only root is good
        TreeNode root = TreeNode.readFromLevelOrderString("10,5,#,3,#,1");
        assertEquals(1, CountGoodNodesBT.goodNodes(root));
        assertEquals(1, CountGoodNodesBT.goodNodes2(root));
    }

    @Test
    void testIncreasingRight() {
        // [1,#,2,#,3,#,4], all nodes are good
        TreeNode root = TreeNode.readFromLevelOrderString("1,#,2,#,3,#,4");
        assertEquals(4, CountGoodNodesBT.goodNodes(root));
        assertEquals(4, CountGoodNodesBT.goodNodes2(root));
    }

    @Test
    void testNegativeDecreasing() {
        // [-1,-2,-3], only root is good
        TreeNode root = TreeNode.readFromLevelOrderString("-1,-2,-3");
        assertEquals(1, CountGoodNodesBT.goodNodes(root));
        assertEquals(1, CountGoodNodesBT.goodNodes2(root));
    }

    @Test
    void testNegativeAllGood() {
        // [-10,-5,-10], -5 >= -10 (good), -10 >= -10 (good), root always good
        TreeNode root = TreeNode.readFromLevelOrderString("-10,-5,-10");
        assertEquals(3, CountGoodNodesBT.goodNodes(root));
        assertEquals(3, CountGoodNodesBT.goodNodes2(root));
    }
}
