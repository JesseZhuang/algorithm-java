package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlattenBtToLinkedListTest {

    private List<Integer> toList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            assertNull(cur.left, "left child should be null in flattened tree");
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    @Test
    void testIterative_example() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,5,3,4,#,6");
        FlattenBtToLinkedList.flattenIterative(root);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), toList(root));
    }

    @Test
    void testRecursive_example() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,5,3,4,#,6");
        FlattenBtToLinkedList.flattenRecursive(root);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), toList(root));
    }

    @Test
    void testIterative_null() {
        FlattenBtToLinkedList.flattenIterative(null);
        // no exception
    }

    @Test
    void testRecursive_null() {
        FlattenBtToLinkedList.flattenRecursive(null);
        // no exception
    }

    @Test
    void testIterative_singleNode() {
        TreeNode root = new TreeNode(1);
        FlattenBtToLinkedList.flattenIterative(root);
        assertEquals(List.of(1), toList(root));
    }

    @Test
    void testRecursive_singleNode() {
        TreeNode root = new TreeNode(1);
        FlattenBtToLinkedList.flattenRecursive(root);
        assertEquals(List.of(1), toList(root));
    }

    @Test
    void testIterative_leftSkewed() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,#,3,#");
        FlattenBtToLinkedList.flattenIterative(root);
        assertEquals(List.of(1, 2, 3), toList(root));
    }

    @Test
    void testRecursive_rightSkewed() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,#,2,#,3");
        FlattenBtToLinkedList.flattenRecursive(root);
        assertEquals(List.of(1, 2, 3), toList(root));
    }
}
