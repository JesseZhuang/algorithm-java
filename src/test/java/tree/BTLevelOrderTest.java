package tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import struct.TreeNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BTLevelOrderTest {
    private static BTLevelOrder tbt;

    @BeforeAll
    static void setup() {
        tbt = new BTLevelOrder();
    }

    @Test
    void testExample1() {
        TreeNode root = TreeNode.readFromLevelOrderString("3,9,20,#,#,15,7");
        List<List<Integer>> expected = List.of(List.of(3), List.of(9, 20), List.of(15, 7));
        assertEquals(expected, tbt.levelOrderBFS(root));
        assertEquals(expected, tbt.levelOrderR(root));
    }

    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.readFromLevelOrderString("1");
        List<List<Integer>> expected = List.of(List.of(1));
        assertEquals(expected, tbt.levelOrderBFS(root));
        assertEquals(expected, tbt.levelOrderR(root));
    }

    @Test
    void testNull() {
        List<List<Integer>> expected = List.of();
        assertEquals(expected, tbt.levelOrderBFS(null));
        assertEquals(expected, tbt.levelOrderR(null));
    }

    @Test
    void testCompleteTree() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,5,6,7");
        List<List<Integer>> expected = List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7));
        assertEquals(expected, tbt.levelOrderBFS(root));
        assertEquals(expected, tbt.levelOrderR(root));
    }

    @Test
    void testLeftSkewed() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,#,#,3");
        List<List<Integer>> expected = List.of(List.of(1), List.of(2), List.of(3));
        assertEquals(expected, tbt.levelOrderBFS(root));
        assertEquals(expected, tbt.levelOrderR(root));
    }
}
