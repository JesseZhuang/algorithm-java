package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BTZigZagTest {

    private final BTZigZag.Solution1 s1 = new BTZigZag.Solution1();
    private final BTZigZag.Solution2 s2 = new BTZigZag.Solution2();

    @Test
    void testExample() {
        TreeNode root = TreeNode.readFromLevelOrderString("3,9,20,#,#,15,7");
        List<List<Integer>> expected = List.of(List.of(3), List.of(20, 9), List.of(15, 7));
        assertEquals(expected, s1.zigzagLevelOrderDfs(root));
        assertEquals(expected, s2.zigzagLevelOrderBfs(root));
    }

    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.readFromLevelOrderString("1");
        List<List<Integer>> expected = List.of(List.of(1));
        assertEquals(expected, s1.zigzagLevelOrderDfs(root));
        assertEquals(expected, s2.zigzagLevelOrderBfs(root));
    }

    @Test
    void testEmpty() {
        List<List<Integer>> expected = List.of();
        assertEquals(expected, s1.zigzagLevelOrderDfs(null));
        assertEquals(expected, s2.zigzagLevelOrderBfs(null));
    }

    @Test
    void testCompleteTree() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,5,6,7");
        List<List<Integer>> expected = List.of(List.of(1), List.of(3, 2), List.of(4, 5, 6, 7));
        assertEquals(expected, s1.zigzagLevelOrderDfs(root));
        assertEquals(expected, s2.zigzagLevelOrderBfs(root));
    }

    @Test
    void testFourLevelsSparse() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,#,#,5,6,#,#,7");
        List<List<Integer>> expected = List.of(List.of(1), List.of(3, 2), List.of(4, 5), List.of(7, 6));
        assertEquals(expected, s1.zigzagLevelOrderDfs(root));
        assertEquals(expected, s2.zigzagLevelOrderBfs(root));
    }

    @Test
    void testNegativeValues() {
        TreeNode root = TreeNode.readFromLevelOrderString("-100,0,100");
        List<List<Integer>> expected = List.of(List.of(-100), List.of(100, 0));
        assertEquals(expected, s1.zigzagLevelOrderDfs(root));
        assertEquals(expected, s2.zigzagLevelOrderBfs(root));
    }
}
