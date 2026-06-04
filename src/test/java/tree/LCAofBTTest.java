package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LCAofBTTest {
    private static LCAofBT toBeTested;
    private TreeNode root;

    @BeforeEach
    void setup() {
        toBeTested = new LCAofBT();
        root = TreeNode.readFromLevelOrderString("3,5,1,6,2,0,8,#,#,7,4");
    }

    @Test
    void testLCA_pIsLeftChild_qIsRightChild() {
        // p=5, q=1 -> LCA=3 (root)
        TreeNode p = root.left, q = root.right, lca = root;
        testAllMethods(root, p, q, lca);
    }

    @Test
    void testLCA_ancestorIsP() {
        // p=5, q=4 -> LCA=5
        TreeNode p = root.left, q = root.left.right.right, lca = root.left;
        testAllMethods(root, p, q, lca);
    }

    @Test
    void testLCA_rootIsP() {
        // tree [1,2], p=1, q=2 -> LCA=1
        TreeNode smallRoot = TreeNode.readFromLevelOrderString("1,2");
        TreeNode p = smallRoot, q = smallRoot.left, lca = smallRoot;
        testAllMethods(smallRoot, p, q, lca);
    }

    @Test
    void testLCA_deepNodes() {
        // p=7, q=4 -> LCA=2
        TreeNode p = root.left.right.left, q = root.left.right.right, lca = root.left.right;
        testAllMethods(root, p, q, lca);
    }

    @Test
    void testLCA_siblingSubtrees() {
        // p=6, q=4 -> LCA=5
        TreeNode p = root.left.left, q = root.left.right.right, lca = root.left;
        testAllMethods(root, p, q, lca);
    }

    private void testAllMethods(TreeNode root, TreeNode p, TreeNode q, TreeNode lca) {
        assertEquals(lca, toBeTested.lowestCommonAncestor(root, p, q));
        assertEquals(lca, toBeTested.lowestCommonAncestorI1(root, p, q));
        assertEquals(lca, toBeTested.lowestCommonAncestorI2(root, p, q));
    }
}
