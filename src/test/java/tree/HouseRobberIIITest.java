package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseRobberIIITest {

    @Test
    void testExample1() {
        // [3,2,3,null,3,null,1] -> 7
        TreeNode root = TreeNode.readFromLevelOrderString("3,2,3,#,3,#,1");
        assertEquals(7, HouseRobberIII.rob(root));
    }

    @Test
    void testExample2() {
        // [3,4,5,1,3,null,1] -> 9
        TreeNode root = TreeNode.readFromLevelOrderString("3,4,5,1,3,#,1");
        assertEquals(9, HouseRobberIII.rob(root));
    }

    @Test
    void testSingleNode() {
        // [5] -> 5
        TreeNode root = new TreeNode(5);
        assertEquals(5, HouseRobberIII.rob(root));
    }

    @Test
    void testNull() {
        // null -> 0
        assertEquals(0, HouseRobberIII.rob(null));
    }

    @Test
    void testLeftSkew() {
        // [4,1,null,null,2] -> 6 (rob 4 + 2)
        TreeNode root = TreeNode.readFromLevelOrderString("4,1,#,#,2");
        assertEquals(6, HouseRobberIII.rob(root));
    }

    @Test
    void testAllOnes() {
        // [1,1,1,1,1] -> 3 (rob three grandchildren-level nodes)
        TreeNode root = TreeNode.readFromLevelOrderString("1,1,1,1,1");
        assertEquals(3, HouseRobberIII.rob(root));
    }
}
