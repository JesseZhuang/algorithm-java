package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxWidthBTTest {

    final MaxWidthBT sol = new MaxWidthBT();

    private void check(String tree, int expected) {
        TreeNode root = TreeNode.readFromLevelOrderString(tree);
        assertEquals(expected, sol.widthOfBinaryTreeBfs(root));
        assertEquals(expected, sol.widthOfBinaryTreeDfs(root));
    }

    @Test
    void example1() {
        check("1,3,2,5,3,#,9", 4);
    }

    @Test
    void example2() {
        check("1,3,2,5,#,#,9,6,#,7", 7);
    }

    @Test
    void example3() {
        check("1,3,2,5", 2);
    }

    @Test
    void singleNode() {
        check("1", 1);
    }

    @Test
    void leftSkewed() {
        check("1,2,#,3", 1);
    }

    @Test
    void rightSkewed() {
        check("1,#,2,#,3", 1);
    }

    @Test
    void completeTree() {
        check("1,2,3,4,5,6,7", 4);
    }

    @Test
    void wideGap() {
        check("1,2,3,4,#,#,5", 4);
    }
}
