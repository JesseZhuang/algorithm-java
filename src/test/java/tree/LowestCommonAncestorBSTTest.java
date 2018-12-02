package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowestCommonAncestorBSTTest {
    private static LowestCommonAncestorBST toBeTested;
    private TreeNode root;

    @BeforeEach
    void setup() {
        toBeTested = new LowestCommonAncestorBST();
        root = TreeNode.readFromLevelOrderString("6,2,8,0,4,7,9,#,#,3,5,#,#,#,#");
        System.out.println(root);
    }

    @Test
    void testLCA() {
        TreeNode p = root.left.left, q = root.right.left, lca = root;
        testLCAMethods(root, p, q, lca);

        p = root.left.left; q = root.left.right.left; lca = root.left;
        testLCAMethods(root, p, q, lca);

        p = root.left.right; q = p.right; lca = p;
        testLCAMethods(root, p, q, lca);
    }

    private void testLCAMethods(TreeNode root, TreeNode p, TreeNode q, TreeNode lca) {
        assertEquals(toBeTested.lowestCommonAncestor(root, p, q), lca);
        assertEquals(toBeTested.lowestCommonAncestor2(root, p, q), lca);
    }
}
