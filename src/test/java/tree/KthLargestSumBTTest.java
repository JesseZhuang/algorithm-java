package tree;

import org.junit.jupiter.api.Test;
import struct.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthLargestSumBTTest {

    @Test
    void testExample1() {
        // [5,8,9,2,1,3,7,4,null,null,null,null,null,null,null]
        TreeNode root = TreeNode.readFromLevelOrderString("5,8,9,2,1,3,7,4,#,#,#,#,#,#,#");
        assertEquals(13, KthLargestSumBT.kthLargestLevelSumSort(root, 2));
        assertEquals(13, KthLargestSumBT.kthLargestLevelSumHeap(root, 2));
    }

    @Test
    void testExample2() {
        // [1,2,null,3]
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,#,3,#");
        assertEquals(3, KthLargestSumBT.kthLargestLevelSumSort(root, 1));
        assertEquals(3, KthLargestSumBT.kthLargestLevelSumHeap(root, 1));
    }

    @Test
    void testKExceedsDepth() {
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3");
        assertEquals(-1, KthLargestSumBT.kthLargestLevelSumSort(root, 5));
        assertEquals(-1, KthLargestSumBT.kthLargestLevelSumHeap(root, 5));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(42);
        assertEquals(42, KthLargestSumBT.kthLargestLevelSumSort(root, 1));
        assertEquals(42, KthLargestSumBT.kthLargestLevelSumHeap(root, 1));
    }

    @Test
    void testNegativeValues() {
        // [-1,-2,-3]
        TreeNode root = TreeNode.readFromLevelOrderString("-1,-2,-3");
        assertEquals(-1, KthLargestSumBT.kthLargestLevelSumSort(root, 1));
        assertEquals(-1, KthLargestSumBT.kthLargestLevelSumHeap(root, 1));
        assertEquals(-5, KthLargestSumBT.kthLargestLevelSumSort(root, 2));
        assertEquals(-5, KthLargestSumBT.kthLargestLevelSumHeap(root, 2));
    }

    @Test
    void testKEqualsDepth() {
        // 3-level tree: [1,2,3,4,5,6,7]
        TreeNode root = TreeNode.readFromLevelOrderString("1,2,3,4,5,6,7");
        // Level sums: 1, 5, 22 -> sorted desc: 22, 5, 1
        assertEquals(1, KthLargestSumBT.kthLargestLevelSumSort(root, 3));
        assertEquals(1, KthLargestSumBT.kthLargestLevelSumHeap(root, 3));
    }
}
