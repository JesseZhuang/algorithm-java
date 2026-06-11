package tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.*;

public class SerializeBTTest {
    private static SerializeBT sbt;

    @BeforeAll
    static void setup() {
        sbt = new SerializeBT();
    }

    private void assertTreesEqual(TreeNode expected, TreeNode actual) {
        assertEquals(expected, actual);
    }

    private TreeNode node(int val, TreeNode left, TreeNode right) {
        TreeNode n = new TreeNode(val);
        n.left = left;
        n.right = right;
        return n;
    }

    // --- Preorder Recursive (POR) tests ---

    @Test
    void testPOR_example() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,#,#,4,5");
        String serialized = sbt.serializePOR(root);
        TreeNode deserialized = sbt.deserializePOR(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testPOR_emptyTree() {
        String serialized = sbt.serializePOR(null);
        TreeNode deserialized = sbt.deserializePOR(serialized);
        assertNull(deserialized);
    }

    @Test
    void testPOR_singleNode() {
        TreeNode root = new TreeNode(42);
        String serialized = sbt.serializePOR(root);
        TreeNode deserialized = sbt.deserializePOR(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testPOR_leftSkewed() {
        TreeNode root = node(1, node(2, node(3, node(4, null, null), null), null), null);
        String serialized = sbt.serializePOR(root);
        TreeNode deserialized = sbt.deserializePOR(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testPOR_rightSkewed() {
        TreeNode root = node(1, null, node(2, null, node(3, null, node(4, null, null))));
        String serialized = sbt.serializePOR(root);
        TreeNode deserialized = sbt.deserializePOR(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testPOR_negativeValues() {
        TreeNode root = node(-1000, node(500, null, null), node(1000, node(-500, null, null), null));
        String serialized = sbt.serializePOR(root);
        TreeNode deserialized = sbt.deserializePOR(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testPOR_completeBinaryTreeDepth3() {
        // Complete binary tree depth 3: nodes 0..6
        TreeNode root = TreeNode.constructTreeNaturalNumbers(3);
        String serialized = sbt.serializePOR(root);
        TreeNode deserialized = sbt.deserializePOR(serialized);
        assertTreesEqual(root, deserialized);
    }

    // --- Level Order Iterative (LOI) tests ---

    @Test
    void testLOI_example() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,#,#,4,5");
        String serialized = sbt.serializeLOI(root);
        TreeNode deserialized = sbt.deserializeLOI(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testLOI_emptyTree() {
        String serialized = sbt.serializeLOI(null);
        TreeNode deserialized = sbt.deserializeLOI(serialized);
        assertNull(deserialized);
    }

    @Test
    void testLOI_singleNode() {
        TreeNode root = new TreeNode(42);
        String serialized = sbt.serializeLOI(root);
        TreeNode deserialized = sbt.deserializeLOI(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testLOI_leftSkewed() {
        TreeNode root = node(1, node(2, node(3, node(4, null, null), null), null), null);
        String serialized = sbt.serializeLOI(root);
        TreeNode deserialized = sbt.deserializeLOI(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testLOI_rightSkewed() {
        TreeNode root = node(1, null, node(2, null, node(3, null, node(4, null, null))));
        String serialized = sbt.serializeLOI(root);
        TreeNode deserialized = sbt.deserializeLOI(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testLOI_negativeValues() {
        TreeNode root = node(-1000, node(500, null, null), node(1000, node(-500, null, null), null));
        String serialized = sbt.serializeLOI(root);
        TreeNode deserialized = sbt.deserializeLOI(serialized);
        assertTreesEqual(root, deserialized);
    }

    @Test
    void testLOI_completeBinaryTreeDepth3() {
        TreeNode root = TreeNode.constructTreeNaturalNumbers(3);
        String serialized = sbt.serializeLOI(root);
        TreeNode deserialized = sbt.deserializeLOI(serialized);
        assertTreesEqual(root, deserialized);
    }
}
