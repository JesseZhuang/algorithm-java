package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtreeTest {

    final Subtree.Solution1 sol1 = new Subtree.Solution1();
    final Subtree.Solution2 sol2 = new Subtree.Solution2();

    void assertAll(boolean expected, TreeNode root, TreeNode subRoot) {
        assertEquals(expected, sol1.isSubtree(root, subRoot));
        assertEquals(expected, sol2.isSubtreeString(root, subRoot));
        // Solution3 is stateful (memo list), create fresh instance each call
        assertEquals(expected, new Subtree.Solution3().isSubtreeHash(root, subRoot));
    }

    @Test
    void testExample1() {
        // root=[3,4,5,1,2], subRoot=[4,1,2] → true
        TreeNode root = TreeNode.readFromLevelOrderString("3,4,5,1,2");
        TreeNode sub = TreeNode.readFromLevelOrderString("4,1,2");
        assertAll(true, root, sub);
    }

    @Test
    void testExample2() {
        // root=[3,4,5,1,2,#,#,#,#,0], subRoot=[4,1,2] → false
        TreeNode root = TreeNode.readFromLevelOrderString("3,4,5,1,2,#,#,#,#,0");
        TreeNode sub = TreeNode.readFromLevelOrderString("4,1,2");
        assertAll(false, root, sub);
    }

    @Test
    void testSingleNodeMatch() {
        // root=[1], sub=[1] → true
        TreeNode root = new TreeNode(1);
        TreeNode sub = new TreeNode(1);
        assertAll(true, root, sub);
    }

    @Test
    void testSingleNodeNoMatch() {
        // root=[1], sub=[2] → false
        TreeNode root = new TreeNode(1);
        TreeNode sub = new TreeNode(2);
        assertAll(false, root, sub);
    }

    @Test
    void testRootEqualsSubtree() {
        // root=[1,2,3], sub=[1,2,3] → true
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3");
        TreeNode sub = TreeNode.readFromLevelOrderString("1,2,3");
        assertAll(true, root, sub);
    }

    @Test
    void testNullRoot() {
        // root=null, sub=[1] → false
        TreeNode sub = new TreeNode(1);
        assertAll(false, null, sub);
    }

    @Test
    void testValuePrefixTrap() {
        // root=[12], sub=[2] → false
        TreeNode root = new TreeNode(12);
        TreeNode sub = new TreeNode(2);
        assertAll(false, root, sub);
    }

    @Test
    void testNegativeValues() {
        // root=[-1,-2], sub=[-2] → true
        TreeNode root = TreeNode.readFromLevelOrderString("-1,-2");
        TreeNode sub = new TreeNode(-2);
        assertAll(true, root, sub);
    }

    @Test
    void testDeepRightChain() {
        // root=[1,#,2,#,3], sub=[2,#,3] → true
        TreeNode root = TreeNode.readFromLevelOrderString("1,#,2,#,3");
        TreeNode sub = TreeNode.readFromLevelOrderString("2,#,3");
        assertAll(true, root, sub);
    }
}
